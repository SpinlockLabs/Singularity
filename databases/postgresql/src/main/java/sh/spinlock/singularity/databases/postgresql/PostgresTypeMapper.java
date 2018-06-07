package sh.spinlock.singularity.databases.postgresql;

import sh.spinlock.singularity.core.data.DataType;
import sh.spinlock.singularity.core.data.DataTypeMapper;

public class PostgresTypeMapper implements DataTypeMapper {
    @Override
    public String mapType(DataType dataType) {
        switch (dataType) {
            case STRING:
                return "text";
            default:
                throw new IllegalArgumentException("Could not map type: " + dataType.name());
        }
    }

    @Override
    public String convert(Object value) {
        if (value instanceof String) {
            return '\'' + String.valueOf(value) + '\'';
        }

        throw new RuntimeException("Unsupported type " + value.getClass().getCanonicalName());
    }
}
