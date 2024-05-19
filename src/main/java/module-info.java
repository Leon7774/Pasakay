module org.example.javafxpractice {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens org.example.javafxpractice to javafx.fxml;
    exports org.example.javafxpractice;
}