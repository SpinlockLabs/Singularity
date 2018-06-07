package sh.spinlock.singularity.core.query;

import java.util.Collections;
import java.util.List;

public class ColumnNamesQueryToken extends QueryToken {
    private final List<String> columnNames;

    public ColumnNamesQueryToken(List<String> columnNames) {
        super(null);
        this.columnNames = columnNames;
    }

    public List<String> getColumnNames() {
        return Collections.unmodifiableList(columnNames);
    }

    public String toString() {
        return '(' + String.join(",", columnNames) + ')';
    }
}
