package sh.spinlock.singularity.core.query;

public class Value {
    private String stringValue;

    public Value(String value) {
        stringValue = value;
    }

    public String getString() {
        return stringValue;
    }
}
