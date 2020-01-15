package bookstoread;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

abstract class DBConnectionPool {
    @BeforeAll
    static void connectDBConnectionPool(){
    }

    @AfterAll
    static void closeDBConnectionPool(){
    }
}
