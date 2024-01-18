package application.bookstore.views;


import java.util.ArrayList;

import application.bookstore.models.Role;
import application.bookstore.controllers.AuthorController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import application.bookstore.controllers.HomeController;
import application.bookstore.models.Author;
import application.bookstore.models.Role;
import application.bookstore.models.User;
import application.bookstore.ui.CreateButton;
import application.bookstore.ui.DeleteButton;
import application.bookstore.ui.EditButton;


public class HomeView extends View{
	
	
private final BorderPane borderPane = new BorderPane();
//private final Label resultLabel = new Label("");
private final TabPane tabPane = new TabPane();
private final Tab bookListT = new Tab("Book List");
public Button getbList() {
	return bList;
}


public Button getaList() {
	return aList;
}


public Button getUsersBtn() {
	return usersBtn;
}


public Button getbSold() {
	return bSold;
}


private final Button bList = new Button("Book List");
private final Button aList = new Button("Author List");
private final Button usersBtn = new Button("Users List");
private final Button bSold = new Button("Books Sold");



public HomeView() {
    new HomeController(this);
}


@Override
public Parent getView() {
	//panes.getStyleClass().add("titledpane");
	
	/*MenuBar menuBar1 = new MenuBar();
	VBox userButtons = new VBox();
	userButtons.getStyleClass().add("bluebox");
	Button register = new Button("Register User");
	Button view = new Button("View Users");
	userButtons.getChildren().addAll(register, view);
	
	MenuBar menuBar = new MenuBar();
    Menu menu1 = new Menu("Users");
    Menu menu2 = new Menu("Edit");
    Menu menu3 = new Menu("Change Password");
    menuBar.getMenus().addAll(menu1,menu2,menu3);
    MenuItem sub1 = new MenuItem("Open");
    MenuItem sub2 = new MenuItem("Close");
    //Adding sub items to the edit
    menu1.getItems().addAll(sub1, sub2);*/
	HBox buttonList = new HBox();
    
    Font font = Font.font("Courier New", FontWeight.BOLD, 25);
    bList.setFont(font);
    aList.setFont(font);
    bSold.setFont(font);
    usersBtn.setFont(font);
    buttonList.getChildren().addAll(bList,aList,bSold);
    buttonList.setSpacing(60);
    
    
    
    
    borderPane.setTop(tabPane);
    buttonList.setPadding(new Insets(40, 100, 0, 100));
    buttonList.setAlignment(Pos.CENTER);
    HBox hbox2 = new HBox();
    Image image1 = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Closed_Book_Icon.svg/2048px-Closed_Book_Icon.svg.png", 200,200 , false, false);
    Image image2 = new Image("https://cdn2.iconfinder.com/data/icons/flat-education-icons-5/96/Writing-512.png", 200, 200, false, false);
    Image image3 = new Image("https://www.kindpng.com/picc/m/45-456813_android-cart-icon-png-transparent-png.png", 200, 200, false, false);
    Image image4 = new Image("https://media.istockphoto.com/vectors/user-icon-flat-isolated-on-white-background-user-symbol-vector-vector-id1300845620?k=20&m=1300845620&s=170667a&w=0&h=ipUh1s5ULxhCBdm5LyFb5ISrUYZqEVTiu_HTX3qWM4Y=", 250, 250, false, false);
    ImageView i1= new ImageView(image1);
    ImageView i2= new ImageView(image2);
    ImageView i3= new ImageView(image3);
    ImageView i4= new ImageView(image4);
    hbox2.getChildren().addAll(i1,i2,i3);
    hbox2.setSpacing(40);
   /* Role currentRole = Role.ADMIN;
        if (currentRole == Role.ADMIN) {
        	hbox2.getChildren().add(i4);
        	buttonList.getChildren().add(usersBtn);
        	System.out.println("I'm USER");
        }*/
    hbox2.setAlignment(Pos.TOP_CENTER);
    hbox2.setPadding(new Insets(200, 60, 0, 90));
    borderPane.setCenter(hbox2);
    borderPane.setBottom(buttonList);

    return borderPane;
}

}