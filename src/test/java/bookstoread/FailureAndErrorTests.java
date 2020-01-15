package bookstoread;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Disabled("This tests are failling for reason")
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

