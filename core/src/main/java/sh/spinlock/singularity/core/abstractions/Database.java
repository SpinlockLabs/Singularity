package sh.spinlock.singularity.core.abstractions;

public abstract class Database {
    /**
     * Create a table in the database.
     * @param tableName Name of table.
     * @param schema Database schema
     * @return Newly created Table instance.
     */
    public Table createTable(String tableName, Schema schema) {
        if (!validateTableName(tableName)) {
            throw new IllegalArgumentException("Invalid table name.");
        }

        if (schema == null) {
            throw new IllegalArgumentException("Table schema is null.");
        }

        if (!schema.validate()) {
            throw new IllegalArgumentException("Invalid table schema.");
        }

        return null;
    }

    /**
     * Drop a table in the database.
     * @param tableName Name of table.
     */
    public void dropTable(String tableName) {
        if (!validateTableName(tableName)) {
            throw new IllegalArgumentException("Invalid table name.");
        }
    }

    /**
     * Validates the table name against a set of restrictions.
     * Does not check whether the table already exists.
     * This implementation checks whether the String is null,
     * as well as null.
     * @param tableName Name of table.
     * @return True if this table name is valid.
     */
    public boolean validateTableName(String tableName) {
        if (tableName == null) {
            return false;
        }

        if (tableName.isEmpty()) {
            return false;
        }

        return true;
    }
}
