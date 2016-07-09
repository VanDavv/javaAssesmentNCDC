package dao;

import mapper.BookMapper;
import model.Book;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(BookMapper.class)
public interface BookDAO {

    @SqlQuery("SELECT isbn, author, title FROM books")
    List<Book> getAllBooks();

    @SqlUpdate("INSERT INTO books VALUES (:isbn, :author, :title)")
    void addBook(@BindBean Book book);
}
