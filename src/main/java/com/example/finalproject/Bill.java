package com.example.finalproject;

import javafx.application.Application;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Bill implements Serializable, Bill_Interface{
    private static final long SerialVersionUID = 10l;
    private int billNo;
    private static int billCounter = 0;
    private ArrayList<Integer> quantities = new ArrayList<>();
    private ArrayList<Book> books = new ArrayList<>();
    private double totalAmount;
    private int booksSold = 0;

    public Bill() {
    }

    @Override
    public void addBook(Book book, int quantity) {
        boolean bookExists = false;
        int quant;
        booksSold += quantity;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getISBN().equals(book.getISBN()) && quantity <= (book.getStock())) {
                quant = quantity + quantities.get(i);
                quantities.set(i,quant);
                books.get(i).setStock(books.get(i).getStock() - quantity);
                totalAmount += books.get(i).getSellingPrice() * quantity;
                bookExists = true;
                break;
            }

        }

        if (!bookExists && book.getStock()>=quantity) {
            books.add(book);
            quantities.add(quantity);
            book.setStock(book.getStock()-quantity);
            totalAmount += book.getSellingPrice() * quantity;
        } else if (book.getStock() < 0) {
            LibrarianInterface.showErrorMessage("This book is out of stock.");
        }else if(!bookExists && quantity>book.getStock()){
            LibrarianInterface.showErrorMessage("The quantity asked exceeds the stock");
        }

    }
    public ArrayList<Integer> getQuantities() {
        return quantities;
    }
    @Override
    public int getBillNo() {
        return billNo;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
    public void generateBill() {
        if(books.size()!=0){
            writeToFile();
        }
        totalAmount = 0;
        quantities.clear();
        books.clear();
    }
    @Override
    public void generateBillWithoutWriting() {
        totalAmount = 0;
        quantities.clear();
        books.clear();
    }
    public void removeBook(Book book) {
        if (!this.books.contains(book)) {
            // show error message: book not found in bill
            return;
        }
        this.books.remove(book);
        this.totalAmount -= book.getSellingPrice();
    }

    private String generateBillNumber() {
        return "Bill" + (++billCounter);
    }
    private Book getBook(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                return book;
            }
        }
        return null;
    }
    private void updateStock(String ISBN, int quantity) {
        // Get the book with the specified ISBN
        Book book = getBook(ISBN);

        // Check if the book exists
        if (book == null) {
            // Show an error message if the book does not exist
            LibrarianInterface.showErrorMessage("Book with ISBN " + ISBN + " does not exist.");
            return;
        }

        // Check if there is enough stock for the specified quantity
        if (book.getStock() < quantity) {
            // Show an error message if there is not enough stock
            LibrarianInterface.showErrorMessage("Not enough stock for book with ISBN " + ISBN + ".");
            return;
        }

        // Update the stock for the book
        book.setStock(book.getStock() - quantity);
    }

    public void writeToFile() {
        try {
            File file = new File("src/main/resources/Output/" + billNo + ".txt");
            FileWriter writer = new FileWriter(file);
            writer.write("Bill Number: " + billNo + "\n");
            writer.write("Date: " + LocalDate.now() + "\n");
            writer.write("Books: \n");
            for (int i=0; i<books.size(); i++) {
                writer.write("  ISBN: " + books.get(i).getISBN() + "\n");
                writer.write("  Title: " + books.get(i).getTitle() + "\n");
                writer.write("  Quantity: " + quantities.get(i) + "\n");
                writer.write("  Price: $" + books.get(i).getSellingPrice() + "\n");
            }
            writer.write("Total Amount: $" + totalAmount + "\n");
            writer.close();
            System.out.println("Bill written to file " + billNo + ".txt");
            billNo++;
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    public void readFromFile(String billNumber) {
        try {
            File file = new File(billNumber + ".txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] details = line.split(",");
                String ISBN = details[0];
                int quantity = Integer.parseInt(details[1]);
                Book book = getBook(ISBN);
                if (book == null) {
                    throw new Exception("Book with ISBN " + ISBN + " not found.");
                }
                addBook(book, quantity);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public int getBooksSold() {
        return booksSold;
    }

}