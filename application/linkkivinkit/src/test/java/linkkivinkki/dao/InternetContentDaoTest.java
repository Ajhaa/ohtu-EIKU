package linkkivinkki.dao;

import linkkivinkki.data.Database;
import linkkivinkki.domain.InternetContent;
import linkkivinkki.domain.Book;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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
    }

    @Test
    public void internetContentsAreRemovedProperly() throws SQLException {
        InternetContent content = new InternetContent("title", "www.com", "desc");
        dao.add(content);
        ArrayList<InternetContent> contents = dao.findAll();
        content = contents.get(0);
        assertTrue(dao.delete(content.getId()));
    }
}
