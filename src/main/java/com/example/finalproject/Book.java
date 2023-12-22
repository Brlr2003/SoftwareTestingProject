package com.example.finalproject;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
public class Book implements Serializable {
    @Serial
    private static final long SerialVersionUID = 10l;
    private String ISBN;
    private String title;
    private String category;
    private String supplier;
    private LocalDate purchasedDate;
    private double purchasedPrice;
    private double originalPrice;
    private double sellingPrice;
    private String author;
    private int stock;

    public Book(){

    }
    public Book(String ISBN, String title, String category, String supplier, LocalDate purchasedDate,
                double purchasedPrice, double originalPrice, double sellingPrice, String author, int stock) {
        this.ISBN = ISBN;
        this.title = title;
        this.category = category;
        this.supplier = supplier;
        this.purchasedDate = purchasedDate;
        this.purchasedPrice = purchasedPrice;
        this.originalPrice = originalPrice;
        this.sellingPrice = sellingPrice;
        this.author = author;
        this.stock = stock;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public LocalDate getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(LocalDate purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public double getPurchasedPrice() {
        return purchasedPrice;
    }

    public void setPurchasedPrice(double purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String toString() {
        return "ISBN: " + ISBN +
                "\nTitle: " + title +
                "\nCategory: " + category +
                "\nSupplier: " + supplier +
                "\nPurchased Date: " + purchasedDate +
                "\nPurchased Price: " + purchasedPrice +
                "\nOriginal Price: " + originalPrice +
                "\nSelling Price: " + sellingPrice +
                "\nAuthor: " + author +
                "\nStock: " + stock + "\n";
    }

    public void setLocalDate(LocalDate date) {
        purchasedDate = date;
    }
}
