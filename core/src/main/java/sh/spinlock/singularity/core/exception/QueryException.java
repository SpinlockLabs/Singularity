package sh.spinlock.singularity.core.exception;

public class QueryException extends Exception {
    private final String queryString;

    public QueryException(String message, String queryString, Exception innerException) {
        super(message, innerException);
        this.queryString = queryString;
    }

    public String getQueryString() {
        return queryString;
    }
}
