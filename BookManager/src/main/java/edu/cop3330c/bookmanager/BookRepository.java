// BookRepository.java
// R. Tran
// 8/17/26
// Database code for book manager

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
        String insertSQL = "INSERT INTO books (title, author) VALUES (?, ?)";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     insertSQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    book.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> findAll() {
        // TODO: Write a prepared statement or a simple query to retrieve all books.
        // Example: Use connection.createStatement() with a SELECT SQL query.
        // Loop through the ResultSet to create Book objects and add them to the list.
        // return new ArrayList<>(); // TODO: replace this with the retrieved data.
        List<Book> books = new ArrayList<>();
        String selectSQL = "SELECT id, title, author FROM books";
        try (Connection connection = DatabaseUtility.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public Book findById(Long id) {
        // TODO: Write a prepared statement to find a book by its ID.
        // Example: Use connection.prepareStatement() with a SELECT query and a WHERE clause.
        // Extract the book details from the ResultSet.
        // return null; // TODO: replace this with the retrieved data.
        String selectSQL = "SELECT id, title, author FROM books WHERE id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {

            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Book(
                            resultSet.getLong("id"),
                            resultSet.getString("title"),
                            resultSet.getString("author")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Book book) {
        // TODO: Write a prepared statement to delete a book by its ID.
        // Example: Use connection.prepareStatement() with a DELETE query.
        // Set the book's ID in the prepared statement.
        String deleteSQL = "DELETE FROM books WHERE id = ?";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {

            preparedStatement.setLong(1, book.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
