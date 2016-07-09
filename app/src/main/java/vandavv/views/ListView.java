package vandavv.views;

import io.dropwizard.views.View;
import model.Book;

import java.util.List;

public class ListView extends View {

    private final List<Book> books;

    public ListView(List<Book> books) {
        super("list.mustache");
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }
}
