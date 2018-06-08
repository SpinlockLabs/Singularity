package sh.spinlock.singularity.core.statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Row {
    private final Map<String, Value> columnValues;

    public Row() {
        columnValues = new HashMap<>();
    }

    public Value getColumn(String name) {
        return columnValues.get(name);
    }

    public void setColumn(String name, Value value) {
        columnValues.put(name, value);
    }

    @Override
    public String toString() {
        List<String> kv = new ArrayList<>();
        for (Map.Entry<String, Value> columnValue : columnValues.entrySet()) {
            kv.add(columnValue.getKey() + '=' + columnValue.getValue());
        }
        return "Row{" + String.join(",", kv) + "}";
    }
}
