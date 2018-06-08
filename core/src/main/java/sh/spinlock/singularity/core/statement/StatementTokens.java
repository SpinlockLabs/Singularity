package sh.spinlock.singularity.core.statement;

public class StatementTokens {
    public static final StatementToken IF_NOT_EXISTS = new StatementToken("IF NOT EXISTS");
    public static final StatementToken IF_EXISTS = new StatementToken("IF EXISTS");
    public static final StatementToken INTO = new StatementToken("INTO");
    public static final StatementToken VALUES = new StatementToken("VALUES");
    public static final StatementToken FROM = new StatementToken("FROM");
}
