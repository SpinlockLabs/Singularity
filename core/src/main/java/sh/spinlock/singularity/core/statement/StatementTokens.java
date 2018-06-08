package sh.spinlock.singularity.core.statement;

public class StatementTokens {
    public static final StatementToken IF_NOT_EXISTS = new StatementToken("IF NOT EXISTS");
    public static final StatementToken IF_EXISTS = new StatementToken("IF EXISTS");
    public static final StatementToken INTO = new StatementToken("INTO");
    public static final StatementToken VALUES = new StatementToken("VALUES");
    public static final StatementToken FROM = new StatementToken("FROM");
    public static final StatementToken WHERE = new StatementToken("WHERE");
    public static final StatementToken AND = new StatementToken("AND");
    public static final StatementToken OR = new StatementToken("OR");
    public static final StatementToken EQUALS_SIGN = new StatementToken("=");
    public static final StatementToken LESS_THAN_SIGN = new StatementToken("<");
    public static final StatementToken LESS_THAN_EQUAL_TO_SIGN = new StatementToken("<=");
    public static final StatementToken GREATER_THAN_SIGN = new StatementToken(">");
    public static final StatementToken GREATER_THAN_EQUAL_TO_SIGN = new StatementToken(">=");
}
