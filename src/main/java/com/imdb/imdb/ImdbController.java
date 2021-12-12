package com.imdb.imdb;

import com.imdb.dataAccess.DatabaseConnector;
import com.imdb.models.Film;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImdbController {

    public MenuBar menuBar;
    public Label exit;
    public TableView databaseTable;
    private DatabaseConnector db = new DatabaseConnector();
    private ObservableList<String> data;
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/filmek?useSSL=false";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "d%gvaMUNt*14#";
    private static final String SELECT_QUERY = "SELECT * FROM filmek";

    @FXML
    public void handleExitApplication() {
        Platform.exit();
    }

    @FXML
    public void createFilmsTable() throws SQLException {
        resetAndShowTableView();
        TableColumn<Film, String> eredetiCímColumn = new TableColumn<>("Eredeti cím");
        eredetiCímColumn.setCellValueFactory(new PropertyValueFactory<>("eredetiCím"));

        TableColumn<Film, String> magyarCímColumn = new TableColumn<>("Magyar cím");
        eredetiCímColumn.setCellValueFactory(new PropertyValueFactory<>("magyarCím"));

        TableColumn<Film, String> műfajColumn = new TableColumn<>("Műfaj");
        eredetiCímColumn.setCellValueFactory(new PropertyValueFactory<>("műfaj"));

       // TableColumn<Film, Integer> évszámColumn = new TableColumn<>("Évszám");
        // eredetiCímColumn.setCellValueFactory(new PropertyValueFactory<>("műfaj"));

        Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        Statement st = connection.prepareStatement(SELECT_QUERY, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery(SELECT_QUERY);


        databaseTable.getColumns().add(eredetiCímColumn);
        databaseTable.getColumns().add(magyarCímColumn);
        databaseTable.getColumns().add(műfajColumn);
        rs.first();
        while(rs.next()) {
            databaseTable.getItems().add(new Film(rs.getString("cím_eredeti"),rs.getString("cím_hu"),rs.getString("műfaj")));
        }
    }

    @FXML
    public void createSeriesTable() {
        resetAndShowTableView();

        ArrayList<TableColumn> columns = new ArrayList<TableColumn>();
        columns.add(new TableColumn("Eredeti cím"));
        columns.add(new TableColumn("Magyar cím"));
        columns.add(new TableColumn("Rendezte"));
        columns.add(new TableColumn("Évszám"));
        columns.add(new TableColumn("Műfaj"));

        databaseTable.getColumns().addAll(columns);
    }

    @FXML
    public void createActorsTable() {
        resetAndShowTableView();

        ArrayList<TableColumn> columns = new ArrayList<TableColumn>();
        columns.add(new TableColumn("ID"));
        columns.add(new TableColumn("Név"));
        columns.add(new TableColumn("Születési idő"));

        databaseTable.getColumns().addAll(columns);
    }

    @FXML
    public void createDirectorTable() {
        resetAndShowTableView();
        DatabaseConnector db = new DatabaseConnector();
        ArrayList<TableColumn> columns = new ArrayList<TableColumn>();
        columns.add(new TableColumn("Név"));
        columns.add(new TableColumn("Születési idő"));
        columns.add(new TableColumn("Születési hely"));

        databaseTable.getColumns().addAll(columns);
    }

    private void resetAndShowTableView() {
        if (databaseTable != null) {
            databaseTable.getColumns().clear();
            databaseTable.setVisible(true);
        }
    }
}