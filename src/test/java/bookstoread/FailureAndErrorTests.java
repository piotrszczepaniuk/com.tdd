package bookstoread;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class FailureAndErrorTests {

    @Test
    void stringIsNotEmpty(){
        String str = "";
        assertFalse(true, "Assertion is true");
    }

    @Test
    void thisMethodThrowsException(){
        String str = null;
        assertTrue(str.isEmpty());
    }

}

