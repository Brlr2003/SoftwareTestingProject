package com.example.finalproject;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class BillTest {

    @Test
    void addBook_shouldAddBookToBill() {
        Bill bill = new Bill();
        Book book = new Book("1234567890", "The Great Gatsby", "Fiction", "Book Supplier",
                LocalDate.now(), 10.0, 15.0, 20.0, "F. Scott Fitzgerald", 5);

        bill.addBook(book, 2);

        assertEquals(1, bill.getQuantities().size());
        assertEquals(2, bill.getQuantities().get(0));
        assertEquals(3, book.getStock());
    }

    @Test
    void generateBillWithoutWriting_shouldResetDataWithoutWritingToFile() {
        Bill bill = new Bill();
        Book book1 = new Book("1111111111", "Book1", "Genre1", "Supplier1",
                LocalDate.now(), 10.0, 15.0, 20.0, "Author1", 5);
        Book book2 = new Book("2222222222", "Book2", "Genre2", "Supplier2",
                LocalDate.now(), 8.0, 12.0, 18.0, "Author2", 3);

        bill.addBook(book1, 2);
        bill.addBook(book2, 1);

        bill.generateBillWithoutWriting();

        assertEquals(0, bill.getQuantities().size());
        assertEquals(3, bill.getBooksSold());
    }

    @Test
    void removeBook_shouldRemoveBookFromBill() {
        Bill bill = new Bill();
        Book bookToRemove = new Book("1112223334", "To Be Removed", "Mystery", "Another Supplier",
                LocalDate.now(), 8.0, 12.0, 18.0, "John Doe", 5);

        bill.addBook(bookToRemove, 2);
        bill.removeBook(bookToRemove);

        assertEquals(0, bill.getQuantities().size());
        assertEquals(3, bookToRemove.getStock());
    }

    @Test
    void generateBill_shouldWriteToFileAndResetData() {
        Bill bill = new Bill();
        Book book1 = new Book("1111111111", "Book1", "Genre1", "Supplier1",
                LocalDate.now(), 10.0, 15.0, 20.0, "Author1", 5);
        Book book2 = new Book("2222222222", "Book2", "Genre2", "Supplier2",
                LocalDate.now(), 8.0, 12.0, 18.0, "Author2", 3);

        bill.addBook(book1, 2);
        bill.addBook(book2, 1);

        bill.generateBill();

        assertEquals(0, bill.getQuantities().size());
        assertEquals(3, bill.getBooksSold());
    }
}
