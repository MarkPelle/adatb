package com.imdb.dataAccess;

import java.sql.*;

public class DatabaseConnector {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/filmek?useSSL=false";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";
    private static final String SELECT_QUERY = "SELECT * FROM filmek";


    public ResultSet selectRecord() throws SQLException {
        Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        Statement st = connection.prepareStatement(SELECT_QUERY, ResultSet.TYPE_SCROLL_SENSITIVE);
        ResultSet rs = st.executeQuery(SELECT_QUERY);
        return rs;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
