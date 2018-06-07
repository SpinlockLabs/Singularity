package sh.spinlock.singularity.core.schema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Column> getColumnsByName(String... columnNames) {
        List<String> columnList = Arrays.asList(columnNames);
        return columns.stream().filter(column -> columnList.contains(column.getName())).collect(Collectors.toList());
    }

    public boolean validate() {
        boolean ret = true;

        if (columns.size() == 0) {
            ret = false;
        }

        return ret;
    }
}
