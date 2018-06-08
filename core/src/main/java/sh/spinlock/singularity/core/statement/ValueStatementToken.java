package sh.spinlock.singularity.core.statement;

import sh.spinlock.singularity.core.data.DataTypeMapper;

public class ValueStatementToken extends StatementToken {
    private final Value value;

    public ValueStatementToken(Value value) {
        super(null);
        this.value = value;
    }

    public String toString(DataTypeMapper typeMapper) {
        return typeMapper.convert(value);
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
