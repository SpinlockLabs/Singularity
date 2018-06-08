package sh.spinlock.singularity.core.statement;

import sh.spinlock.singularity.core.schema.Column;
import sh.spinlock.singularity.core.schema.Schema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Statement {
    private final List<StatementToken> tokens;

    private Statement() {
        tokens = new ArrayList<>();
    }

    /**
     * Create new Statement with StatementToken.
     * @param queryType StatementToken constant from StatementType class.
     * @return New instance of Statement.
     */
    public static Statement create(StatementToken queryType) {
        return new Statement().addToken(queryType);
    }

    /**
     * Inserts a StatementToken of custom value.
     * @param value String value to insert.
     * @return This Statement.
     */
    public Statement string(String value) {
        return addToken(new StatementToken(value));
    }

    /**
     * Wrapper of {@link #string(String)} with different name.
     * @param name String to insert.
     * @return This Statement.
     */
    public Statement name(String name) {
        return string(name);
    }

    /**
     * Wrapper of {@link #string(String)} with different name.
     * @param column String to insert.
     * @return This Statement.
     */
    public Statement column(String column) {
        return string(column);
    }

    public Statement from() {
        return addToken(StatementTokens.FROM);
    }

    /**
     * Adds StatementToken.IF_NOT_EXISTS, and should be used after a table.
     * @return This Statement.
     */
    public Statement ifNotExists() {
        return addToken(StatementTokens.IF_NOT_EXISTS);
    }

    public Statement ifExists() {
        return addToken(StatementTokens.IF_EXISTS);
    }

    /**
     * Adds StatementToken.INTO and table name, used in an INSERT statement.
     * @param tableName Table name.
     * @return This Statement.
     */
    public Statement into(String tableName) {
        return addToken(StatementTokens.INTO)
                .addToken(new StatementToken(tableName));
    }

    public Statement withSchema(Schema schema) {
        return addToken(new SchemaStatementToken(schema));
    }

    public Statement withColumnNames(List<Column> columns) {
        return addToken(new ColumnNamesStatementToken(columns.stream().map(Column::getName).collect(Collectors.toList())));
    }

    public Statement values(Value... values) {
        return addToken(StatementTokens.VALUES)
                .addToken(new ValuesStatementToken(Arrays.asList(values)));
    }

    public Statement where() {
        return addToken(StatementTokens.WHERE);
    }

    public List<StatementToken> getTokens() {
        return Collections.unmodifiableList(tokens);
    }

    /**
     * Add a StatementToken to the Statement.
     * @param token StatementToken to be added.
     * @return This Statement.
     */
    private Statement addToken(StatementToken token) {
        if (token == null) {
            throw new IllegalArgumentException("Token is null.");
        }

        tokens.add(token);
        return this;
    }

    public Statement equals(Value value) {
        return addToken(StatementTokens.EQUALS_SIGN)
                .addToken(new ValueStatementToken(value));
    }
}
