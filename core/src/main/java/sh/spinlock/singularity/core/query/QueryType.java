package sh.spinlock.singularity.core.query;

public final class QueryType {
    public static final QueryToken CREATE_TABLE = new QueryToken("CREATE TABLE");
    public static final QueryToken DROP_TABLE = new QueryToken("DROP TABLE");
    public static final QueryToken INSERT = new QueryToken("INSERT");
    public static final QueryToken SELECT = new QueryToken("SELECT");
}
