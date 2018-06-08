package sh.spinlock.singularity.core.statement;

public class StatementToken {
    private final String value;

    public StatementToken(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
