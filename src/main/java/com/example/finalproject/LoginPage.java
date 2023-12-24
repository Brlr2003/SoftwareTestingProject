package com.example.finalproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;


public class LoginPage extends Application {

    static UserController users = new UserController();
    public static String username, password;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage loginPageStage) {
        readFromUsers();

        loginPageStage.setTitle("Bookstore Management System - Login");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome to Bookstore Management System");
        scenetitle.setId("welcome-text");
        grid.add(scenetitle, 0, 0, 3, 1);

        Label usernameField = new Label("Username:");
        grid.add(usernameField, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 1, 2);

        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                username = userTextField.getText();
                password = passwordField.getText();
                checkCredentials(btn);
            }
        });

        Scene scene = new Scene(grid, 600, 400);
        loginPageStage.setScene(scene);
        loginPageStage.setTitle("Library Manegement System - Login Page");
        loginPageStage.show();
    }

    void checkCredentials(Button btn) {
        readFromUsers();
        ArrayList<User> user = users.getUsers();
        boolean check = false;

        String role = "";
        if(username.equals("admin") && password.equals("admin")) {
            role = "administrator";
            check = true;
        }
        else{
            for(int i=0; i<user.size(); i++){
                if(username.equals(user.get(i).getUsername()) && password.equals(user.get(i).getPassword()) && user.get(i).getAccessLevel().equals("Librarian")) {
                    role = "librarian";
                    check = true;
                    break;
                } else if(username.equals(user.get(i).getUsername()) && password.equals(user.get(i).getPassword()) && user.get(i).getAccessLevel().equals("Manager")) {
                    role = "manager";
                    check = true;
                    break;
                }
            }
        }

        if(!check){
            showError("Invalid username or password. Please try again.");
        }
        switch (role) {
            case "librarian":
                loadLibrarianInterface(btn);
                break;
            case "manager":
                loadManagerInterface(btn);
                break;
            case "administrator":
                loadAdministratorInterface(btn);
                break;
        }
    }

    private void loadLibrarianInterface(Button btn){
        LibrarianInterface librarianInterface = new LibrarianInterface();
        Stage stage = (Stage) btn.getScene().getWindow();
        librarianInterface.start(stage);
    }
    private void loadManagerInterface(Button btn) {
        ManagerInterface managerInterface = new ManagerInterface();
        Stage stage = (Stage) btn.getScene().getWindow();
        managerInterface.start(stage);
    }
    private void loadAdministratorInterface(Button btn) {
        AdministratorInterface administratorInterface = new AdministratorInterface();
        Stage stage = (Stage) btn.getScene().getWindow();
        administratorInterface.start(stage);
    }

    private void showError(String errorMessage) {
        Label errorLabel = new Label(errorMessage);
        errorLabel.setTextFill(Color.RED);
        errorLabel.setPadding(new Insets(10));
        errorLabel.setAlignment(Pos.CENTER);

        StackPane errorPane = new StackPane(errorLabel);
        errorPane.setPrefSize(300, 100);

        Scene errorScene = new Scene(errorPane);

        Stage errorStage = new Stage();
        errorStage.setScene(errorScene);
        errorStage.initModality(Modality.APPLICATION_MODAL);
        errorStage.show();
    }

    public static void writeToUsers(){
        try{
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/main/resources/users.dat"));
            output.writeObject(users.getUsers());
            output.close();
        }catch(IOException e){
            System.out.println("1. " + e);
        }
    }

    //read the input from the file
    public static void readFromUsers(){
        try{
            File file = new File("src/main/resources/users.dat");
            file.createNewFile();
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
            users.setUsers((ArrayList<User>)input.readObject());
        }catch(IOException ignored){
            System.out.println("2. " + ignored);
        }catch(ClassNotFoundException e1){
            System.out.println("3. " + e1);
        }
    }



}

