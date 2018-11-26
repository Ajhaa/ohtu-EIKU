package linkkivinkki.dao;

import linkkivinkki.data.Database;
import linkkivinkki.domain.Book;
import linkkivinkki.domain.Podcast;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
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
        Book b = new Book("hello", "world", "desc");
        assertTrue(dao.add(b));
    }

    @Test
    public void tryingToAddNonBookFails() {
        Podcast p = new Podcast("name", "title", "desc");
        assertFalse(dao.add(p));
    }

    @Test
    public void booksAreFetchedProperly() throws SQLException {
        Book b = new Book("hello", "world", "desc");
        dao.add(b);
        ArrayList<Book> books = dao.findAll();
        assertEquals(b.getAuthor(), books.get(0).getAuthor());
        assertEquals(b.getTitle(), books.get(0).getTitle());
        assertEquals(b.getDescription(), books.get(0).getDescription());
    }

    @Test
    public void booksAreRemovedProperly() throws SQLException {
        Book b = new Book("hello", "world", "desc");
        dao.add(b);
        ArrayList<Book> books = dao.findAll();
        b = books.get(0);
        assertTrue(dao.delete(b.getId()));
    }

    @Test
    public void findOneFindsOne() throws SQLException {
        Book b = new Book("hello", "world", "desc");
        dao.add(b);
        b = new Book("hei", "maailma", "kuvaus");
        dao.add(b);

        b = dao.findOne(2);
        assertEquals(2, b.getId());
        assertEquals("hei", b.getAuthor());
        assertEquals("maailma", b.getTitle());
        assertEquals("kuvaus", b.getDescription());
    }

    @Test
    public void findOneReturnsNullIfIdIsInvalid() throws SQLException {
        Book b = dao.findOne(10);
        assertEquals(null, b);
    }

    @Test
    public void updateReturnsTrueOnSuccess() throws SQLException {
        Book b = new Book("hello", "world", "desc");
        dao.add(b);

        b = dao.findOne(1);

        HashMap<String, String> updates = new HashMap<>();
        updates.put("title", "new_title");
        updates.put("author", "yolo");

        boolean success = dao.update(updates, b.getId());

        assertEquals(true, success);
    }

    @Test
    public void updateUpdatesWithValidId() throws SQLException {
        Book b = new Book("hello", "world", "desc");
        dao.add(b);

        b = dao.findOne(1);

        HashMap<String, String> updates = new HashMap<>();
        updates.put("title", "new_title");

        dao.update(updates, b.getId());
        b = dao.findOne(1);

        assertEquals("new_title", b.getTitle());
    }

    @Test
    public void updateReturnsFalseWithInvalidId() throws SQLException {
        boolean success = dao.update(new HashMap<>(), 4);
        assertEquals(success, false);
    }
}
