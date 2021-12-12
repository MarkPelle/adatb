module com.imdb.imdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;

    opens com.imdb.imdb to javafx.fxml;
    exports com.imdb.imdb;
}