package vandavv.resources;

import dao.BookDAO;
import model.Book;
import vandavv.views.ListView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/")
@Produces("text/html")
public class ListResource {

    private final BookDAO dao;

    public ListResource(BookDAO dao) {

        this.dao = dao;
    }

    @GET
    public ListView getAllBooks() {
        final List<Book> books = dao.getAllBooks();

        return new ListView(books);
    }
}
