package sh.spinlock.singularity.core.schema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Schema {
    private final List<Column> columns;

    public Schema() {
        columns = new ArrayList<>();
    }

    public final void addColumn(Column column) {
        columns.add(column);
    }

    public final List<Column> getColumns() {
        return Collections.unmodifiableList(columns);
    }

    public boolean validate() {
        boolean ret = true;

        if (columns.size() == 0) {
            ret = false;
        }

        return ret;
    }
}
