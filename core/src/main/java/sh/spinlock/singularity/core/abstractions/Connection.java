package sh.spinlock.singularity.core.abstractions;

import sh.spinlock.singularity.core.data.DataTypeMapper;
import sh.spinlock.singularity.core.exception.DatabaseException;
import sh.spinlock.singularity.core.exception.QueryException;
import sh.spinlock.singularity.core.statement.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Connection {
    private final DataTypeMapper typeMapper;

    protected Connection() {
        typeMapper = createTypeMapper();
    }

    public abstract void connect() throws DatabaseException;
    public abstract int update(Statement statement) throws QueryException;
    public abstract void execute(Statement statement) throws QueryException;
    public abstract List<Row> query(Statement statement) throws QueryException;
    protected abstract DataTypeMapper createTypeMapper();

    public DataTypeMapper getTypeMapper() {
        return typeMapper;
    }

    protected String formatQuery(Statement statement) {
        List<String> tokenStrings = new ArrayList<>();

        for (StatementToken statementToken : statement.getTokens()) {
            String tokenString;
            if (statementToken instanceof SchemaStatementToken) {
                tokenString = ((SchemaStatementToken) statementToken).toString(getTypeMapper());
            } else if (statementToken instanceof ValuesStatementToken) {
                tokenString = ((ValuesStatementToken) statementToken).toString(getTypeMapper());
            } else {
                tokenString = statementToken.toString();
            }
            tokenStrings.add(tokenString);
        }

        return String.join(" ", tokenStrings);
    }
}
