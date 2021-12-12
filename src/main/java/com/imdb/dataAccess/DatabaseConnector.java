package com.imdb.dataAccess;

import java.sql.*;

public class DatabaseConnector {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/filmek?useSSL=false";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "d%gvaMUNt*14#";
    private static final String SELECT_QUERY = "SELECT * FROM filmek";
    private static final String INSERT_QUERY = "INSERT INTO registration (full_name, email_id, password) VALUES (?, ?, ?)";


    public ResultSet selectRecord() throws SQLException {
        Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        Statement st = connection.prepareStatement(SELECT_QUERY, ResultSet.TYPE_SCROLL_SENSITIVE);
        ResultSet rs = st.executeQuery(SELECT_QUERY);
        return rs;
    }

    public void insertRecord(String fullName, String emailId, String password) throws SQLException {

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             PreparedStatement preparedStatement1 = connection.prepareStatement(SELECT_QUERY);
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, emailId);
            preparedStatement.setString(3, password);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
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
