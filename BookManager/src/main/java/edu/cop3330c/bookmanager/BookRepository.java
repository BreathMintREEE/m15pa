package edu.cop3330c.bookmanager;

// add DB imports
import java.sql.*;
import java.util.*;

// utility class for managing DB connection
class DatabaseUtility {

    private static final String JDBC_URL =
            "jdbc:h2:mem:bookdb;DB_CLOSE_DELAY=-1";
    private static final String JDBC_USER = "sa";
    private static final String JDBC_PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
}

public class BookRepository {

    public void initializeDatabase() {
        try (Connection connection = DatabaseUtility.getConnection();
             Statement statement = connection.createStatement()) {

            String createTableSQL = "CREATE TABLE IF NOT EXISTS books (" +
                                     "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                                     "title VARCHAR(255) NOT NULL, " +
                                     "author VARCHAR(255))";
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(Book book) {
        // TODO: Write a prepared statement to insert a new book into the database.
        // Example: Use connection.prepareStatement with an INSERT SQL query.
        // Remember to set the title and author fields using preparedStatement.setString().
        // Use the generated keys to set the book's ID.
    }

    public List<Book> findAll() {
        // TODO: Write a prepared statement or a simple query to retrieve all books.
        // Example: Use connection.createStatement() with a SELECT SQL query.
        // Loop through the ResultSet to create Book objects and add them to the list.
        return new ArrayList<>(); // TODO: replace this with the retrieved data.
    }

    public Book findById(Long id) {
        // TODO: Write a prepared statement to find a book by its ID.
        // Example: Use connection.prepareStatement() with a SELECT query and a WHERE clause.
        // Extract the book details from the ResultSet.
        return null; // TODO: replace this with the retrieved data.
    }

    public void delete(Book book) {
        // TODO: Write a prepared statement to delete a book by its ID.
        // Example: Use connection.prepareStatement() with a DELETE query.
        // Set the book's ID in the prepared statement.
    }
}
