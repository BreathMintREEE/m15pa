// BookController.java
// D. Singletary
// 11/20/24
// Book controller component in MVC example

package edu.cop3330c.bookmanager;

import java.util.List;

// BookController is where our business logic can be found
public class BookController {

    private final BookRepository repository;

    public BookController() {
        this.repository = new BookRepository();
        // Set up the database
        this.repository.initializeDatabase();
    }

    public void addBook(String title, String author) {
        Book book = new Book(null, title, author);
        repository.save(book);
        System.out.println("Book added: " + book);
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book getBookById(Long id) {
        return repository.findById(id);
    }

    public void deleteBook(Long id) {
        Book book = repository.findById(id);
        if (book != null) {
            repository.delete(book);
            System.out.println("Book deleted: " + book);
        } else {
            System.out.println("Book with ID " + id + " not found.");
        }
    }

    public static void main(String[] args) {
        BookController controller = new BookController();

        // Add books
        controller.addBook("Effective Java", "Joshua Bloch");
        controller.addBook("Clean Code", "Robert C. Martin");

        // List all books
        System.out.println("All Books: " + controller.getAllBooks());

        // Find a book by ID
        Book book = controller.getBookById(1L);
        System.out.println("Found Book: " + book);

        // Delete a book
        controller.deleteBook(1L);
        System.out.println("Books after deletion: " + controller.getAllBooks());
    }
}
