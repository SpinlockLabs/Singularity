package sh.spinlock.singularity.databases.postgresql;

import sh.spinlock.singularity.core.data.DataType;
import sh.spinlock.singularity.core.data.types.JsonWrapper;
import sh.spinlock.singularity.core.implementation.jdbc.JdbcTypeMapper;
import sh.spinlock.singularity.core.statement.Value;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class PostgresTypeMapper extends JdbcTypeMapper {
    @Override
    public String mapType(DataType dataType) {
        switch (dataType) {
            case STRING:
                return "text";
            case BOOLEAN:
                return "boolean";
            case SHORT:
                return "smallint";
            case INTEGER:
                return "integer";
            case LONG:
                return "bigint";
            case UUID:
                return "uuid";
            case JSON:
                return "json";
            case TIMESTAMP:
                return "timestamp";
            default:
                throw new IllegalArgumentException("Could not map type: " + dataType.name());
        }
    }

    @Override
    public String convert(Value value) {
        if (value.getString() != null) {
            return '\'' + value.toString() + '\'';
        } else if (value.getShort() != null ||
                value.getInteger() != null ||
                value.getLong() != null) {
            return value.toString();
        } else if (value.getUuid() != null) {
            return '\'' + value.toString() + '\'';
        } else if (value.getDate() != null) {
            return '\'' + new Timestamp(value.getDate().getTime()).toString() + '\'';
        } else if (value.getJson() != null) {
            return "to_json('" + value.getJson().getJson() + "'::json)";
        }

        return null;
    }

    @Override
    public Value toValue(String typeName, int columnId, ResultSet resultSet) throws SQLException {
        switch (typeName) {
            case "text":
                return new Value(resultSet.getString(columnId));
            case "boolean":
                return new Value(resultSet.getBoolean(columnId));
            case "smallint":
                return new Value(resultSet.getShort(columnId));
            case "integer":
                return new Value(resultSet.getInt(columnId));
            case "bigint":
                return new Value(resultSet.getLong(columnId));
            case "uuid":
                return new Value((UUID) resultSet.getObject(columnId));
            case "json":
                return new Value(new JsonWrapper(resultSet.getString(columnId)));
            case "timestamp":
                return new Value(new Date(resultSet.getTimestamp(columnId).getTime()));
            default:
                throw new IllegalArgumentException("Could not map type: " + typeName);
        }
    }
}
