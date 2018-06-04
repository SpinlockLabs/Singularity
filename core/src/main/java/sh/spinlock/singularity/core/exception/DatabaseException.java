package sh.spinlock.singularity.core.exception;

public class DatabaseException extends Exception {
    public DatabaseException(String message, Exception innerException) {
        super(message, innerException);
    }
}
