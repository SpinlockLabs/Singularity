package sh.spinlock.singularity.core.implementation.jdbc;

import com.google.inject.Inject;
import sh.spinlock.singularity.core.exception.DatabaseException;
import sh.spinlock.singularity.core.connection.JdbcConnectionConfig;
import sh.spinlock.singularity.core.exception.QueryException;
import sh.spinlock.singularity.core.query.Query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class JdbcConnection extends sh.spinlock.singularity.core.abstractions.Connection {
    @Inject
    private JdbcConnectionConfig config;
    private Connection connection;

    protected JdbcConnection() {
        super();
    }

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

    @Override
    public void query(Query query) throws QueryException {
        String queryString = formatQuery(query);

        try {
            connection.createStatement().execute(queryString);
        } catch (SQLException e) {
            throw new QueryException("Failed to run query", queryString, e);
        }
    }
}
