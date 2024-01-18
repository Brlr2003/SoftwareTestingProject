package application.bookstore.models;

import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;

import application.bookstore.models.Author;
import application.bookstore.models.BaseModel;

public class Book extends BaseModel  implements Serializable{
    private String isbn;
    private String title;
    private float purchasedPrice;
    private float sellingPrice;
    private Author author;
    private int  stock;
    private Category category;
    private String supplier;
    @Serial
    private static final long serialVersionUID = 1234567L;
    
    public Category getCategory() {
		return this.category;
	}

	

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}


	public static final String FILE_PATH = "data/books.ser";
    private static final File DATA_FILE = new File(FILE_PATH);
    private static final ArrayList<Book> books = new ArrayList<>();
    
    public Book(){}

   
    public Book(String isbn, String title, float purchasedPrice, float sellingPrice, Author author, int stock,
			Category category, String supplier) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.purchasedPrice = purchasedPrice;
		this.sellingPrice = sellingPrice;
		this.author = author;
		this.stock = stock;
		this.category = category;
		this.supplier = supplier;
	}

	public float getPurchasedPrice() {
        return purchasedPrice;
    }

    public void setPurchasedPrice(float purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }

    public float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    
    public String getFullResult() {
        return getIsbn()+ " " + getTitle() + " " + getPurchasedPrice();
    }

    @Override
    public boolean saveInFile() {
        boolean saved = super.save(DATA_FILE);
        if (saved)
            books.add(this);
        return saved;
    }

    public boolean isValid() {
    	if (!isbn.matches("^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$"))
           return false;
        if (sellingPrice < 0 || purchasedPrice < 0 || stock<0 )
            return false;
        if (!title.matches("\\w+") || !supplier.matches("\\w+"))
            return false;
        return true;
    }
    
    public boolean existsInList(){
        for (Book b: books) {
            if (b.getIsbn().equals(this.getIsbn()))
                return true;
        }
        return false;
    }
    
    @Override
    public boolean updateFile(){
        try {
            overwriteCurrentListToFile(DATA_FILE, books);
        }
        catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        }
        return true;
    }


    @Override
    public boolean deleteFromFile() {
    	books.remove(this);
    	try {
            overwriteCurrentListToFile(DATA_FILE, books);
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    public static ArrayList<Book> getBooks() {
        if (books.size() == 0) {
            try {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
                while (true) {
                    Book temp = (Book) inputStream.readObject();
                    if (temp != null)
                        books.add(temp);
                    else
                        break;
                }
                inputStream.close();
            } catch (EOFException eofException) {
                System.out.println("End of book file reached!");
            }
            catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return books;
    }
    
    
    public static<Book extends BaseModel> void overwriteCurrentListToFile(File DATA_FILE, ArrayList<Book> bookList) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(DATA_FILE, false);
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
        if (bookList.size() == 0) {
        } else {
            for (Book book : bookList)
                outputStream.writeObject(book);
        }
    }    
    
    
    public static ArrayList<Book> getSearchResults(String searchText) {
        ArrayList<Book> searchResults = new ArrayList<>();
        for(Book book: getBooks())
            if (book.getTitle().toLowerCase().matches(".*"+searchText.toLowerCase()+".*") || 
            		book.getAuthor().getFullName().toLowerCase().matches(".*"+searchText.toLowerCase()+".*")|| 
            		book.getIsbn().matches(".*"+searchText+".*") )  
                searchResults.add(book);
        return searchResults;
    }
    
    
    
    
    
  
}