package vandavv.views;

import io.dropwizard.views.View;
import model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ListView extends View {
    private final static Logger LOG = LoggerFactory.getLogger(ListView.class);

    private final List<Book> books;

    public ListView(List<Book> books) {
        super("list.mustache");
        this.books = books;

        LOG.debug("Created ListView with book list: " + books);
    }

    public List<Book> getBooks() {
        return books;
    }
}
