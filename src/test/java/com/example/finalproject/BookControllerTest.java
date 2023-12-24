package com.example.finalproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

public class BookControllerTest {

    private BookController bookController;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        bookController = new BookController();
    }

    @Test
    void testCheckerWithValidData() {
        assertTrue(bookController.checker("1234567890", "Test Book", "Fiction", "Supplier1", LocalDate.now(), 20.0, 15.0, 25.0, "John Doe", 50));
        assertEquals(1, bookController.getBooks().size());
    }

    @Test
    void testCheckerWithInvalidData() {
        assertFalse(bookController.checker("", "Test Book", "Fiction", "Supplier1", LocalDate.now(), 20.0, 15.0, 25.0, "John Doe", 50));
        assertEquals(0, bookController.getBooks().size());
    }

    @Test
    void testPrintBooks() {
        System.setOut(new PrintStream(outContent));

        bookController.checker("1234567890", "Test Book", "Fiction", "Supplier1", LocalDate.now(), 20.0, 15.0, 25.0, "John Doe", 50);
        bookController.printBooks();

        assertTrue(outContent.toString().contains("Hello World!"));
        assertTrue(outContent.toString().contains("ISBN: 1234567890"));
        assertTrue(outContent.toString().contains("Title: Test Book"));

        System.setOut(originalOut);
    }
}
