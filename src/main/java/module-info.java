module org.example.javafxpractice {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.j;
    requires com.jfoenix;


    opens org.example.javafxpractice to javafx.fxml;
    exports org.example.javafxpractice;
    exports org.example.javafxpractice.objects;
    exports org.example.javafxpractice.controllers;
    opens org.example.javafxpractice.controllers to javafx.fxml;
    exports org.example.javafxpractice.util;
    opens org.example.javafxpractice.util to javafx.fxml;
}