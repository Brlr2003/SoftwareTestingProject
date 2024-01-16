package com.example.finalProject;

import com.example.finalProject.Controller.MainController;
import com.example.finalProject.Model.AdministratorModel;
import com.example.finalProject.Model.ManagerModel;
import com.example.finalProject.View.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

//--add-opens javafx.graphics/com.sun.javafx.application=ALL-UNNAMED
public class BookstoreApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainView mainView = new MainView();

        mainView.setPrimaryStage(primaryStage);

        MainController mainController = new MainController(mainView);
        mainView.setController(mainController);
        Scene scene = new Scene(mainView.mainPage(), 800, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Bookstore Application");

        primaryStage.show();
    }

    public static void main(String[] args) {
        //fix this
        ManagerModel.InstantiateLibrarians();
        AdministratorModel.InstantiateManagers();


        launch(args);
    }

}
