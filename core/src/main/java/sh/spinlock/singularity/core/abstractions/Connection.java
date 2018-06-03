package sh.spinlock.singularity.core.abstractions;

import sh.spinlock.singularity.core.DatabaseException;

public abstract class Connection {
    public abstract void connect() throws DatabaseException;
}
