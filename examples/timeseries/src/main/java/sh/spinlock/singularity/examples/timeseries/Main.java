package sh.spinlock.singularity.examples.timeseries;

import com.google.inject.Guice;
import com.google.inject.Injector;
import sh.spinlock.singularity.core.connection.JdbcConnectionConfig;
import sh.spinlock.singularity.core.data.DataType;
import sh.spinlock.singularity.core.data.types.JsonWrapper;
import sh.spinlock.singularity.core.exception.DatabaseException;
import sh.spinlock.singularity.core.exception.QueryException;
import sh.spinlock.singularity.core.statement.Statement;
import sh.spinlock.singularity.core.statement.StatementType;
import sh.spinlock.singularity.core.statement.Row;
import sh.spinlock.singularity.core.schema.Column;
import sh.spinlock.singularity.core.schema.Schema;
import sh.spinlock.singularity.databases.postgresql.PostgresConnection;
import sh.spinlock.singularity.databases.postgresql.PostgresModule;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        JdbcConnectionConfig config = new JdbcConnectionConfig()
                .setProtocol("postgresql")
                .setHost("localhost")
                .setPort(5432)
                .setUsername("logan")
                .setDatabase("singularity");

        Schema schema = new Schema() {{
            addColumn(new Column("time", DataType.TIMESTAMP));
            addColumn(new Column("path", DataType.UUID));
            addColumn(new Column("value", DataType.JSON));
        }};

        Injector injector = Guice.createInjector(new PostgresModule(config));
        PostgresConnection connection = injector.getInstance(PostgresConnection.class);
        UUID uuid = UUID.randomUUID();
        try {
            connection.connect();
            connection.execute(
                    Statement.create(StatementType.DROP_TABLE)
                            .ifExists()
                            .name("Test")
            );
            connection.execute(
                    Statement.create(StatementType.CREATE_TABLE)
                            .name("Test")
                            .withSchema(schema)
            );
            int rowsUpdated = connection.update(
                    Statement.create(StatementType.INSERT)
                            .into("Test")
                            .withColumnNames(schema.getColumnsByName("time", "path", "value"))
                            .values(new Date(), uuid, new JsonWrapper("[\"test\"]"))
            );
            List<Row> result = connection.query(
                    Statement.create(StatementType.SELECT)
                            .string("*")
                            .from()
                            .name("Test")
            );

            System.out.print(rowsUpdated);
            System.out.println(" rows updated.");
            for (Row row : result) {
                System.out.println(row.toString());
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (QueryException e) {
            System.err.println("Statement: " + e.getQueryString());
            e.printStackTrace();
        }
    }
}
