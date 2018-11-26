package linkkivinkki.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import linkkivinkki.data.Database;
import linkkivinkki.domain.Book;
import linkkivinkki.domain.InternetContent;

public class InternetContentDaoTest {

    private Database db;
    private InternetContentDao dao;
    private Connection conn;

    @Before
    public void setUp() throws SQLException {
        this.db = new Database("jdbc:sqlite:test.db");
        this.dao = new InternetContentDao(db);
        this.conn = db.getConnection();
    }

    @After
    public void tearDown() throws SQLException {
        File databaseFile = new File("test.db");
        databaseFile.delete();
        conn.close();
    }

     @Test
    public void internetContentsAreAddedCorrectly() {
        InternetContent content = new InternetContent("title", "www.com", "desc");
        assertTrue(dao.add(content));
    }

    @Test
    public void tryingToAddNonInternetContentFails() {
        Book b = new Book("author", "title", "desc");
        assertFalse(dao.add(b));
    }

    @Test
    public void internetContentsAreFetchedProperly() throws SQLException {
        InternetContent content = new InternetContent("title", "www.com", "desc");
        dao.add(content);
        ArrayList<InternetContent> contents = dao.findAll();
        assertEquals(content.getTitle(), contents.get(0).getTitle());
        assertEquals(content.getUrl(), contents.get(0).getUrl());
        assertEquals(content.getDescription(), contents.get(0).getDescription());
    }

    @Test
    public void internetContentsAreRemovedProperly() throws SQLException {
        InternetContent content = new InternetContent("title", "www.com", "desc");
        dao.add(content);
        ArrayList<InternetContent> contents = dao.findAll();
        content = contents.get(0);
        assertTrue(dao.delete(content.getId()));
    }

    @Test
    public void findOneFindsOne() throws SQLException {
        InternetContent content = new InternetContent("title", "www.com", "desc");
        dao.add(content);
        content = new InternetContent("otsikko", "www.fi", "kuvaus");
        dao.add(content);

        InternetContent found = dao.findOne(2);
        assertEquals(2, found.getId());
        assertEquals("otsikko", found.getTitle());
        assertEquals("www.fi", found.getUrl());
        assertEquals("kuvaus", found.getDescription());
    }

    @Test
    public void findOneReturnsNullIfNotFound() throws SQLException {
        InternetContent content = dao.findOne(25);
        assertEquals(null, content);
    }

    @Test
    public void updateReturnsTrueOnSuccess() throws SQLException {
        InternetContent b = new InternetContent("hello", "world.fi", "desc");
        dao.add(b);

        b = dao.findOne(1);

        HashMap<String, String> updates = new HashMap<>();
        updates.put("title", "new_title");
        updates.put("url", "yolo.fi");

        boolean success = dao.update(updates, b.getId());

        assertEquals(true, success);
    }

    @Test
    public void updateUpdatesWithValidId() throws SQLException {
        InternetContent b = new InternetContent("hello", "world.fi", "desc");
        dao.add(b);

        b = dao.findOne(1);

        HashMap<String, String> updates = new HashMap<>();
        updates.put("title", "new_title");

        dao.update(updates, b.getId());
        b = dao.findOne(1);

        assertEquals("new_title", b.getTitle());
    }

    @Test
    public void updateReturnsFalseWithEmptyItems() throws SQLException {
        InternetContent b = new InternetContent("hello", "world.fi", "desc");
        dao.add(b);

        boolean success = dao.update(new HashMap<>(), 1);
        assertEquals(success, false);
    }
}
