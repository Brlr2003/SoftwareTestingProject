package com.example.finalproject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class BookController implements Serializable {

    private ArrayList<Book> books;

    public BookController(){
        books = new ArrayList<>();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books){
        this.books = books;
    }

    public boolean checker(String ISBN, String title, String category, String supplier, LocalDate purchasedDate, double purchasedPrice, double originalPrice, double sellingPrice, String author, int stock){
        if(ISBN.equals("") || title.equals("") || supplier.equals("") || purchasedDate.equals("") || purchasedPrice < 0 || originalPrice == 0 ||sellingPrice == 0 || author.equals("") || stock == 0){
            return false;
        }else{
            Book book = new Book(ISBN, title, category, supplier, purchasedDate, purchasedPrice, originalPrice, sellingPrice, author, stock);
            books.add(book);
            return true;
        }
    }

    public void printBooks(){
        System.out.println("Hello World!")
        ;
        for(int i=0; i<books.size(); i++){
            System.out.println(books.get(i));
        }
    }



}
