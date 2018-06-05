package sh.spinlock.singularity.databases.postgresql;

import sh.spinlock.singularity.core.data.DataTypeMapper;
import sh.spinlock.singularity.core.implementation.jdbc.JdbcConnection;

public class PostgresConnection extends JdbcConnection {
    public PostgresConnection() {
        super();
    }

    @Override
    protected DataTypeMapper createTypeMapper() {
        return new PostgresTypeMapper();
    }
}
