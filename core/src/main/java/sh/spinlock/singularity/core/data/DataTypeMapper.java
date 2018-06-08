package sh.spinlock.singularity.core.data;

import sh.spinlock.singularity.core.statement.Value;

public interface DataTypeMapper {
    String mapType(DataType dataType);
    String convert(Value value);
}
