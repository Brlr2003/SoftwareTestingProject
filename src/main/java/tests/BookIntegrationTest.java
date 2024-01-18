package tests;
import application.bookstore.models.Author;
import application.bookstore.models.Book;
import application.bookstore.models.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BookIntegrationTest {

    private final String testFilePath = "data/test_books.ser";
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
        // Clean up the test file after Each test
        if (testFile.exists()) {
            assertTrue(testFile.delete());
        }
    }

    @Test
    public void testBookSaveAndRetrieve() {
        // Create a Book and save it to the File
        Author author = new Author("Jane", "Doe");
        Book book = new Book("1234567890", "Test Book", 100.0f, 150.0f, author, 10, Category.Fantasy, "Supplier");

        // Save the book and check if save was successful
        assertTrue(book.saveInFile(), "Failed to save the book");

        // retrieve the books from the test file
        ArrayList<Book> books = Book.getBooks();

        // Assert that the retrieved books list is not null
        assertNotNull(books, "Retrieved books list is null");

        // Assert that the retrieved books list is not empty
        assertFalse(books.isEmpty(), "Retrieved books list is empty");

        // Assert that the saved book is in the retrieved list
        assertTrue(books.contains(book), "Saved book is not in the retrieved list");



    }


}
