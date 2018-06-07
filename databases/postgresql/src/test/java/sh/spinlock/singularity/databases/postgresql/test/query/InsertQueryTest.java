package sh.spinlock.singularity.databases.postgresql.test.query;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sh.spinlock.singularity.core.exception.DatabaseException;
import sh.spinlock.singularity.core.exception.QueryException;
import sh.spinlock.singularity.core.query.Query;
import sh.spinlock.singularity.core.query.QueryType;
import sh.spinlock.singularity.databases.postgresql.test.util.EmbeddedPostgresWrapper;

import java.io.IOException;

class InsertQueryTest {
    @BeforeAll
    static void setup() throws IOException, DatabaseException {
    }

    @Test
    void testConnection() throws QueryException {
        EmbeddedPostgresWrapper.getConnection().query(Query.create(QueryType.SELECT).string("1"));
    }
}
