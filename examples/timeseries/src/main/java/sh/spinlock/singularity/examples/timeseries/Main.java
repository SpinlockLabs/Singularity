package sh.spinlock.singularity.examples.timeseries;

import com.google.inject.Guice;
import com.google.inject.Injector;
import sh.spinlock.singularity.core.DatabaseException;
import sh.spinlock.singularity.core.connection.JdbcConnectionConfig;
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

        Injector injector = Guice.createInjector(new PostgresModule(config));
        PostgresConnection connection = injector.getInstance(PostgresConnection.class);
        try {
            connection.connect();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}
