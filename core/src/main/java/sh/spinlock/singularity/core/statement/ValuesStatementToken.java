package sh.spinlock.singularity.core.statement;

import sh.spinlock.singularity.core.data.DataTypeMapper;

import java.util.ArrayList;
import java.util.List;

public class ValuesStatementToken extends StatementToken {
    private final List<Object> values;

    public ValuesStatementToken(List<Object> values) {
        super(null);
        this.values = values;
    }

    public String toString(DataTypeMapper typeMapper) {
        List<String> strValues = new ArrayList<>();
        for (Object value : values) {
            strValues.add(typeMapper.convert(value));
        }
        return '(' + String.join(",", strValues) + ')';
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("");
    }
}
