package sh.spinlock.singularity.core.query;

import sh.spinlock.singularity.core.schema.Column;
import sh.spinlock.singularity.core.schema.Schema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Query {
    private final List<QueryToken> tokens;

    private Query() {
        tokens = new ArrayList<>();
    }

    /**
     * Inserts a QueryToken of custom value.
     * @param value String value to insert.
     * @return This Query.
     */
    public Query string(String value) {
        return addToken(new QueryToken(value));
    }

    /**
     * Wrapper of {@link #string(String)} with different name.
     * @param name Name of
     * @return This Query.
     */
    public Query name(String name) {
        return string(name);
    }

    /**
     * Adds QueryToken.IF_NOT_EXISTS, and should be used after a table.
     * @return This Query.
     */
    public Query ifNotExists() {
        return addToken(QueryTokens.IF_NOT_EXISTS);
    }

    public Query ifExists() {
        return addToken(QueryTokens.IF_EXISTS);
    }

    /**
     * Adds QueryToken.INTO and table name, used in an INSERT query.
     * @param tableName Table name.
     * @return This Query.
     */
    public Query into(String tableName) {
        return addToken(QueryTokens.INTO)
                .addToken(new QueryToken(tableName));
    }

    public Query withSchema(Schema schema) {
        return addToken(new SchemaQueryToken(schema));
    }

    public Query withColumnNames(List<Column> columns) {
        return addToken(new ColumnNamesQueryToken(columns.stream().map(Column::getName).collect(Collectors.toList())));
    }

    public Query values(Object... values) {
        return addToken(QueryTokens.VALUES)
                .addToken(new ValuesQueryToken(Arrays.asList(values)));
    }

    public List<QueryToken> getTokens() {
        return Collections.unmodifiableList(tokens);
    }

    /**
     * Add a QueryToken to the Query.
     * @param token QueryToken to be added.
     * @return This Query.
     */
    private Query addToken(QueryToken token) {
        if (token == null) {
            throw new IllegalArgumentException("Token is null.");
        }

        tokens.add(token);
        return this;
    }

    /**
     * Create new Query with QueryToken.
     * @param queryType QueryToken constant from QueryType class.
     * @return New instance of Query.
     */
    public static Query create(QueryToken queryType) {
        return new Query().addToken(queryType);
    }
}
