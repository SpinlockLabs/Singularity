package sh.spinlock.singularity.core.query;

public class QueryToken {
    private final String value;

    public QueryToken(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
