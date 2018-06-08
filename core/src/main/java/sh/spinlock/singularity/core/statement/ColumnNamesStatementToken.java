package sh.spinlock.singularity.core.statement;

import java.util.Collections;
import java.util.List;

public class ColumnNamesStatementToken extends StatementToken {
    private final List<String> columnNames;

    public ColumnNamesStatementToken(List<String> columnNames) {
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
