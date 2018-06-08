package sh.spinlock.singularity.core.implementation.jdbc;

import com.google.inject.Inject;
import sh.spinlock.singularity.core.exception.DatabaseException;
import sh.spinlock.singularity.core.connection.JdbcConnectionConfig;
import sh.spinlock.singularity.core.exception.QueryException;
import sh.spinlock.singularity.core.statement.Statement;
import sh.spinlock.singularity.core.statement.Row;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public void execute(Statement statement) throws QueryException {
        String queryString = formatQuery(statement);

        try {
            connection.createStatement().execute(queryString);
        } catch (SQLException e) {
            throw new QueryException("Failed to run statement", queryString, e);
        }
    }

    @Override
    public int update(Statement statement) throws QueryException {
        String queryString = formatQuery(statement);

        try {
            return connection.createStatement().executeUpdate(queryString);
        } catch (SQLException e) {
            throw new QueryException("Failed to run statement", queryString, e);
        }
    }

    @Override
    public List<Row> query(Statement statement) throws QueryException {
        String queryString = formatQuery(statement);
        List<Row> result = new ArrayList<>();

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(queryString);

            while (resultSet.next()) {
                Row row = new Row();
                result.add(row);
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    String typeName = resultSet.getMetaData().getColumnTypeName(i);
                    row.setColumn(
                            resultSet.getMetaData().getColumnName(i),
                            getTypeMapper().toValue(typeName, i, resultSet));
                }
            }
        } catch (SQLException e) {
            throw new QueryException("Failed to run statement", queryString, e);
        }

        return result;
    }

    @Override
    public JdbcTypeMapper getTypeMapper() {
        return (JdbcTypeMapper) super.getTypeMapper();
    }
}
