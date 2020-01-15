package bookstoread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("A bookshelf")
public class BookShelfSpec_03 {
    @BeforeEach
    void init() {
    // Test case removed for brevity
    }
    @Nested
    @DisplayName("is empty")
    class IsEmpty {
        @Test
        @DisplayName("when no book is added to it")
        public void emptyBookShelfWhenNoBookAdded() {
        // Test case removed for brevity
        }
        @Test
        @DisplayName("when add is called without books")
        void emptyBookShelfWhenAddIsCalledWithoutBooks() {
        // Test case removed for brevity
        }
    }
    @Nested
    @DisplayName("after adding books")
    class BooksAreAdded {
        @Test
        @DisplayName("contains two books")
        void bookshelfContainsTwoBooksWhenTwoBooksAdded() {
        // Test case removed for brevity
        }
        @Test
        @DisplayName("returns an immutable books collection to client")
        void bookshelfIsImmutableForClient() {
        // Test case removed for brevity
        }
    }
// Test case removed for brevity
}