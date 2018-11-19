package linkkivinkki.dao;


import linkkivinkki.data.Database;
import linkkivinkki.domain.Book;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class BookDaoTest {
    
    private Database db;
    private BookDao dao;
    private Connection conn;

    @Before
    public void setUp() throws SQLException {
        this.db = new Database("jdbc:sqlite:test.db");
        this.dao = new BookDao(db);
        this.conn = db.getConnection();
    }

    @After
    public void tearDown() throws SQLException {
        File databaseFile = new File("test.db");
        databaseFile.delete();
        conn.close();
    }

    @Test
    public void booksAreAddedCorrectly() {
        Book b = new Book("hello", "world");
        assertTrue(dao.add(b));
    }
    
    @Test
    public void booksAreFetchedProperly() throws SQLException {
        Book b = new Book("hello", "world");
        dao.add(b);
        ArrayList<Book> books = dao.findAll();
        assertEquals(b.getAuthor(), books.get(0).getAuthor());
        assertEquals(b.getTitle(), books.get(0).getTitle());
    }
    
    @Test
    public void booksAreRemovedProperly() throws SQLException {
        Book b = new Book("hello", "world");
        dao.add(b);
        ArrayList<Book> books = dao.findAll();
        b = books.get(0);
        assertTrue(dao.delete(b.getId()));
    }
}
