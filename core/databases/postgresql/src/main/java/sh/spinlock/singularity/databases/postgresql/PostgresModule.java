package sh.spinlock.singularity.databases.postgresql;

import com.google.inject.AbstractModule;
import sh.spinlock.singularity.core.abstractions.Connection;
import sh.spinlock.singularity.core.connection.JdbcConnectionConfig;

public class PostgresModule extends AbstractModule {
    private final JdbcConnectionConfig config;

    public PostgresModule(JdbcConnectionConfig config) {
        this.config = config;
    }

    @Override
    protected void configure() {
        // Bind classes with passed-in instances
        bind(JdbcConnectionConfig.class).toInstance(config);

        // Bind abstract classes to implementation classes
        bind(Connection.class).to(PostgresConnection.class);
    }
}
