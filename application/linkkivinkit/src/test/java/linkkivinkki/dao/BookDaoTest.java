package linkkivinkki.dao;

import linkkivinkki.data.Database;
import linkkivinkki.domain.Book;
import linkkivinkki.domain.Podcast;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import linkkivinkki.domain.User;

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
        assertEquals(b.getCreationDate(), books.get(0).getCreationDate());
        assertEquals(b.getUserId(), books.get(0).getUserId());
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
        Date d = b.getCreationDate();
        dao.add(b);

        b = dao.findOne(2);
        assertEquals(2, b.getId());
        assertEquals("hei", b.getAuthor());
        assertEquals("maailma", b.getTitle());
        assertEquals("kuvaus", b.getDescription());
        assertEquals(-1, b.getUserId()); // -1 is the 'default' user id of an item
        assertEquals(d, b.getCreationDate());
    }

    @Test
    public void findOneReturnsNullIfIdIsInvalid() throws SQLException {
        Book b = dao.findOne(10);
        assertEquals(null, b);
    }

    @Test
    public void updateReturnsFalseIfNotABook() {
        assertFalse(dao.update(new Podcast("name", "title", "desc")));
    }

    @Test
    public void updateReturnsTrueOnSuccess() throws SQLException {
        Book b = new Book("hello", "world", "desc");
        dao.add(b);

        b = dao.findOne(1);

        b.setAuthor("newAuthor");
        b.setTitle("newTitle");

        boolean success = dao.update(b);

        assertEquals(true, success);
    }

    @Test
    public void updateReturnsFalseIfBookDoesNotExist() throws SQLException {
        assertFalse(dao.update(new Book("author", "title", "desc")));
    }
    
    @Test
    public void findAllByUserIdReturnsCorrectItems() throws SQLException {
        User u1 = new User("käyttäjä1");
        User u2 = new User("käyttäjä2");
        u1.setId(1);
        u2.setId(2);
        
        Book b1 = new Book("hello", "world", "desc");
        b1.setUserId(u1.getId());
        Book b2 = new Book("hei", "maailma", "kuvaus");
        b2.setUserId(u2.getId());
        Book b3 = new Book("abc", "def", "ghi");
        b3.setUserId(u1.getId());
        
        dao.add(b1);
        dao.add(b2);
        dao.add(b3);
        
        List<Book> userOneBooks = dao.findAllByUserId(u1.getId());
        assertEquals(2, userOneBooks.size());
        assertEquals(u1.getId(), userOneBooks.get(0).getUserId());
        assertEquals(u1.getId(), userOneBooks.get(1).getUserId());
    }
}
