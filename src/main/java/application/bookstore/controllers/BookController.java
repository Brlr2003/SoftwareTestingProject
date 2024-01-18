package application.bookstore.controllers;

import java.util.ArrayList;
import java.util.List;

import application.bookstore.models.Author;
import application.bookstore.models.Book;
import application.bookstore.models.Category;
import application.bookstore.views.BookView;
import javafx.collections.FXCollections;
import javafx.scene.paint.Color;

public class BookController {
    private final BookView bookView;

    public BookController(BookView bookView) {
        this.bookView = bookView;
        setSaveListener();
        setEditListener();
        setDeleteListener();
        setComboBoxListener();
        setSearchListener();
    }

    private void setSearchListener() {
        bookView.getSearchView().getClearBtn().setOnAction(e -> {
            bookView.getSearchView().getSearchField().setText("");
            bookView.getTableView().setItems(FXCollections.observableArrayList(Book.getBooks()));
        });
        bookView.getSearchView().getSearchBtn().setOnAction(e -> {
            String searchText = bookView.getSearchView().getSearchField().getText();
            ArrayList<Book> searchResults = Book.getSearchResults(searchText);
            bookView.getTableView().setItems(FXCollections.observableArrayList(searchResults));
        });
    }

    private void setComboBoxListener(){
        bookView.getAuthorsComboBox().setOnMouseClicked(e->{
            bookView.getAuthorsComboBox().getItems().setAll(Author.getAuthors());
            // set default selected the first author
            if (!Author.getAuthors().isEmpty())
                bookView.getAuthorsComboBox().setValue(Author.getAuthors().get(0));
        });
        
        bookView.getCategoryComboBox().setOnMouseClicked(e->{
            bookView.getCategoryComboBox().getItems().setAll(Category.values());
  
        });

    }
    private void setSaveListener() {
        bookView.getSaveBtn().setOnAction(e -> {
            String isbn = bookView.getIsbnField().getText();
            String title = bookView.getTitleField().getText();
            float purchasedPrice = Float.parseFloat(bookView.getPurchasedPriceField().getText());
            float sellingPrice = Float.parseFloat(bookView.getSellingPriceField().getText());
            Author author = bookView.getAuthorsComboBox().getValue();
            Category category = bookView.getCategoryComboBox().getValue();
            String supplier = bookView.getSupplierField().getText();
            int stock = Integer.parseInt(bookView.getStockField().getText());
            Book book = new Book(isbn ,title,purchasedPrice,sellingPrice, author, stock, category, supplier);

            if (!book.existsInList()){
                if (book.saveInFile()) {
                    bookView.getTableView().getItems().add(book);
                    bookView.getResultLabel().setText("Book created successfully");
                    bookView.getResultLabel().setTextFill(Color.GREEN);
                    resetFields();
                } else {
                    bookView.getResultLabel().setText("Book creation failed");
                    bookView.getResultLabel().setTextFill(Color.RED);
                }
            } else {
                bookView.getResultLabel().setText("ISBN must be unique");
                bookView.getResultLabel().setTextFill(Color.RED);
            }
        });
    }

    private void setDeleteListener(){
        bookView.getDeleteBtn().setOnAction(e->{
            List<Book> itemsToDelete = List.copyOf(bookView.getTableView().getSelectionModel().getSelectedItems());
            for (Book b: itemsToDelete) {
                if (b.deleteFromFile()) {
                    bookView.getTableView().getItems().remove(b);
                    bookView.getResultLabel().setText("Book removed successfully");
                    bookView.getResultLabel().setTextFill(Color.GREEN);
                } else {
                    bookView.getResultLabel().setText("Book deletion failed");
                    bookView.getResultLabel().setTextFill(Color.RED);
                    break;
                }
            }
        });
    }

    private void setEditListener() {
        bookView.getIsbnCol().setOnEditCommit(e -> {
            Book bookToEdit = e.getRowValue();
            String oldVal=bookToEdit.getIsbn();
            bookToEdit.setIsbn(e.getNewValue());
            if (bookToEdit.isValid()){
                bookToEdit.updateFile();
            }
            else {
                bookToEdit.setIsbn(oldVal);
                bookView.getTableView().getItems().set(bookView.getTableView().getItems().indexOf(bookToEdit), bookToEdit);
                bookView.getResultLabel().setTextFill(Color.DARKRED);
            }
        });

        bookView.getTitleCol().setOnEditCommit(e -> {
            Book bookToEdit = e.getRowValue();
            String oldVal=bookToEdit.getTitle();
            bookToEdit.setTitle(e.getNewValue());
            if (bookToEdit.isValid()){
                bookToEdit.updateFile();
            }
            else {
                System.out.println("You cannot edit "+e.getNewValue());
                bookToEdit.setTitle(oldVal);
                bookView.getTableView().getItems().set(bookView.getTableView().getItems().indexOf(bookToEdit), bookToEdit);
                bookView.getResultLabel().setText("No edition done");
                bookView.getResultLabel().setTextFill(Color.RED);
            }
        });

        bookView.getPurchasedPriceCol().setOnEditCommit(e -> {
            Book bookToEdit = e.getRowValue();
            float oldVal=bookToEdit.getPurchasedPrice();
            bookToEdit.setPurchasedPrice(e.getNewValue());
            if (bookToEdit.isValid()){
                bookToEdit.updateFile();
            }
            else {
       
                bookToEdit.setPurchasedPrice(oldVal);
                bookView.getTableView().getItems().set(bookView.getTableView().getItems().indexOf(bookToEdit), bookToEdit);
                bookView.getResultLabel().setText("Invalid  book data");
                bookView.getResultLabel().setTextFill(Color.RED);
            }
        });

        bookView.getSellingPriceCol().setOnEditCommit(e -> {
            Book bookToEdit = e.getRowValue();
            float oldVal=bookToEdit.getSellingPrice();
            bookToEdit.setSellingPrice(e.getNewValue());
            if (bookToEdit.isValid()){
                bookToEdit.updateFile();
            }
            else {
                bookToEdit.setSellingPrice(oldVal);
                bookView.getTableView().getItems().set(bookView.getTableView().getItems().indexOf(bookToEdit), bookToEdit);
                bookView.getResultLabel().setText("Try again");
                bookView.getResultLabel().setTextFill(Color.RED);
            }
        });
        
        bookView.getStockCol().setOnEditCommit(e -> {
            Book bookToEdit = e.getRowValue();
            int oldVal=bookToEdit.getStock();
            bookToEdit.setStock(e.getNewValue());
            if (bookToEdit.isValid()){
                bookToEdit.updateFile();
            }
            else {
                bookToEdit.setStock(oldVal);
                bookView.getTableView().getItems().set(bookView.getTableView().getItems().indexOf(bookToEdit), bookToEdit);
                bookView.getResultLabel().setText("Try again");
                bookView.getResultLabel().setTextFill(Color.RED);
            }
        });
        
        bookView.getSupplierCol().setOnEditCommit(e -> {
            Book bookToEdit = e.getRowValue();
            String oldVal=bookToEdit.getTitle();
            bookToEdit.setSupplier(e.getNewValue());
            if (bookToEdit.isValid()){
                bookToEdit.updateFile();
            }
            else {
                System.out.println("You cannot edit "+e.getNewValue());
                bookToEdit.setTitle(oldVal);
                bookView.getTableView().getItems().set(bookView.getTableView().getItems().indexOf(bookToEdit), bookToEdit);
                bookView.getResultLabel().setText("No edition done");
                bookView.getResultLabel().setTextFill(Color.RED);
            }
        });



    }

    private void resetFields() {
        bookView.getIsbnField().setText("");
        bookView.getTitleField().setText("");
        bookView.getPurchasedPriceField().setText("");
        bookView.getSellingPriceField().setText("");
    }
}
