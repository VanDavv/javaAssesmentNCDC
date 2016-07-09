package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {

    @JsonProperty
    private final String author;
    @JsonProperty
    private final String name;
    @JsonProperty
    private final Integer isbn;

    public Book(String author, String name, Integer isbn) {
        this.author = author;
        this.name = name;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public Integer getIsbn() {
        return isbn;
    }
}
