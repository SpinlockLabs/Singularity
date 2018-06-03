package sh.spinlock.singularity.core.schema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SchemaEntry {
    private final Column column;
    private final List<SchemaMeta> metadata;

    private SchemaEntry(Column column, List<SchemaMeta> metadata) {
        this.column = column;
        this.metadata = metadata;
    }

    public Column getColumn() {
        return column;
    }

    public List<SchemaMeta> getMetadata() {
        return Collections.unmodifiableList(metadata);
    }

    public static SchemaEntryBuilder newBuilder() {
        return new SchemaEntryBuilder();
    }

    public static class SchemaEntryBuilder {
        private Column column;
        private List<SchemaMeta> metadata;

        private SchemaEntryBuilder() {
            metadata = new ArrayList<>();
        }

        public SchemaEntryBuilder setColumn(Column column) {
            this.column = column;
            return this;
        }

        public SchemaEntryBuilder addMetadata(SchemaMeta metadata) {
            this.metadata.add(metadata);
            return this;
        }

        public SchemaEntry build() {
            return new SchemaEntry(column, metadata);
        }
    }
}
