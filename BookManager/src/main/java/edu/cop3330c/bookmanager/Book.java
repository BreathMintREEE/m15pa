// Book.java
// D. Singletary
// 11/20/24
// Book model component in MVC example

package edu.cop3330c.bookmanager;

import java.io.Serializable;

public class Book implements Serializable {

    private Long id;
    private String title;
    private String author; // New field

    public Book() {
        // Default constructor
    }

    public Book(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{id=" + id + ", title='" + title + "', author='" + author + "'}";
    }
}