package mapper;

import model.Book;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements ResultSetMapper<Book> {
    @Override
    public Book map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Book(r.getString("author"), r.getString("title"), r.getInt("isbn"));
    }
}
