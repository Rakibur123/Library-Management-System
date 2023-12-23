/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.management.system;

/**
 *
 * @author Rakibur Rahman
 */


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
    private final StringProperty id;
    private final StringProperty title;
    private final StringProperty author;
    private final StringProperty year;
    private final StringProperty page;

    public Book(String id, String title, String author, String year, String page) {
        this.id = new SimpleStringProperty(id);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.year = new SimpleStringProperty(year);
        this.page = new SimpleStringProperty(page);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getAuthor() {
        return author.get();
    }

    public StringProperty authorProperty() {
        return author;
    }

    public String getYear() {
        return year.get();
    }

    public StringProperty yearProperty() {
        return year;
    }

    public String getPage() {
        return page.get();
    }

    public StringProperty pageProperty() {
        return page;
    }
}

