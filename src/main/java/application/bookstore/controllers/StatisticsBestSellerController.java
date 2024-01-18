package application.bookstore.controllers;

import application.bookstore.models.Author;

import application.bookstore.views.AuthorView;
import application.bookstore.views.StaticsticBestSellerCategory;
import application.bookstore.views.StatisticView;
import javafx.collections.FXCollections;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class StatisticsBestSellerController {
	 private StaticsticBestSellerCategory statsView;
	    public StatisticsBestSellerController(StaticsticBestSellerCategory statsView) {
	        this.statsView = statsView;
	    }
}
