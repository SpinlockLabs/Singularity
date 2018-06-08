package sh.spinlock.singularity.core.implementation.jdbc;

import sh.spinlock.singularity.core.data.DataTypeMapper;
import sh.spinlock.singularity.core.statement.Value;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class JdbcTypeMapper implements DataTypeMapper {
    public abstract Value toValue(String typeName, int columnId, ResultSet resultSet) throws SQLException;
}
