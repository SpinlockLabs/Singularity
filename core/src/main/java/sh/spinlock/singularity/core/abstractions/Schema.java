package sh.spinlock.singularity.core.abstractions;

import sh.spinlock.singularity.core.schema.SchemaEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Schema {
    private final List<SchemaEntry> schemaEntries;

    public Schema() {
        schemaEntries = new ArrayList<>();
    }

    public final void addSchemaEntry(SchemaEntry schemaEntry) {
        schemaEntries.add(schemaEntry);
    }

    public final List<SchemaEntry> getSchemaEntries() {
        return Collections.unmodifiableList(schemaEntries);
    }

    public boolean validate() {
        boolean ret = true;

        if (schemaEntries.size() == 0) {
            ret = false;
        }

        return ret;
    }
}
