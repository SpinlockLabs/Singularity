package sh.spinlock.singularity.databases.postgresql.test.util;

import com.google.inject.Guice;
import com.google.inject.Injector;
import ru.yandex.qatools.embed.postgresql.EmbeddedPostgres;
import sh.spinlock.singularity.core.connection.JdbcConnectionConfig;
import sh.spinlock.singularity.core.exception.DatabaseException;
import sh.spinlock.singularity.databases.postgresql.PostgresConnection;
import sh.spinlock.singularity.databases.postgresql.PostgresModule;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import static ru.yandex.qatools.embed.postgresql.distribution.Version.Main.V10;

public class EmbeddedPostgresWrapper {
    private static final String HOSTNAME = "localhost";
    private static final int PORT = 2337;
    private static final String DATABASE = "singularity";
    private static final String USERNAME = "singularity";
    private static final String PASSWORD = "password123";
    private static final JdbcConnectionConfig JDBC_CONFIG;

    private static final EmbeddedPostgres postgres;
    private static PostgresConnection connection;

    static {
        JDBC_CONFIG = new JdbcConnectionConfig()
                .setHost(HOSTNAME)
                .setProtocol("postgresql")
                .setPort(PORT)
                .setDatabase(DATABASE)
                .setUsername(USERNAME)
                .setPassword(PASSWORD);
        postgres = new EmbeddedPostgres(V10);
        try {
            postgres.start(EmbeddedPostgres.cachedRuntimeConfig(Paths.get("postgres")),
                    HOSTNAME,
                    PORT,
                    DATABASE,
                    USERNAME,
                    PASSWORD,
                    new ArrayList<>());
            Injector injector = Guice.createInjector(new PostgresModule(JDBC_CONFIG));
            connection = injector.getInstance(PostgresConnection.class);
            getConnection().connect();
        } catch (IOException | DatabaseException e) {
            e.printStackTrace();
        }
    }

    public static PostgresConnection getConnection() {
        return connection;
    }
}
