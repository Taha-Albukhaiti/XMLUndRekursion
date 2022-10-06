module com.example.xmlundrekursion {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens com.example.xmlundrekursion to javafx.fxml;
    exports com.example.xmlundrekursion;
}