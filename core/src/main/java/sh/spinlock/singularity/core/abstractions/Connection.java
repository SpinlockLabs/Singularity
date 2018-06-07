package sh.spinlock.singularity.core.abstractions;

import sh.spinlock.singularity.core.data.DataTypeMapper;
import sh.spinlock.singularity.core.exception.DatabaseException;
import sh.spinlock.singularity.core.exception.QueryException;
import sh.spinlock.singularity.core.query.Query;
import sh.spinlock.singularity.core.query.QueryToken;
import sh.spinlock.singularity.core.query.SchemaQueryToken;
import sh.spinlock.singularity.core.query.ValuesQueryToken;

import java.util.ArrayList;
import java.util.List;

public abstract class Connection {
    private final DataTypeMapper typeMapper;

    protected Connection() {
        typeMapper = createTypeMapper();
    }

    public abstract void connect() throws DatabaseException;
    public abstract void query(Query query) throws QueryException;
    protected abstract DataTypeMapper createTypeMapper();

    public DataTypeMapper getTypeMapper() {
        return typeMapper;
    }

    protected String formatQuery(Query query) {
        List<String> tokenStrings = new ArrayList<>();

        for (QueryToken queryToken : query.getTokens()) {
            String tokenString;
            if (queryToken instanceof SchemaQueryToken) {
                tokenString = ((SchemaQueryToken) queryToken).toString(getTypeMapper());
            } else if (queryToken instanceof ValuesQueryToken) {
                tokenString = ((ValuesQueryToken) queryToken).toString(getTypeMapper());
            } else {
                tokenString = queryToken.toString();
            }
            tokenStrings.add(tokenString);
        }

        return String.join(" ", tokenStrings);
    }
}
