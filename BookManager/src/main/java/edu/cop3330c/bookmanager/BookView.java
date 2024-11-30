// BookView.java
// D. Singletary
// 11/20/24
// Book view component in MVC example

package edu.cop3330c.bookmanager;

import java.util.List;

public class BookView {
    public void displayMenu() {
        System.out.println("\nBook Manager:");
        System.out.println("1. Add a book");
        System.out.println("2. View all books");
        System.out.println("3. Save books to file");
        System.out.println("4. Load books from file");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    public void displayBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books in the list.");
        } else {
            System.out.println("\nBooks:");
            for (int i = 0; i < books.size(); i++) {
                System.out.println((i + 1) + ". " + books.get(i).getTitle());
            }
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
