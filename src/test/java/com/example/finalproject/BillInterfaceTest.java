package com.example.finalproject;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BillInterfaceTest {

    @Test
    void testAddBook() {
        TestBillImplementation testBill = new TestBillImplementation();

        Book testBook = new Book("1234567890", "Test Book", "Test Category", "Test Supplier",
                LocalDate.now(), 10.0, 15.0, 20.0, "Test Author", 5);

        testBill.addBook(testBook, 3);

        assertEquals(3, testBill.getAddedQuantity());
        assertEquals(testBook, testBill.getAddedBook());
    }

}

class TestBillImplementation implements Bill_Interface {
    private Book addedBook;
    private int addedQuantity;

    @Override
    public void addBook(Book book, int quantity) {
        this.addedBook = book;
        this.addedQuantity = quantity;
    }

    @Override
    public int getBillNo() {
        return 0;
    }

    @Override
    public void generateBillWithoutWriting() {
    }

    public Book getAddedBook() {
        return addedBook;
    }

    public int getAddedQuantity() {
        return addedQuantity;
    }
}
