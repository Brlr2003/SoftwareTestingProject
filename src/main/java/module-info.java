module com.example.kthimi {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.finalProject to javafx.fxml;
    exports com.example.finalProject;

    opens com.example.finalProject.Model to javafx.base;
}

