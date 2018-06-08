package sh.spinlock.singularity.core.statement;

import sh.spinlock.singularity.core.data.DataTypeMapper;
import sh.spinlock.singularity.core.schema.Column;
import sh.spinlock.singularity.core.schema.Schema;

import java.util.ArrayList;
import java.util.List;

public class SchemaStatementToken extends StatementToken {
    private final Schema schema;

    public SchemaStatementToken(Schema schema) {
        super(null);
        this.schema = schema;
    }

    public Schema getSchema() {
        return schema;
    }

    public String toString(DataTypeMapper typeMapper) {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        List<String> columnStrings = new ArrayList<>();
        for (Column column : schema.getColumns()) {
            columnStrings.add(
                    column.getName() + ' ' + typeMapper.mapType(column.getDataType())
            );
        }
        sb.append(String.join(",", columnStrings));
        sb.append(')');
        return sb.toString();
    }

    public String toString() {
        throw new UnsupportedOperationException("");
    }
}
