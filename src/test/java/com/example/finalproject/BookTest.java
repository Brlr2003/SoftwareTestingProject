package com.example.finalproject;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    @Test
    public void testBookToString() {
        Book book = new Book(
                "1234567890",
                "Test Book",
                "Fiction",
                "Test Supplier",
                LocalDate.of(2023, 1, 1),
                20.0,
                25.0,
                30.0,
                "Test Author",
                50
        );

        String expectedToString = "ISBN: 1234567890\n" +
                "Title: Test Book\n" +
                "Category: Fiction\n" +
                "Supplier: Test Supplier\n" +
                "Purchased Date: 2023-01-01\n" +
                "Purchased Price: 20.0\n" +
                "Original Price: 25.0\n" +
                "Selling Price: 30.0\n" +
                "Author: Test Author\n" +
                "Stock: 50\n";

        assertEquals(expectedToString, book.toString());
    }

    @Test
    public void testBookSetLocalDate() {
        Book book = new Book();
        LocalDate testDate = LocalDate.of(2023, 12, 31);

        book.setLocalDate(testDate);

        assertEquals(testDate, book.getPurchasedDate());
    }

}
