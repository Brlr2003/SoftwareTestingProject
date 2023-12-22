package com.example.finalproject;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ManagerInterface extends Application implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;
    BookController book = new BookController();
    UserController user = new UserController();
    ArrayList<User> librarians = new ArrayList<>();
    private String ISBN, title, supplier, author, category;
    private double originalPrice, sellingPrice, purchasedPrice;
    private int stock;
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<String> categoryList = new ArrayList<>();
    private static TableView<Book> tableView = new TableView<>();
    private static TableView<User> librarianTableView = new TableView<>();
    private Button deleteBookButton = new Button("Delete");
    Label lISBN = new Label("ISBN");
    Label lTitle = new Label("Title");
    Label lCategory = new Label("Category");
    Label lSupplier = new Label("Supplier");
    Label lPurchaseDate = new Label("Purchased Date");
    Label lPurchasePrice = new Label("Purchased Price");
    Label lOriginPrice = new Label("Original Price");
    Label lSellingPrice = new Label("Selling Price");
    Label lAuthor = new Label("Author");
    Label lStock = new Label("Stock");
    Label fillCategoryLabel = new Label("");
    Label categoryLabel = new Label("New Category: ");
    Button addCategoryButton = new Button("Add Category");
    Button addBookButton = new Button("Add Book");
    Button logOutButton = new Button("Log Out");
    Button exitButton = new Button("Close");
    Button backButton = new Button("Back");
    Button nextButton = new Button("Next");
    Label message = new Label("");
    TextField tfaddCategory = new TextField();
    TextField tfISBN = new TextField();
    TextField tfTitle = new TextField();
    ComboBox categoryDropDown;
    TextField tfSupplier = new TextField();
    TextField tfPurchasePrice = new TextField();
    TextField tfOriginPrice = new TextField();
    TextField tfSellingPrice = new TextField();
    TextField tfAuthor = new TextField();
    TextField tfStock = new TextField();
    Menu menu1 = new Menu("Show Options");
    Menu menu2 = new Menu("More");
    MenuItem addBooks = new MenuItem("Add Books");
    MenuItem exitProgram = new MenuItem("Exit Program");
    MenuItem addCategory = new MenuItem("Add Category");
    MenuItem showLibrarians = new MenuItem("Librarians");
    public MenuItem getAddCategory() {
        return addCategory;
    }
    public void setAddCategory(MenuItem addCategory) {
        this.addCategory = addCategory;
    }
    DatePicker tfPurchaseDate = new DatePicker();
    LocalDate purchasedDate;
    //HBox filter = new HBox(10);
    VBox pane = new VBox();
    VBox librarianBox = new VBox();
    VBox filterBox = new VBox(10);
    Stage addBookStage = new Stage();
    Scene addBookScene = null;
    Scene librarianScene = new Scene(librarianBox, 750, 400);
    Scene filterScene = new Scene(filterBox, 400, 200);
    MenuBar menuBar = new MenuBar();
    Menu newMenu = new Menu("Menu");
    DatePicker dateFrom = new DatePicker();
    DatePicker dateTo = new DatePicker();
    ObservableList<User> data;
    boolean isPressedForLibrarian = false;
    boolean isPressedForDate = false;
    Stage librarianStage = new Stage();

    @Override
    public void start(Stage primaryStage){
        read();
        readFromUsers();
        user.printUsers();
        addLayout(pane);


        newMenu.getItems().add(addCategory);
        menuBar.getMenus().add(newMenu);

        pane.setSpacing(5);
        pane.setPadding(new Insets(10, 10, 10, 10));
        Scene scene = new Scene(pane, 1000, 400);
        primaryStage.setTitle("Manager Interface");
        primaryStage.setScene(scene);
        primaryStage.show();
        checkStock();
        logOutButton.setOnAction(event -> {
            LoginPage li = new LoginPage();
            li.start(primaryStage);
        });
        addBookButton.setOnAction(e->{
            try {
                ISBN = tfISBN.getText();
                title = tfTitle.getText();
                category = categoryDropDown.getSelectionModel().getSelectedItem().toString();
                supplier = tfSupplier.getText();
                purchasedDate = LocalDate.now();
                purchasedPrice = Double.parseDouble(tfPurchasePrice.getText());
                originalPrice = Double.parseDouble(tfOriginPrice.getText());
                sellingPrice = Double.parseDouble(tfSellingPrice.getText());
                author = tfAuthor.getText();
                stock = Integer.parseInt(tfStock.getText());
            }catch(Exception e2){
                message.setText("Fill in all the fields.");
            }
            boolean checker = book.checker(ISBN, title, category, supplier, purchasedDate, purchasedPrice, originalPrice, sellingPrice, author, stock);
            if(checker){
                tfISBN.clear();
                tfTitle.clear();
                categoryDropDown.setValue(null);
                tfSupplier.clear();
                tfPurchaseDate.setValue(null);
                tfPurchasePrice.clear();
                tfOriginPrice.clear();
                tfSellingPrice.clear();
                tfAuthor.clear();
                tfStock.clear();
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("The book is added successfully");
                a.setTitle("The book is added successfully");
                a.show();
                book.printBooks();
                write();
                read();
                updateTableView(tableView);
            }
            else{
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setHeaderText("Error occurred!");
                a.setTitle("Error occurred!");
                a.show();
            }
        });
        deleteBookButton.setOnAction(event -> {
            Book selectedBook = getSelectedBook();
            book.getBooks().remove(selectedBook);
            write();
            read();
            updateTableView(tableView);
        });
        exitProgram.setOnAction(e->primaryStage.close());
        exitButton.setOnAction(e->addBookStage.close());
        addBooks.setOnAction(e -> {
            readToCategory();
            categoryDropDown = new ComboBox(FXCollections.observableList(categoryList));
            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setPadding(new Insets(20, 20, 20, 20));
            gridPane.setVgap(5);

            gridPane.add(lISBN, 0, 0);
            tfISBN.setAlignment(Pos.BASELINE_RIGHT);
            gridPane.add(tfISBN, 1, 0);

            gridPane.add(lTitle, 0, 1);
            tfTitle.setAlignment(Pos.BASELINE_RIGHT);
            gridPane.add(tfTitle, 1,1);

            gridPane.add(lCategory, 0, 2);
            gridPane.add(categoryDropDown, 1, 2);


            gridPane.add(lSupplier, 0, 3);
            gridPane.add(tfSupplier, 1, 3);

            gridPane.add(lPurchaseDate, 0, 4);
            gridPane.add(tfPurchaseDate, 1, 4);

            gridPane.add(lPurchasePrice, 0, 5);
            gridPane.add(tfPurchasePrice, 1, 5);

            gridPane.add(lOriginPrice, 0, 6);
            gridPane.add(tfOriginPrice, 1, 6);

            gridPane.add(lSellingPrice, 0, 7);
            gridPane.add(tfSellingPrice, 1, 7);

            gridPane.add(lAuthor, 0, 8);
            gridPane.add(tfAuthor, 1, 8);

            gridPane.add(lStock, 0, 9);
            gridPane.add(tfStock, 1, 9);

            HBox buttonPane = new HBox();

            buttonPane.getChildren().addAll(addBookButton, exitButton);
            buttonPane.setAlignment(Pos.CENTER);

            VBox bookBox = new VBox();
            bookBox.setSpacing(5);
            bookBox.setPadding(new Insets(10, 10, 10, 10));
            bookBox.getChildren().addAll(menuBar, gridPane, buttonPane, message);
            addBookScene = new Scene(bookBox, 350, 450);
            addBookStage.setScene(addBookScene);
            addBookStage.show();

        });
        addCategory.setOnAction(e ->{
            VBox categoryPane = new VBox();
            categoryPane.setPadding(new Insets(20, 20, 20, 20));
            categoryPane.getChildren().addAll(categoryLabel,tfaddCategory,addCategoryButton, fillCategoryLabel, backButton);
            Scene newScene2 = new Scene(categoryPane, 300, 450);
            addBookStage.setScene(newScene2);
            addBookStage.show();
        });
        addCategoryButton.setOnAction(e->{
            if(tfaddCategory.getText().equals("")){
                fillCategoryLabel.setText("Fill in the category field.");
            }else {
                boolean exist = false;
                for(int i=0; i<categoryList.size(); i++){
                    if(categoryList.get(i).equals(tfaddCategory.getText())){
                        exist = true;
                        break;
                    }
                    else {
                        exist = false;

                    }
                }
                if(!exist){
                    categoryList.add(tfaddCategory.getText());
                    writeToCategory();
                    fillCategoryLabel.setText("Category added successfully!.");
                }else{
                    fillCategoryLabel.setText("Category already exists.");
                }

            }
        });
        backButton.setOnAction(e->{
            addBookStage.setScene(addBookScene);
        });
        showLibrarians.setOnAction(e->{
            Label filterFrom = new Label("From: ");
            Label filterTo = new Label("To: ");
            filterBox.setAlignment(Pos.CENTER);

            GridPane pane = new GridPane();
            pane.setAlignment(Pos.CENTER);
            pane.setHgap(5);
            pane.setVgap(5);
            pane.add(filterFrom, 0, 0);
            pane.add(dateFrom, 1, 0);
            pane.add(filterTo, 0, 1);
            pane.add(dateTo, 1, 1);

            if(!isPressedForDate){
                filterBox.getChildren().addAll(pane, nextButton);
            }
            isPressedForDate = true;

            librarianStage.setScene(filterScene);
            librarianStage.show();

        });
        nextButton.setOnAction(e->{
            LocalDate fromDate = dateFrom.getValue();
            LocalDate toDate = dateTo.getValue();

            readFromUsers();
            librarianTableView = new TableView<>();
            librarianTableView.setEditable(true);


            for(int i=0; i<user.getUsers().size(); i++){
                if(user.getUsers().get(i).getAccessLevel().equals("Librarian")){
                    librarians.add(user.getUsers().get(i));
                    for(int j=0; j<user.getUsers().get(i).getDatesArray().size(); j++){
                        if(user.getUsers().get(i).getDatesArray().get(j).isAfter(fromDate) && user.getUsers().get(i).getDatesArray().get(j).isBefore(toDate) ||
                                user.getUsers().get(i).getDatesArray().get(j).isEqual(fromDate) || user.getUsers().get(i).getDatesArray().get(j).isEqual(toDate)){
                            user.getUsers().get(i).setFilteredProfit(user.getUsers().get(i).getFilteredProfit() + user.getUsers().get(i).getProfitArray().get(j));
                        }
                    }
                }
            }

            data = FXCollections.observableArrayList(librarians);
            librarianTableView.setItems(data);

            TableColumn<User, String> firstNameColumn = new TableColumn<>("First Name");
            firstNameColumn.setMinWidth(150);
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));

            TableColumn<User, String> lastNameColumn = new TableColumn<>("Last Name");
            lastNameColumn.setMinWidth(150);
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));

            TableColumn<User, String> totalBillsColumn = new TableColumn<>("Total Bills");
            totalBillsColumn.setMinWidth(150);
            totalBillsColumn.setCellValueFactory(new PropertyValueFactory<>("totalBills"));

            TableColumn<User, String> totalBooksSoldColumn = new TableColumn<>("Total Books Sold");
            totalBooksSoldColumn.setMinWidth(150);
            totalBooksSoldColumn.setCellValueFactory(new PropertyValueFactory<>("booksSold"));

            TableColumn<User, String> filteredProfit = new TableColumn<>("Filter Profit");
            filteredProfit.setMinWidth(150);
            filteredProfit.setCellValueFactory(new PropertyValueFactory<>("filteredProfit"));

            if(!isPressedForLibrarian){

                librarianTableView.getColumns().addAll(firstNameColumn, lastNameColumn, totalBillsColumn, totalBooksSoldColumn, filteredProfit);
                librarianBox.getChildren().addAll(librarianTableView);
            }
            isPressedForLibrarian=true;

            librarianStage.setScene(librarianScene);
            librarianStage.show();

        });

    }
    private void writeToCategory(){
        try{
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/main/resources/categories.dat"));
            output.writeObject(categoryList);
            output.close();
        }catch(IOException e){
            System.out.println("1. " + e);
        }
    }
    private void readToCategory() {

        try{
            File file = new File("src/main/resources/categories.dat");
            file.createNewFile();
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
            categoryList = (ArrayList<String>)input.readObject();
        }catch(IOException ignored){
            System.out.println("2. " + ignored);
        }catch(ClassNotFoundException e1){
            System.out.println("3. " + e1);
        }
    }
    private void writeToUsers(){
        try{
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/main/resources/users.dat"));
            output.writeObject(user.getUsers());
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
            user.setUsers((ArrayList<User>)input.readObject());
        }catch(IOException ignored){
            System.out.println("2. " + ignored);
        }catch(ClassNotFoundException e1){
            System.out.println("3. " + e1);
        }
    }
    public static Book getSelectedBook() {
        return tableView.getSelectionModel().getSelectedItem();
    }
    public void updateTableView(TableView<Book> tableView) {
        ObservableList<Book> data = FXCollections.observableArrayList();
        ObservableList<Book> books = getBooks();
        data.addAll(books);
        // Set the data for the table view
        setTableData(data, tableView);
    }
    public static void setTableData(ObservableList<Book> data, TableView<Book> tableView) {
        tableView.setItems(data);
    }
    public ObservableList<Book> getBooks() {
        return FXCollections.observableList(book.getBooks());
    }
    private void checkStock() {
        int i=0;
        boolean isLow = false;
        books = book.getBooks();
        for (i = 0; i < books.size(); i++) {
            if(books.get(i).getStock()<5){
                isLow = true;
                break;
            }
        }
        if(isLow){
            showLowStockMessage(books.get(i));
            System.out.println("Hello");
        }
    }
    private void showLowStockMessage(Book book) {
        Alert warning = new Alert(Alert.AlertType.WARNING);
        warning.setTitle("Low Stock!");
        warning.setHeaderText("Low Stock!" );
        warning.setContentText("Check the stock, there are books which has stock lower than 5.");
        Optional<ButtonType> result = warning.showAndWait();
    }
    public void write(){
        try{
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/main/resources/history.dat"));
            output.writeObject(book.getBooks());
            output.close();
        }catch(IOException e){
            System.out.println("1. " + e);
        }
    }
    public void writeAsLibrarian(){
        try{
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/main/resources/users.dat"));
            output.writeObject(librarians);
            output.close();
        }catch(IOException e){
            System.out.println("1. " + e);
        }
    }

    public void read(){
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
    private void addLayout(VBox pane) {
        tableView = new TableView<>();
        tableView.setEditable(true);

        ObservableList<Book> data = FXCollections.observableArrayList(book.getBooks());
        tableView.setItems(data);

        TableColumn<Book, String> ISBNColumn = new TableColumn<>("ISBN");
        ISBNColumn.setMinWidth(100);
        ISBNColumn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        ISBNColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ISBNColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Book, String> event) {
                Book book = event.getRowValue();
                book.setISBN(event.getNewValue());
                write();
            }
        });

        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setMinWidth(100);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        titleColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Book, String> event) {
                Book book = event.getRowValue();
                book.setTitle(event.getNewValue());
                write();
            }
        });

        TableColumn<Book, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setMinWidth(100);
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        categoryColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Book, String> event) {
                Book book = event.getRowValue();
                book.setCategory(event.getNewValue());
                write();
            }
        });

        TableColumn<Book, String> supplierColumn = new TableColumn<>("Supplier");
        supplierColumn.setMinWidth(100);
        supplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        supplierColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        supplierColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Book, String> event) {
                Book book = event.getRowValue();
                book.setSupplier(event.getNewValue());
                write();
            }
        });

        TableColumn<Book, LocalDate> purchasedDateColumn = new TableColumn<>("Purchased Date");
        purchasedDateColumn.setMinWidth(100);
        purchasedDateColumn.setCellValueFactory(new PropertyValueFactory<>("purchasedDate"));
        try {
            purchasedDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
            purchasedDateColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book, LocalDate>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Book, LocalDate> event) {
                    Book book = event.getRowValue();
                    book.setLocalDate(event.getNewValue());
                    write();
                    Alert a = new Alert(Alert.AlertType.WARNING);
                    a.setHeaderText("Fill it with the correct Format!");
                    a.setTitle("Fill it with the correct Format!");
                    a.show();


                }
            });
        }catch (Exception ex){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Fill it with the correct Format!");
            a.setTitle("Fill it with the correct Format!");
            a.show();
        }


        TableColumn<Book, Double> purchasedPriceColumn = new TableColumn<>("Purchased Price");
        purchasedPriceColumn.setMinWidth(100);
        purchasedPriceColumn.setCellValueFactory(new PropertyValueFactory<>("purchasedPrice"));
        purchasedPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        purchasedPriceColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Book, Double> event) {
                Book book = event.getRowValue();
                book.setPurchasedPrice(event.getNewValue());
                write();
            }
        });

        TableColumn<Book, Double> originalPriceColumn = new TableColumn<>("Original Price");
        originalPriceColumn.setMinWidth(100);
        originalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("originalPrice"));
        originalPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        originalPriceColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Book, Double> event) {
                Book book = event.getRowValue();
                book.setOriginalPrice(event.getNewValue());
                write();
            }
        });

        TableColumn<Book, Double> sellingPriceColumn = new TableColumn<>("Selling Price");
        sellingPriceColumn.setMinWidth(100);
        sellingPriceColumn.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        sellingPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        sellingPriceColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Book, Double> event) {
                Book book = event.getRowValue();
                book.setSellingPrice(event.getNewValue());
                write();
            }
        });

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setMinWidth(100);
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        authorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        authorColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Book, String> event) {
                Book book = event.getRowValue();
                book.setAuthor(event.getNewValue());
                write();
            }
        });

        TableColumn<Book, Integer> stockColumn = new TableColumn<>("Stock");
        stockColumn.setMinWidth(100);
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        stockColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        stockColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Book, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Book, Integer> event) {
                Book book = event.getRowValue();
                book.setStock(event.getNewValue());
                write();
            }
        });

        MenuBar menu = new MenuBar();

        menu1.getItems().addAll(addBooks, showLibrarians);
        menu2.getItems().add(exitProgram);
        menu.getMenus().addAll(menu1, menu2);

        tableView.getColumns().addAll(ISBNColumn, titleColumn, categoryColumn, supplierColumn, purchasedDateColumn,
                purchasedPriceColumn, originalPriceColumn, sellingPriceColumn, authorColumn, stockColumn);

        pane.getChildren().addAll(menu, tableView, deleteBookButton, logOutButton);
    }
    public static void main(String[] args) {
        launch();
    }
}