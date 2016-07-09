package vandavv.resources;

import model.Book;
import vandavv.views.ListView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.LinkedList;
import java.util.List;

@Path("/")
@Produces("text/html")
public class ListResource {

    @GET
    public ListView getList() {
        List<Book> books = new LinkedList<>();
        books.add(new Book("author1", "name1", 111));
        books.add(new Book("author2", "name2", 222));
        return new ListView(books);
    }
}
