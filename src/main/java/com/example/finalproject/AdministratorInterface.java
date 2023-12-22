package com.example.finalproject;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class AdministratorInterface extends Application{
    UserController users = new UserController();
    private static TableView<User> employeeTableView = new TableView<>();
    private VBox mainVBox = new VBox();
    VBox librarianBox = new VBox(10);
    Scene librarianScene = new Scene(librarianBox, 300, 400);
    Scene mainScene = new Scene(mainVBox, 300, 400);
    Scene addEmployeeScene = null;
    boolean isPressed = false;
    Button addEmployee = new Button("Add Employee");
    Button saveEmployeeButton = new Button("Save");
    Button exitButton = new Button("Close");
    Button backButton = new Button("Back");
    Button deleteUserButton = new Button("Delete");
    ArrayList<String> roles = new ArrayList<>();
    ComboBox roleDropDown = new ComboBox();
    Label message= new Label("");
    Label lFirstName = new Label("First Name: ");
    Label lLastName = new Label("Last Name: ");
    Label lEmail = new Label("Email: ");
    Label lBirthdate = new Label("Birthdate: ");
    Label lPhoneNumber = new Label("Phone Number: ");
    Label lRole = new Label("Role: ");
    Label lUsername = new Label("Username: ");
    Label lPassword = new Label("Password: ");
    Label lVerifyPass = new Label("Verify Password: ");
    TextField tfFirstName = new TextField();
    TextField tfLastName = new TextField();
    TextField tfEmail = new TextField();
    TextField tfUsername = new TextField();
    PasswordField tfPassword = new PasswordField();
    PasswordField tfVerifyPassword = new PasswordField();
    DatePicker tfBirthdate = new DatePicker();
    TextField tfPhoneNumber = new TextField();
    Stage addEmployeeStage = new Stage();
    private String firstName, lastName, email, phoneNumber, role, username, password, verifyPassword;
    LocalDate birthdate;
    int accessLevel;

    private Book selectedBook;
    private static BookController book = new BookController();
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


    public void start(Stage mainStage) {
        readFromUsers();
        roles.add("Librarian");
        roles.add("Manager");
        librarianBox.setAlignment(Pos.CENTER);

        TabPane tabPane = new TabPane();


        Tab tab1 = new Tab("Manage Employees");
        tab1.setClosable(false);
        Tab tab2 = new Tab("Books");
        tab2.setClosable(false);
        Tab tab3 = new Tab("Stats");
        tab3.setClosable(false);

        tabPane.getTabs().addAll(tab1, tab2, tab3);

        VBox vBox = new VBox(tabPane);
        Scene scene = new Scene(vBox);

        //MANAGE EMPLOYEE TAB VIEW
        employeeTableView = new TableView<>();
        employeeTableView.setEditable(true);

        ObservableList<User> data = FXCollections.observableArrayList(users.getUsers());
        employeeTableView.setItems(data);

        TableColumn<User, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setMinWidth(100);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<User, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<User, String> event) {
                User user = event.getRowValue();
                user.setFirstname(event.getNewValue());
                writeToUsers();
            }
        });

        TableColumn<User, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setMinWidth(100);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<User, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<User, String> event) {
                User user = event.getRowValue();
                user.setLastname(event.getNewValue());
                writeToUsers();
            }
        });

        TableColumn<User, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setMinWidth(100);
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("accessLevel"));
        roleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        roleColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<User, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<User, String> event) {
                User user = event.getRowValue();
                if(event.getNewValue().equals("Librarian") || event.getNewValue().equals("Manager")){
                    user.setAccessLevel(event.getNewValue());
                    writeToUsers();
                }else{
                    Alert a = new Alert(Alert.AlertType.WARNING);
                    a.setHeaderText("Warning!");
                    a.setTitle("Role can only be Manager or Librarian");
                    a.show();
                }

            }
        });

        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setMinWidth(100);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<User, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<User, String> event) {
                User user = event.getRowValue();
                user.setEmail(event.getNewValue());
                writeToUsers();
            }
        });

        TableColumn<User, String> phoneColumn = new TableColumn<>("Contact");
        phoneColumn.setMinWidth(100);
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<User, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<User, String> event) {
                User user = event.getRowValue();
                user.setPhoneNumber(event.getNewValue());
                writeToUsers();
            }
        });



        if(!isPressed){
            employeeTableView.getColumns().addAll(firstNameColumn, lastNameColumn, roleColumn, emailColumn, phoneColumn);
            librarianBox.getChildren().addAll(employeeTableView, addEmployee, deleteUserButton, logOutButton);
            tab1.setContent(librarianBox);
        }
        isPressed=true;

//        Button viewStatisticsButton = new Button("View Statistics");
//
//        mainVBox.setPadding(new Insets(10, 10, 10, 10));
//        mainVBox.getChildren().addAll(manageEmployeesButton, viewStatisticsButton);

        mainStage.setTitle("Admin Panel");
        mainStage.setScene(scene);
        mainStage.show();

        logOutButton.setOnAction(event -> {
            LoginPage li = new LoginPage();
            li.start(mainStage);
            System.out.println("LOG OUT:\n");
            users.printUsers();
            System.out.println("\n\n\n");
        });
        exitButton.setOnAction(e->addEmployeeStage.close());
//        manageEmployeesButton.setOnAction(e->{
//            employeeTableView = new TableView<>();
//            employeeTableView.setEditable(true);
//
//            ObservableList<User> data = FXCollections.observableArrayList(users.getUsers());
//            employeeTableView.setItems(data);
//
//            TableColumn<User, String> firstNameColumn = new TableColumn<>("First Name");
//            firstNameColumn.setMinWidth(100);
//            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
//            firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//            firstNameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<User, String>>() {
//                @Override
//                public void handle(TableColumn.CellEditEvent<User, String> event) {
//                    User user = event.getRowValue();
//                    user.setFirstname(event.getNewValue());
//                    writeToUsers();
//                }
//            });
//
//            TableColumn<User, String> lastNameColumn = new TableColumn<>("Last Name");
//            lastNameColumn.setMinWidth(100);
//            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
//            lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//            lastNameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<User, String>>() {
//                @Override
//                public void handle(TableColumn.CellEditEvent<User, String> event) {
//                    User user = event.getRowValue();
//                    user.setLastname(event.getNewValue());
//                    writeToUsers();
//                }
//            });
//
//            TableColumn<User, String> roleColumn = new TableColumn<>("Role");
//            roleColumn.setMinWidth(100);
//            roleColumn.setCellValueFactory(new PropertyValueFactory<>("accessLevel"));
//            roleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//            roleColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<User, String>>() {
//                @Override
//                public void handle(TableColumn.CellEditEvent<User, String> event) {
//                    User user = event.getRowValue();
//                    if(event.getNewValue().equals("Librarian") || event.getNewValue().equals("Manager")){
//                        user.setAccessLevel(event.getNewValue());
//                        writeToUsers();
//                    }else{
//                        Alert a = new Alert(Alert.AlertType.WARNING);
//                        a.setHeaderText("Warning!");
//                        a.setTitle("Role can only be Manager or Librarian");
//                        a.show();
//                    }
//
//                }
//            });
//
//            TableColumn<User, String> emailColumn = new TableColumn<>("Email");
//            emailColumn.setMinWidth(100);
//            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
//            emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//            emailColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<User, String>>() {
//                @Override
//                public void handle(TableColumn.CellEditEvent<User, String> event) {
//                    User user = event.getRowValue();
//                    user.setEmail(event.getNewValue());
//                    writeToUsers();
//                }
//            });
//
//            TableColumn<User, String> phoneColumn = new TableColumn<>("Contact");
//            phoneColumn.setMinWidth(100);
//            phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
//            phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//            phoneColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<User, String>>() {
//                @Override
//                public void handle(TableColumn.CellEditEvent<User, String> event) {
//                    User user = event.getRowValue();
//                    user.setPhoneNumber(event.getNewValue());
//                    writeToUsers();
//                }
//            });
//
//
//
//            if(!isPressed){
//                employeeTableView.getColumns().addAll(firstNameColumn, lastNameColumn, roleColumn, emailColumn, phoneColumn);
//                librarianBox.getChildren().addAll(employeeTableView, addEmployee, backButton, deleteUserButton);
//            }
//            isPressed=true;
//            mainStage.setScene(librarianScene);
////            mainStage.show();
//        });
        addEmployee.setOnAction(e -> {
            roleDropDown = new ComboBox(FXCollections.observableList(roles));
            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setPadding(new Insets(20, 20, 20, 20));
            gridPane.setVgap(5);

            gridPane.add(lFirstName, 0, 0);
            tfFirstName.setAlignment(Pos.BASELINE_RIGHT);
            gridPane.add(tfFirstName, 1, 0);

            gridPane.add(lLastName, 0, 1);
            tfLastName.setAlignment(Pos.BASELINE_RIGHT);
            gridPane.add(tfLastName, 1,1);

            gridPane.add(lRole, 0, 2);
            gridPane.add(roleDropDown, 1, 2);

            gridPane.add(lBirthdate, 0, 5);
            gridPane.add(tfBirthdate, 1, 5);

            gridPane.add(lPhoneNumber, 0, 3);
            gridPane.add(tfPhoneNumber, 1, 3);

            gridPane.add(lEmail, 0, 4);
            gridPane.add(tfEmail, 1, 4);

            gridPane.add(lUsername, 0, 6);
            gridPane.add(tfUsername, 1, 6);

            gridPane.add(lPassword, 0, 7);
            gridPane.add(tfPassword, 1, 7);

            gridPane.add(lVerifyPass, 0, 8);
            gridPane.add(tfVerifyPassword, 1, 8);

            HBox buttonPane = new HBox();

            buttonPane.getChildren().addAll(saveEmployeeButton, exitButton);
            buttonPane.setAlignment(Pos.CENTER);

            VBox bookBox = new VBox();
            bookBox.setSpacing(5);
            bookBox.setPadding(new Insets(10, 10, 10, 10));
            bookBox.getChildren().addAll(gridPane, buttonPane, message);
            addEmployeeScene = new Scene(bookBox, 300, 400);
            addEmployeeStage.setScene(addEmployeeScene);
            addEmployeeStage.show();

        });
        saveEmployeeButton.setOnAction(e->{
            try {
                firstName = tfFirstName.getText();
                lastName = tfLastName.getText();
                role = roleDropDown.getSelectionModel().getSelectedItem().toString();
                email = tfEmail.getText();
                birthdate = LocalDate.now();
                phoneNumber = tfPhoneNumber.getText();
                username = tfUsername.getText();
                password = tfPassword.getText();
                verifyPassword = tfVerifyPassword.getText();
            }catch(Exception e2){
                message.setText("Fill in all the fields.");
            }
            boolean checker = users.checker(firstName, lastName, birthdate, email, phoneNumber, role, username, password, verifyPassword);
            if(checker){
                tfFirstName.clear();
                tfLastName.clear();
                tfBirthdate.setValue(null);
                roleDropDown.setValue(null);
                tfEmail.clear();
                tfPhoneNumber.clear();
                tfUsername.clear();
                tfPassword.clear();
                tfVerifyPassword.clear();
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("User added successfully");
                a.setTitle("The User added successfully");
                a.show();
                users.printUsers();
                writeToUsers();
                readFromUsers();
                updateUserTableView(employeeTableView);
            }else{
                message.setText("Check the fields again.");
            }
        });
        backButton.setOnAction(e->mainStage.setScene(mainScene));
        deleteUserButton.setOnAction(event -> {
            User selectedBook = getSelectedUser();
            users.getUsers().remove(selectedBook);
            writeToUsers();
            readFromUsers();
            updateUserTableView(employeeTableView);
        });

        ///BOOK VIEW
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
        vbox.getChildren().addAll(tableView,addToBillButton, quantityLabel,quantityField, checkOutButton, success);

        tab2.setContent(vbox);

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
                //alert.setContentText("You total amount is: " + totalAmount);

                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    readFromUsers();
                    for(int i=0; i<users.getUsers().size(); i++){
                        if(LoginPage.username.equals(users.getUsers().get(i).getUsername()) &&
                                LoginPage.password.equals(users.getUsers().get(i).getPassword())){
                            users.getUsers().get(i).setTotalBills(users.getUsers().get(i).getTotalBills()+totalAmount);
                            users.getUsers().get(i).setBooksSold(users.getUsers().get(i).getBooksSold()+booksSold);
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




        //CHART VIEW
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
        tab3.setContent(bc);
    }

    public static void setUserTableData(ObservableList<User> data, TableView<User> tableView) {
        tableView.setItems(data);
    }
    public void updateUserTableView(TableView<User> tableView) {
        ObservableList<User> data = FXCollections.observableArrayList();
        ObservableList<User> users = getUsers();
        data.addAll(users);
        // Set the data for the table view
        setUserTableData(data, tableView);
    }

    public ObservableList<User> getUsers() {
        return FXCollections.observableList(users.getUsers());
    }

    public static User getSelectedUser() {
        return employeeTableView.getSelectionModel().getSelectedItem();
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
    public VBox getLayout() {
        return mainVBox;
    }





    //FOR BOOKS



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

    //read the input from the file
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
        // Set the data for the table view
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
}