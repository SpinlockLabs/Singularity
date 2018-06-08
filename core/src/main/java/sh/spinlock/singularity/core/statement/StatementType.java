package sh.spinlock.singularity.core.statement;

public final class StatementType {
    public static final StatementToken CREATE_TABLE = new StatementToken("CREATE TABLE");
    public static final StatementToken DROP_TABLE = new StatementToken("DROP TABLE");
    public static final StatementToken INSERT = new StatementToken("INSERT");
    public static final StatementToken SELECT = new StatementToken("SELECT");
}
