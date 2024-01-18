package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.bookstore.models.Order;

class TestOrder {

	@Test
	void test_order_constructor() {
		String isbn = "978-93-95055-02-6";
		String clientName = "Omar Alsafarti";
		float price = 400;
		int quantity = 6;
		float total = 2400;
		Order order = new Order(isbn, clientName, quantity, price, total);
	    assertNotNull(order);
	}
	
	@Test
	void test_getTotalOfOrderProperly() throws Exception {
		float price = 700;
		int quantity = 7;
		float total = Order.getTotal(price,quantity);
		assertEquals(total,4900);
	}
	
	@Test
	void test_getTotalOfOrderNotProperlyException1() {
		float price = -700;
		int quantity = 5;
		 try {
		        fail();
		    } catch (Exception e) {
		        assertEquals(e.getMessage(), "Price cannot be negative");
		   
	}
	}
	
	
	
	@Test
	void test_getTotalOfOrderNotProperlyException2() {
		float price = 800;
		int quantity = -9;
		 try {
		        fail();
		    } catch (Exception e) {
		        assertEquals(e.getMessage(), "Quantity cannot be negative");
		   
	}
	}

}
