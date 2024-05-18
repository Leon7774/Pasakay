module org.example.javafxpractice {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.javafxpractice to javafx.fxml;
    exports org.example.javafxpractice;
}