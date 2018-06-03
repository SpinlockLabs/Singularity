package sh.spinlock.singularity.databases.postgresql;

import com.google.inject.Inject;
import sh.spinlock.singularity.core.DatabaseException;
import sh.spinlock.singularity.core.connection.JdbcConnectionConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection extends sh.spinlock.singularity.core.abstractions.Connection {
    @Inject
    private JdbcConnectionConfig config;
    private Connection connection;

    @Override
    public void connect() throws DatabaseException {
        try {
            connection = DriverManager.getConnection(config.uri(), config.props());
        } catch (SQLException e) {
            throw new DatabaseException("Failed to connect to database", e);
        }
        try {
            connection.createStatement().execute("SELECT 1");
        } catch (SQLException e) {
            throw new DatabaseException("Failed to test database connection after connect", e);
        }
    }
}
