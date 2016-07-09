package vandavv.resources;

import dao.BookDAO;
import model.Book;
import org.hibernate.validator.constraints.NotEmpty;
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

    private final BookDAO dao;

    public AddResource(BookDAO dao) {
        this.dao = dao;
    }

    @GET
    public AddView getFormView() {
        return new AddView();
    }

    @POST
    public Response addBook(@NotEmpty @FormParam("author") String author,
                            @NotEmpty @FormParam("title") String title,
                            @NotNull @FormParam("isbn") Integer isbn) {

        final Book book = new Book(author, title, isbn);

        dao.addBook(book);

        final URI redirectURI = UriBuilder.fromResource(ListResource.class).build();
        return Response.seeOther(redirectURI).build();
    }
}
