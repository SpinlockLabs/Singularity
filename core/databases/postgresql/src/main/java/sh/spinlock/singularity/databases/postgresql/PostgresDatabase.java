package sh.spinlock.singularity.databases.postgresql;

import sh.spinlock.singularity.core.abstractions.Database;
import sh.spinlock.singularity.core.abstractions.Schema;
import sh.spinlock.singularity.core.abstractions.Table;

public class PostgresDatabase extends Database {
    @Override
    public Table createTable(String name, Schema schema) {
        return null;
    }
}
