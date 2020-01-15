package bookstoread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookShelfSpec {

    private BookShelf shelf;

    @BeforeEach
    void init() throws Exception {
        shelf = new BookShelf();
    }


    @Test
    public void emptyBookShelfWhenNoBookAdded(){
        List<String> books = shelf.books();
        //Then
        assertTrue(books.isEmpty(), "BookShelf should be empty");

    }

    @Test
    public void emptyBookShelfWhenAddIsCalledWithoutBooks(){

        shelf.add();
        List<String> books = shelf.books();
        assertTrue(books.isEmpty(), "BookShelf should be empty.");
    }

    @Test
    void bookshelfContainsTwoBooksWhenTwoBooksAdded() {

        shelf.add("Effective Java","Code Complete");
        List<String> books = shelf.books();
        assertEquals(2, books.size(), () -> "BookShelf should have two books.");
    }

    @Test
    void booksReturnedFromBookShelfIsImmutableForClient(){

        shelf.add("Effective Java","Code Complete");
        List<String> books = shelf.books();
        try{
            books.add("The Mythical Man-Month");
            fail(()->"Should not be able to add book to books.");
        }catch (Exception e){
            assertTrue(e instanceof  UnsupportedOperationException, () -> "Should " +
                    "throw UnsuportedOperationException.");

        }
    }

    @Test
    void bookshelfArrangedByBookTitle(){
        BookShelf shelf = new BookShelf();
        shelf.add("Effective Java","Code Complete", "The Mythical Man-Month");
        List<String> books = shelf.arrange();
        assertEquals(Arrays.asList("Code Complete", "Effective Java","The Mythical Man-Month"),
                books, () -> "Books in a bookshelf should be arranged by default by book title.");

    }

    @Test
    void booksInBookShelfAreInInsertionOrderAfterCallingArrange(){
        BookShelf shelf = new BookShelf();
        shelf.add("Effective Java","Code Complete", "The Mythical Man-Month");
        shelf.arrange();
        List<String> books = shelf.books();
        assertEquals(Arrays.asList("Effective Java","Code Complete", "The Mythical Man-Month"),
                books, () -> "Books in insert are in insertion order");

    }


}
