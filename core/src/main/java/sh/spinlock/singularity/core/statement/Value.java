package sh.spinlock.singularity.core.statement;

import sh.spinlock.singularity.core.data.types.JsonWrapper;

import java.util.Date;
import java.util.UUID;

public class Value {
    private Boolean booleanValue;
    private Short shortValue;
    private Integer integerValue;
    private Long longValue;
    private String stringValue;
    private UUID uuidValue;
    private JsonWrapper jsonValue;
    private Date dateValue;

    public Value(Boolean value) {
        booleanValue = value;
    }

    public Value(Short value) {
        shortValue = value;
    }

    public Value(Integer value) {
        integerValue = value;
    }

    public Value(Long value) {
        longValue = value;
    }

    public Value(String value) {
        stringValue = value;
    }

    public Value(UUID value) {
        uuidValue = value;
    }

    public Value(JsonWrapper value) {
        jsonValue = value;
    }

    public Value(Date value) {
        dateValue = value;
    }

    @Override
    public String toString() {
        if (booleanValue != null) {
            return booleanValue.toString();
        } else if (shortValue != null) {
            return shortValue.toString();
        } else if (integerValue != null) {
            return integerValue.toString();
        } else if (longValue != null) {
            return longValue.toString();
        } else if (stringValue != null) {
            return stringValue;
        } else if (uuidValue != null) {
            return uuidValue.toString();
        } else if (jsonValue != null) {
            return jsonValue.getJson();
        } else if (dateValue != null) {
            return dateValue.toString();
        }

        return null;
    }

    public Boolean getBoolean() {
        return booleanValue;
    }

    public Short getShort() {
        return shortValue;
    }

    public Integer getInteger() {
        return integerValue;
    }

    public Long getLong() {
        return longValue;
    }

    public String getString() {
        return stringValue;
    }

    public UUID getUuid() {
        return uuidValue;
    }

    public JsonWrapper getJson() {
        return jsonValue;
    }

    public Date getDate() {
        return dateValue;
    }
}
