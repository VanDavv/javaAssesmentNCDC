package vandavv.resources;

import dao.BookDAO;
import model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vandavv.views.ListView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/")
@Produces("text/html")
public class ListResource {
    private static final Logger LOG = LoggerFactory.getLogger(ListResource.class);
    private final BookDAO dao;

    public ListResource(BookDAO dao) {
        this.dao = dao;
        LOG.debug("ListResource created");
    }

    @GET
    public ListView getAllBooks() {
        LOG.debug("Fetching all books from DAO");
        final List<Book> books = dao.getAllBooks();

        LOG.debug("Rendering ListView");
        return new ListView(books);
    }
}
