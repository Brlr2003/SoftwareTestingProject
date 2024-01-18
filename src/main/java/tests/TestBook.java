package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.bookstore.models.Author;
import application.bookstore.models.Book;
import application.bookstore.models.Category;

class TestBook {

	@Test
	void test_bookConstructor() {
		String isbn = "978-93-95055-02-5";
		String title = "Title1";
		float purchasedPrice = 500;
		float sellingPrice = 670;
		int stock = 7;
		Category category = Category.Adventure;
		Author author = new Author("Author1","Author1");
		String supplier = "Supplier1";
		Book book = new Book(isbn, title, purchasedPrice, sellingPrice, author, stock,
			category, supplier);
		assertNotNull(book);
	}
	
	@Test
	void test_getProperlyISBN() {
		String isbn = "978-93-95055-02-6";
		String title = "Title2";
		float purchasedPrice = 900;
		float sellingPrice = 1200;
		int stock = 13;
		Category category = Category.Adventure;
		Author author = new Author("Author2","Author2");
		String supplier = "Supplier1";
		Book book = new Book(isbn, title, purchasedPrice, sellingPrice, author, stock,
			category, supplier);
		assertEquals(book.getIsbn(),isbn);
	}
	
	
	@Test
	void test_getProperlyAuthor() {
		String isbn = "978-93-95055-02-6";
		String title = "Title2";
		float purchasedPrice = 600;
		float sellingPrice = 1000;
		int stock = 20;
		Category category = Category.Classics;
		Author author = new Author("Author3","Author3");
		String supplier = "Supplier1";
		Book book = new Book(isbn, title, purchasedPrice, sellingPrice, author, stock,
			category, supplier);
		assertEquals(book.getAuthor(),author);
	}
	
	@Test
	void test_isbn_is_not_valid(){
		String isbn = "9355026";
		String title = "Title2";
		float purchasedPrice = 600;
		float sellingPrice = 1000;
		int stock = 20;
		Category category = Category.Classics;
		Author author = new Author("Author3","Author3");
		String supplier = "Supplier1";
		Book book = new Book(isbn, title, purchasedPrice, sellingPrice, author, stock,
			category, supplier);
		assertFalse(book.isValid());
	}
	
	
	@Test
	void test_selling_price_is_not_valid(){
		String isbn = "978-93-95055-02-6";
		String title = "Title5";
		float purchasedPrice = 400;
		float sellingPrice = -150;
		int stock = 20;
		Category category = Category.Classics;
		Author author = new Author("Author3","Author3");
		String supplier = "Supplier1";
		Book book = new Book(isbn, title, purchasedPrice, sellingPrice, author, stock,
			category, supplier);
		assertFalse(book.isValid());
	}
	
	@Test
	void test_title_supplier_not_valid(){
		String isbn = "978-93-95055-02-6";
		String title = " ";
		float purchasedPrice = 400;
		float sellingPrice = 950;
		int stock = 24;
		Category category = Category.Classics;
		Author author = new Author("Author3","Author3");
		String supplier = " ";
		Book book = new Book(isbn, title, purchasedPrice, sellingPrice, author, stock,
			category, supplier);
		assertFalse(book.isValid());
	}
	
	
	@Test
	void test_book_valid(){
		String isbn = "978-93-95055-02-5";
		String title = "Title1";
		float purchasedPrice = 500;
		float sellingPrice = 670;
		int stock = 7;
		Category category = Category.Adventure;
		Author author = new Author("Author1","Author1");
		String supplier = "Supplier1";
		Book book = new Book(isbn, title, purchasedPrice, sellingPrice, author, stock,
			category, supplier);
		assertTrue(book.isValid());

	}
	
	
	

}
