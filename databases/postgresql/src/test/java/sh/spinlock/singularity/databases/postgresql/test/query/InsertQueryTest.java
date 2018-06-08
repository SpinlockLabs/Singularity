package sh.spinlock.singularity.databases.postgresql.test.query;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sh.spinlock.singularity.core.exception.DatabaseException;
import sh.spinlock.singularity.core.exception.QueryException;
import sh.spinlock.singularity.core.statement.Statement;
import sh.spinlock.singularity.core.statement.StatementType;
import sh.spinlock.singularity.databases.postgresql.test.util.EmbeddedPostgresWrapper;

import java.io.IOException;

class InsertQueryTest {
    @BeforeAll
    static void setup() throws IOException, DatabaseException {
    }

    @Test
    void testConnection() throws QueryException {
        EmbeddedPostgresWrapper.getConnection().query(Statement.create(StatementType.SELECT).string("1"));
    }
}
