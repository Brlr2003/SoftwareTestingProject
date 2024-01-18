package tests;

import application.bookstore.models.Author;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestAuthor {

	@Test
	void testCreateAuthor() {
		Author author = new Author("John", "Doe");
		assertNotNull(author);
		assertEquals("John", author.getFirstName());
		assertEquals("Doe", author.getLastName());
	}

	@Test
	void testAuthorFullName() {
		Author author = new Author("Jane", "Smith");
		assertEquals("Jane   Smith", author.getFullName());
	}

	@Test
	void testAuthorValidation() {
		Author validAuthor = new Author("Valid", "Author");
		Author invalidAuthor = new Author("", "Invalid");

		assertTrue(validAuthor.isValid());
		assertFalse(invalidAuthor.isValid());
	}

	@Test
	void testSearchAuthors() {
		// Save a few authors to the file
		Author author1 = new Author("John", "Doe");
		Author author2 = new Author("Jane", "Smith");
		Author author3 = new Author("Alice", "Johnson");

		author1.saveInFile();
		author2.saveInFile();
		author3.saveInFile();

		// Search for authors
		ArrayList<Author> searchResults1 = Author.getSearchResults("John");
		ArrayList<Author> searchResults2 = Author.getSearchResults("Smith");

		assertEquals(2, searchResults1.size());
		assertEquals(1, searchResults2.size());
		assertTrue(searchResults1.contains(author1));
		assertTrue(searchResults2.contains(author2));
	}

}
