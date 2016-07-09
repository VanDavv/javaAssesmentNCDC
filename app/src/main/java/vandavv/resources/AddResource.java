package vandavv.resources;

import dao.BookDAO;
import model.Book;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vandavv.views.AddView;

import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@Path("/addRecord")
public class AddResource {

    private static final Logger LOG = LoggerFactory.getLogger(AddResource.class);
    private final BookDAO dao;

    public AddResource(BookDAO dao) {
        this.dao = dao;
        LOG.debug("Created AddResource");
    }

    @GET
    public AddView getFormView() {
        LOG.debug("Rendering AddView (form)");
        return new AddView();
    }

    @POST
    public Response addBook(@NotEmpty @FormParam("author") String author,
                            @NotEmpty @FormParam("title") String title,
                            @NotNull @FormParam("isbn") Integer isbn) {
        LOG.debug("Received data: " + author + " , " + title + " , " + isbn);
        LOG.debug("Creating Book from received data");
        final Book book = new Book(author, title, isbn);

        LOG.debug("Adding book to database");
        dao.addBook(book);

        LOG.debug("Preparing redirect link to ListView");
        final URI redirectURI = UriBuilder.fromResource(ListResource.class).build();

        LOG.debug("Redirecting to " + redirectURI);
        return Response.seeOther(redirectURI).build();
    }
}
