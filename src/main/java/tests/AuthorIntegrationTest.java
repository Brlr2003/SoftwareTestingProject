package tests;

import application.bookstore.models.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorIntegrationTest {

    private final String testFilePath = "data/test_authors.ser";
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
    public void testAuthorSaveAndRetrieve() {
        // Create an Author and save it to the test file
        Author author = new Author("John", "Doe");
        assertTrue(author.saveInFile());

        // Retrieve the authors from the test file
        ArrayList<Author> authors = Author.getAuthors();

        // Assert that the retrieved authors list is not null and contains the saved author
        assertNotNull(authors);
        assertEquals(1, authors.size());

        // Assert that the retrieved author has the same attributes as the saved author
        Author retrievedAuthor = authors.get(0);
        assertEquals(author.getFirstName(), retrievedAuthor.getFirstName());
        assertEquals(author.getLastName(), retrievedAuthor.getLastName());
    }

}
