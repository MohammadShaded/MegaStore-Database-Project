module com.example.thestore {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires javafx.graphics;
    requires jasperreports;


    opens com.example.thestore to javafx.fxml;
    exports com.example.thestore;
    exports com.example.thestore.Methods;
    opens com.example.thestore.Methods to javafx.fxml;
    exports com.example.thestore.AdminControllers;
    opens com.example.thestore.AdminControllers to javafx.fxml;
    exports com.example.thestore.AddingControllers;
    opens com.example.thestore.AddingControllers to javafx.fxml;
    exports com.example.thestore.SlidesControllers;
    opens com.example.thestore.SlidesControllers to javafx.fxml;
    exports com.example.thestore.TablesFields;
    opens com.example.thestore.TablesFields to javafx.fxml;


}