package sh.spinlock.singularity.examples.timeseries;

import com.google.inject.Guice;
import com.google.inject.Injector;
import sh.spinlock.singularity.core.exception.DatabaseException;
import sh.spinlock.singularity.core.connection.JdbcConnectionConfig;
import sh.spinlock.singularity.core.exception.QueryException;
import sh.spinlock.singularity.core.query.Query;
import sh.spinlock.singularity.core.query.QueryType;
import sh.spinlock.singularity.core.schema.Column;
import sh.spinlock.singularity.core.data.DataType;
import sh.spinlock.singularity.core.schema.Schema;
import sh.spinlock.singularity.databases.postgresql.PostgresConnection;
import sh.spinlock.singularity.databases.postgresql.PostgresModule;

public class Main {
    public static void main(String[] args) {
        JdbcConnectionConfig config = new JdbcConnectionConfig()
                .setProtocol("postgresql")
                .setHost("localhost")
                .setPort(5432)
                .setUsername("logan")
                .setDatabase("singularity");

        Schema schema = new Schema() {{
            addColumn(new Column("FirstName", DataType.STRING));
            addColumn(new Column("LastName", DataType.STRING));
        }};

        Injector injector = Guice.createInjector(new PostgresModule(config));
        PostgresConnection connection = injector.getInstance(PostgresConnection.class);
        try {
            connection.connect();
            connection.query(
                    Query.create(QueryType.CREATE_TABLE)
                            .ifNotExists()
                            .name("Test")
                            .withSchema(schema)
            );
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (QueryException e) {
            System.err.println("Query: " + e.getQueryString());
            e.printStackTrace();
        }
    }
}
