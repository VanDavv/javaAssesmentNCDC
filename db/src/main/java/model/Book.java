package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {

    @JsonProperty
    private final String author;
    @JsonProperty
    private final String title;
    @JsonProperty
    private final Integer isbn;

    public Book(String author, String title, Integer isbn) {
        this.author = author;
        this.title = title;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public Integer getIsbn() {
        return isbn;
    }
}
