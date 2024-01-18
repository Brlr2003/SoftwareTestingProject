package tests;
import application.bookstore.models.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

public class OrderIntegrationTest {

    private final String testFilePath = "data/test_orders.ser";
    private final File testFile = new File(testFilePath);

    @BeforeEach
    public void setUp() {
        //Clean up the test file before each test
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
    public void testOrderIntegration() {

        String isbn = "978-93-95055-02-6";
        String clientName = "Omar Alsafarti";
        float price = 400;
        int quantity = 6;
        float total = 2400;
        Order order = new Order(isbn, clientName, quantity, price, total);

        assertNotNull(order);



    }


}
