package sh.spinlock.singularity.core.schema;

import sh.spinlock.singularity.core.data.DataType;

public class Column {
    private final String name;
    private final DataType dataType;

    public Column(String name, DataType dataType) {
        this.name = name;
        this.dataType = dataType;
    }

    public String getName() {
        return name;
    }

    public DataType getDataType() {
        return dataType;
    }
}
