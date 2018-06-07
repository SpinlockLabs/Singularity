package sh.spinlock.singularity.databases.postgresql;

import sh.spinlock.singularity.core.data.DataType;
import sh.spinlock.singularity.core.data.DataTypeMapper;
import sh.spinlock.singularity.core.data.types.JsonWrapper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class PostgresTypeMapper implements DataTypeMapper {
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
    public String convert(Object value) {
        if (value instanceof String) {
            return '\'' + String.valueOf(value) + '\'';
        } else if (value instanceof Short ||
                value instanceof Integer ||
                value instanceof Long) {
            return String.valueOf(value);
        } else if (value instanceof UUID) {
            return '\'' + value.toString() + '\'';
        } else if (value instanceof Date) {
            return '\'' + new Timestamp(((Date) value).getTime()).toString() + '\'';
        } else if (value instanceof JsonWrapper) {
            return "to_json('" + ((JsonWrapper) value).getJson() + "'::json)";
        }

        throw new RuntimeException("Unsupported type " + value.getClass().getCanonicalName());
    }
}
