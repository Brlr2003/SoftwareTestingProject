package com.example.finalProject.Controller.Mockers;

import com.example.finalProject.Model.BookModel;

import java.util.ArrayList;

public interface StockBookRepository {
    ArrayList<BookModel> getStockBooks();
}