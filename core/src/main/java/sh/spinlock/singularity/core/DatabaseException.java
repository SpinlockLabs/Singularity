package sh.spinlock.singularity.core;

public class DatabaseException extends Exception {
    public DatabaseException(String message, Exception innerException) {
        super(message, innerException);
    }
}
