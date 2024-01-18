package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.bookstore.models.Author;
import application.bookstore.models.Book;
import application.bookstore.models.BookSold;
import application.bookstore.models.Category;

class TestBookSold {

	@Test
	void test_total_price() {
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
		BookSold book_sold = new BookSold(book,5);
		assertEquals(book_sold.getTotalPrice(),3350);
	}
	
	@Test
	void test_total_price_false() {
		String isbn = "978-93-95055-02-5";
		String title = "Title1";
		float purchasedPrice = 500;
		float sellingPrice = 800;
		int stock = 7;
		Category category = Category.Adventure;
		Author author = new Author("Author1","Author1");
		String supplier = "Supplier1";
		Book book = new Book(isbn, title, purchasedPrice, sellingPrice, author, stock,
			category, supplier);
		BookSold book_sold = new BookSold(book,3);
		assertNotEquals(book_sold.getTotalPrice(),1500);
	}
	
	

}
