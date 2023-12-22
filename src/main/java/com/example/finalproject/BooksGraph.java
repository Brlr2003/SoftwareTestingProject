package com.example.finalproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class BooksGraph extends Application {
    static UserController users = new UserController();
    static BookController book = new BookController();
    static ArrayList<Book> books;

    @Override public void start(Stage stage) {
        readFromUsers();
        stage.setTitle("Bar Chart Sample");

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Books Summary");
        xAxis.setLabel("Books");
        yAxis.setLabel("Value");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Revenue / 100");

        for(int i=0; i<users.getUsers().size(); i++){
            series1.getData().add(new XYChart.Data(users.getUsers().get(i).getFirstname(), (users.getUsers().get(i).getTotalBills())/100));
        }

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Nr. of Books Sold");
        for(int i=0; i<users.getUsers().size(); i++){
            series2.getData().add(new XYChart.Data(users.getUsers().get(i).getFirstname(), users.getUsers().get(i).getBooksSold()));
        }


        bc.getData().addAll(series1, series2);
        Scene scene  = new Scene(bc,800,600);
        stage.setScene(scene);
        stage.show();
    }

    private void readFromUsers() {

        try{
            File file = new File("src/main/resources/users.dat");
            file.createNewFile();
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
            users.setUsers((ArrayList<User>)input.readObject());
        }
        catch(IOException ignored){
            System.out.println("2. " + ignored);
        }catch(ClassNotFoundException e1){
            System.out.println("3. " + e1);
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}