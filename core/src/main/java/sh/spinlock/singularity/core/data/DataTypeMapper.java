package sh.spinlock.singularity.core.data;

public interface DataTypeMapper {
    String mapType(DataType dataType);
    String convert(Object value);
}
