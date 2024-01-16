package com.example.finalProject.Controller;

import com.example.finalProject.Model.ManagerModel;
import com.example.finalProject.View.Manager.ManagerView;
import com.example.finalProject.View.Manager.NewCategoryManagerView;
import com.example.finalProject.View.Manager.SupplyManagerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class NewCategoryManagerController {

    ManagerModel managerModel;
    ManagerView managerView;
    SupplyManagerController supplyManagerController;
    SupplyManagerView supplyManagerView;
    NewCategoryManagerView view;
    NewCategoryManagerController controller;

   // AddStockManagerView addStockManagerView;

    public NewCategoryManagerController(SupplyManagerView supplyManagerView, SupplyManagerController supplyManagerController){
        this.supplyManagerView = supplyManagerView;

        this.supplyManagerController = supplyManagerController;

        // this.managerModel = new ManagerModel();
        this.view = new NewCategoryManagerView(supplyManagerView,supplyManagerView.getCategories());
        this.controller = this;
        initNewCategoryButtonAction();
    }

    private void initNewCategoryButtonAction() {
//        supplyManagerView.getBttNewCategory().setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                handleNewCategory();
//            }
//        });
        supplyManagerView.getBttNewCategory().setOnAction(event->handleNewCategory());
    }

    public void handleNewCategory(){
        BorderPane supplyPage = view.createNewCategory(); // Create the supply view content

        Stage currentStage = (Stage) supplyManagerView.getBttNewCategory().getScene().getWindow();
        currentStage.setScene(new Scene(supplyPage, 800, 600));
        currentStage.show();

    }
}
