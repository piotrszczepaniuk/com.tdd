package bookstoread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class BookShelfSpec_02 {

    private BookShelf_02 shelf;
    private Book effectiveJava;
    private Book cleanCode;
    private Book codeComplete;
    private Book mythicalManMonth;

    @BeforeEach
    void init() throws Exception {
        shelf = new BookShelf_02();
        effectiveJava = new Book("Effective Java", "Joshua Bloch",
                LocalDate.of(2008, Month.MAY, 8));
       cleanCode = new Book("Clean Code", "Robert C. Martin",
                LocalDate.of(2008, Month.APRIL, 8));
        codeComplete = new Book("Code Complete", "Steve McConnel",
                LocalDate.of(2004, Month.JUNE, 9));
        mythicalManMonth = new Book("The Mythical Man-Month\",", "Frederick\n" +
                "Phillips Brooks", LocalDate.of(1975, Month.JANUARY, 1));
    }

    @Test
    public void emptyBookShelfWhenNoBookAdded(){
        List<Book> books = shelf.books();
        //Then
        assertTrue(books.isEmpty(), "BookShelf should be empty");

    }

    @Test
    public void emptyBookShelfWhenAddIsCalledWithoutBooks(){

        shelf.add();
        List<Book> books = shelf.books();
        assertTrue(books.isEmpty(), "BookShelf should be empty.");
    }

    @Test
    void bookshelfContainsTwoBooksWhenTwoBooksAdded() {

        shelf.add(effectiveJava,codeComplete);
        List<Book> books = shelf.books();
        assertEquals(2, books.size(), () -> "BookShelf should have two books.");
    }

    @Test
    void booksReturnedFromBookShelfIsImmutableForClient(){

        shelf.add(effectiveJava,codeComplete);
        List<Book> books = shelf.books();
        try{
            books.add(mythicalManMonth);
            fail(()->"Should not be able to add book to books.");
        }catch (Exception e){
            assertTrue(e instanceof  UnsupportedOperationException, () -> "Should " +
                    "throw UnsuportedOperationException.");

        }
    }

    //@Disabled("Needs to implement Comparator")
    @DisplayName("bookshelf is arranged lexicographically by book title")
    @Test
    void bookshelfArrangedByBookTitle(){

        shelf.add(effectiveJava,codeComplete, mythicalManMonth);
        List<Book> books = shelf.arrange();
        assertEquals(Arrays.asList(codeComplete, effectiveJava, mythicalManMonth),
                books, () -> "Books in a bookshelf should be arranged by default by book title.");

    }

    @Test
    void booksInBookShelfAreInInsertionOrderAfterCallingArrange(){
        shelf.add(effectiveJava,codeComplete, mythicalManMonth);
        shelf.arrange();
        List<Book> books = shelf.books();
        assertEquals(Arrays.asList(effectiveJava,codeComplete,mythicalManMonth),
                books, () -> "Books in insert are in insertion order");

    }


    @Test
    void bookshelfArrangeByUserProvidedCriteria(){
        shelf.add(effectiveJava,codeComplete,mythicalManMonth);
        List<Book> books = shelf.arrange(Comparator.<Book>naturalOrder().reversed());
        assertEquals(
                Arrays.asList(mythicalManMonth,effectiveJava,codeComplete),
                books,
                () -> "Books in a bookshelf are arranged in descending order of book title");
    }

    @Test
    void bookshelfArrangeByUserProvidedCriteria2(){
        shelf.add(effectiveJava,codeComplete,mythicalManMonth);
        Comparator<Book> reserved = Comparator.<Book>naturalOrder().reversed();
        List<Book> books = shelf.arrange(Comparator.<Book>naturalOrder().reversed());
        assertThat(books).isSortedAccordingTo(reserved);
    }

    @Test
    @DisplayName("books inside bookshelf are grouped by publication year")
    void groupBooksInsideBookShelfByPublicationYear(){
        shelf.add(effectiveJava,codeComplete,mythicalManMonth,cleanCode);
        Map<Year, List<Book>> booksByPublicationYear = shelf.groupByPublicationYear();

        assertThat(booksByPublicationYear)
                .containsKey(Year.of(2008))
                .containsValues(Arrays.asList(effectiveJava,cleanCode));

        assertThat(booksByPublicationYear)
                .containsKey(Year.of(2004))
                .containsValues(Arrays.asList(codeComplete));

        assertThat(booksByPublicationYear)
                .containsKey(Year.of(1975))
                .containsValues(Arrays.asList(mythicalManMonth));



    }

    @Disabled("Needs refactoring")
    @Test
    @DisplayName("books inside bookshelf are grouped according to user provided creteria (group by author name)")
    void groupBooksByUserProvidedCriteria(){
        shelf.add(effectiveJava,codeComplete,mythicalManMonth,cleanCode);
        Map<String,List<Book>> booksByAuthor = shelf.groupBy(Book::getAuthor);

        assertThat(booksByAuthor)
                .containsKey("Joshua Bloch")
                .containsValues(singletonList(effectiveJava));
        assertThat(booksByAuthor)
                .containsKey("Steve McConnel")
                .containsValues(singletonList(codeComplete));
        assertThat(booksByAuthor)
                .containsKey("Frederick Phillips Brooks")
                .containsValues(singletonList(mythicalManMonth));
        assertThat(booksByAuthor)
                .containsKey("Robert C. Martin")
                .containsValues(singletonList(cleanCode));
    }

}
