package application.bookstore.views;

import javafx.geometry.Insets;


import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.FontWeight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import application.bookstore.models.Role;

public class LoginView extends View{
    private final BorderPane borderPane = new BorderPane();

    private final TextField usernameField = new TextField();
    
    private final ObservableList<Role> items = FXCollections.observableArrayList( Role.values());
    
    private final ComboBox<Role> cbxStatus = new ComboBox<>();
    

    private final PasswordField passwordField = new PasswordField();
    private final Button login = new Button("Login");
    private final Button cancel = new Button("Cancel");
    private final Label errorLabel = new Label("");
    public TextField getUsernameField() {
        return usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Button getLogin() {
        return login;
    }
    
    public Button getCancel() {
        return cancel;
    } 
    
    public Label getErrorLabel() {
        return errorLabel;
    }
    public LoginView() {
        setView();
    }
    
    
    private static final Role roleSelected = Role.ADMIN;
  

    public static Role getRoleSelected() {
		return roleSelected;
	}
 

	private void setView() {
    	Label usernameLabel = new Label("Username   ");
        Label passwordLabel = new Label("Password    ");
       // Label roleLabel = new Label("Role   ");
        HBox hb2 = new HBox();
        HBox hb3 = new HBox();
        HBox hb4 = new HBox();
        hb2.getChildren().addAll(usernameLabel,usernameField);
        hb3.getChildren().addAll(passwordLabel,passwordField);
        VBox vBox = new VBox();
        Label loginLabel = new Label("WELCOME TO OUR BOOKSTORE");
        loginLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		HBox hb = new HBox(login, cancel);
		loginLabel.setTextFill(Color.BEIGE); 
		hb.setPadding(new Insets(0, 50, 0, 100));
		hb.setMargin(login, new Insets(0, 10, 0, 0));
		usernameLabel.setFont(Font.font("verdana", FontPosture.REGULAR, 14));
		passwordLabel.setFont(Font.font("verdana", FontPosture.REGULAR, 14));
		//cbxStatus.setItems( FXCollections.observableArrayList( Role.values()));
		//hb4.getChildren().addAll(roleLabel,cbxStatus);
	
		login.setStyle("-fx-background-color: Lightgreen");
		cancel.setStyle("-fx-background-color: Lightblue");
		hb.setAlignment(Pos.CENTER_LEFT);
        vBox.getChildren().addAll(loginLabel, hb2, hb3 , hb4, hb, errorLabel);
        hb4.setSpacing(30);
        // set background
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setPadding(new Insets(0,0,0,100));
        vBox.setSpacing(20);
        borderPane.setCenter(vBox);
        
        Image image = new Image("https://media.istockphoto.com/photos/school-books-stacked-on-desk-with-chalkboard-picture-id583709876?k=20&m=583709876&s=612x612&w=0&h=j4VWqeJ_ufv8kwaSZud4crhMDXRyEStP-RIMZH2vdJY=",600, 300, false, false);


        BackgroundImage backgroundimage = new BackgroundImage(image, 
                                         BackgroundRepeat.NO_REPEAT, 
                                         BackgroundRepeat.NO_REPEAT, 
                                         BackgroundPosition.DEFAULT, 
                                            BackgroundSize.DEFAULT);
        // create Background
        Background background = new Background(backgroundimage);
        vBox.setBackground(background);
        
    }

    @Override
    public Parent getView() {
        return borderPane;
    }
}