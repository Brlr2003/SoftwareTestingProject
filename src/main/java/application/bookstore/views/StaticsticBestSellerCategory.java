package application.bookstore.views;

import java.util.ArrayList;

import application.bookstore.controllers.StatisticController;
import application.bookstore.controllers.StatisticsBestSellerController;
import application.bookstore.models.Book;
import application.bookstore.models.Category;
import application.bookstore.models.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;

public class StaticsticBestSellerCategory extends View {
	  private final VBox vBox = new VBox();


	    public StaticsticBestSellerCategory() {
	        new StatisticsBestSellerController(this);
	    }

	    @Override
	    public Parent getView() {
	        vBox.setSpacing(10);
	        vBox.setAlignment(Pos.CENTER);

	        ArrayList<Book> books = Book.getBooks();
	        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

	        
	        Category[] catVal = Category.values();
	        String[] categories = new String[8];
	        
	        for(int i=0; i<8;i++) {
	    		categories[i] = (catVal[i]).name();
	    	}
	        
	        
	        int[] quantity = new int[8];
	        
	        int index=0;
	        
	        for(Category c: Category.values()) {
	        	for(Order o : Order.getOrders()) {
	        		if ((o.getIsbnCategory()).equals(c))
	        			quantity[index]+=o.getQuantity();
	        	}
	        	index+=1;
	        }

	        for(int i=0; i<8; i++) {
	        pieChartData.add(new PieChart.Data(categories[i],quantity[i]));
	        }
	             
	        		
	    
	     
	        final PieChart chart = new PieChart(pieChartData);

	        chart.setTitle("BEST SELLER CATEGORIES");

	        vBox.getChildren().addAll(chart);

	        return vBox;
	    }

}
