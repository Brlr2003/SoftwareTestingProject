package tests;
import application.bookstore.models.Author;
import application.bookstore.models.Book;
import application.bookstore.models.BookSold;
import application.bookstore.models.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;

public class BookSoldIntegrationTest {

    private final String testFilePath = "data/test_books_sold.ser";
    private final File testFile = new File(testFilePath);


    @BeforeEach
    public void setUp() {
        // Clean up the test file before each test
        if (testFile.exists()) {
            assertTrue(testFile.delete());
        }
    }

    @AfterEach
    public void tearDown() {
        // Clean up the test file after each test
        if (testFile.exists()) {
            assertTrue(testFile.delete());
        }
    }

    @Test
    public void testBookSoldIntegration() {


        // Create a Book and a BookSold instance
        Author author = new Author("John", "Doe");
        Book book = new Book("9876543210", "Test Book Sold", 80.0f, 120.0f, author, 15, Category.Fantasy, "Supplier");
        BookSold bookSold = new BookSold(book, 5);

        // Assert that BookSold instance is not null
        assertNotNull(bookSold);


    }


}
