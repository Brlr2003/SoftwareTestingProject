package com.example.finalproject;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class LibrarianInterface extends Application {
    private static final String INPUT_FILE = "src/main/resources/input/input.txt";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
    private static final Locale LOCALE = Locale.ENGLISH;
    private Book selectedBook;
    private static BookController book = new BookController();
    static UserController users = new UserController();
    private TextField ISBNField;
    private TextField quantityField = new TextField();
    private static Bill bill;
    private static TableView<Book> tableView;
    private Label statusLabel;
    private ObservableList<Book> data;
    private boolean isClicked = false;
    public static void main(String[] args) {
        launch(args);
    }

    private Button addToBillButton = new Button("Add to bill");
    private Button checkOutButton = new Button("Check out");
    private Button logOutButton = new Button("Log Out");

    private static double totalAmount = 0;
    private static int booksSold = 0;

    @Override
    public void start(Stage stage) {
        read();
        tableView = new TableView<>();
        tableView.setEditable(true);
        initializeTableColumns(tableView);
        addListeners(tableView);
        tableView.setItems(getBooks());

        Label quantityLabel = new Label("Quantity: ");
        Label success = new Label("");
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(tableView,quantityLabel,quantityField, addToBillButton, checkOutButton, success, logOutButton);

        Scene scene = new Scene(vbox, 500, 500);
        stage.setScene(scene);
        stage.show();

        bill = new Bill();
        addToBillButton.setOnAction(event -> {
            isClicked=true;
            try {
                Book selectedBook = getSelectedBook();
                int quantity = Integer.parseInt(quantityField.getText());
                bill.addBook(selectedBook, quantity);
                totalAmount = bill.getTotalAmount();
                booksSold = bill.getBooksSold();
                success.setText("Added to the bill!");
                updateTableView(tableView);

            }catch (NumberFormatException e){
                showErrorMessage("Add a quantity (integers).");
            }catch (NullPointerException e1){
                showErrorMessage("Choose a book.");
            }
        });
        checkOutButton.setOnAction(event -> {
            if(isClicked){
                success.setText("Checking out...");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Please confirm.");
                alert.setHeaderText("Are you sure you want to check out?");

                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    readFromUsers();
                    for(int i=0; i<users.getUsers().size(); i++){
                        if(LoginPage.username.equals(users.getUsers().get(i).getUsername()) &&
                                LoginPage.password.equals(users.getUsers().get(i).getPassword())){
                            users.getUsers().get(i).setTotalBills(users.getUsers().get(i).getTotalBills()+totalAmount);
                            users.getUsers().get(i).setBooksSold(users.getUsers().get(i).getBooksSold()+booksSold);
                            users.getUsers().get(i).addToDates(LocalDate.now());
                            users.getUsers().get(i).addToTotalProfit(totalAmount);
                            writeToUsers();
                        }
                    }
                    showConfirmationMessage();
                    success.setText("Checked out!");
                }
                isClicked=false;
            }
            else {
                showErrorMessage("Add to bill first");
            }
        });
        logOutButton.setOnAction(event -> {
            LoginPage li = new LoginPage();
            li.start(stage);
            System.out.println("LOG OUT:\n");
            users.printUsers();
            System.out.println("\n\n\n");
        });
    }
    public static ObservableList<Book> getBooks() {
        return FXCollections.observableList(book.getBooks());
    }


    public static void setTableData(ObservableList<Book> data, TableView<Book> tableView) {
        tableView.setItems(data);
    }
    private void checkOut(){
        String ISBN = ISBNField.getText();
        String stringQuantity = quantityField.getText();
        if(ISBN.isEmpty() || stringQuantity.isEmpty()){
            statusLabel.setText("You have to fill in the blanks.");
        }
        try{
            int quantity = Integer.parseInt(quantityField.getText());
            statusLabel.setText("Checkout successful!");
        }
        catch(NumberFormatException nfe){
            statusLabel.setText("Fill it only with numbers.");
        }
    }

    public static void write(){
        try{
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/main/resources/history.dat"));
            output.writeObject(book.getBooks());
            output.close();
        }catch(IOException e){
            System.out.println("1. " + e);
        }
    }

    public static void read(){
        try{
            File file = new File("src/main/resources/history.dat");
            file.createNewFile();
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
            book.setBooks((ArrayList<Book>)input.readObject());
        }catch(IOException ignored){
            System.out.println("2. " + ignored);
        }catch(ClassNotFoundException e1){
            System.out.println("3. " + e1);
        }
    }
    private void initializeTableColumns(TableView<Book> tableView) {
        TableColumn<Book, String> isbn = new TableColumn<Book, String>("ISBN");
        isbn.setCellValueFactory(new PropertyValueFactory<Book, String>("ISBN"));
        isbn.setPrefWidth(95);

        TableColumn<Book, String> title = new TableColumn<Book, String>("Title");
        title.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        title.setPrefWidth(95);

        TableColumn<Book, String> author = new TableColumn<Book, String>("Author");
        author.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        author.setPrefWidth(95);

        TableColumn<Book, String> price = new TableColumn<Book, String>("Price");
        price.setCellValueFactory(new PropertyValueFactory<Book, String>("sellingPrice"));
        price.setPrefWidth(95);

        TableColumn<Book, String> stock = new TableColumn<Book, String>("Stock");
        stock.setCellValueFactory(new PropertyValueFactory<Book, String>("stock"));
        stock.setPrefWidth(97);

        tableView.getColumns().addAll(isbn, title, author, price, stock);

    }
    public static Book getSelectedBook() {
        return tableView.getSelectionModel().getSelectedItem();
    }
    public static void updateTableView(TableView<Book> tableView) {
        ObservableList<Book> data = FXCollections.observableArrayList();
        ObservableList<Book> books = getBooks();
        data.addAll(books);
        setTableData(data, tableView);
    }
    private void setCellValueFactory(TableColumn<Book, String> column, String property) {
        column.setCellValueFactory(new PropertyValueFactory<>(property));
    }
    private void addListeners(TableView<Book> tableView) {
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedBook = newSelection;
            }
        });
    }
    public static void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void showConfirmationMessage() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Successfully Checked-out!");
        alert.setHeaderText("Printing the receipt.");
        alert.setContentText("You total amount is: " + totalAmount + "\nDo you want to print a receipt?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            bill.generateBill();
        }
        else {
            bill.generateBillWithoutWriting();
        }
        write();
        read();
        updateTableView(tableView);
    }

    private void writeToUsers(){
        try{
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/main/resources/users.dat"));
            output.writeObject(users.getUsers());
            output.close();
        }catch(IOException e){
            System.out.println("1. " + e);
        }
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

}

