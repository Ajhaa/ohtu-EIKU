package linkkivinkki.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import linkkivinkki.data.Database;
import linkkivinkki.domain.Book;
import linkkivinkki.domain.InternetContent;
import linkkivinkki.domain.User;
import static org.junit.Assert.assertEquals;

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
        assertEquals(content.getUserId(), contents.get(0).getUserId());
    }

    @Test
    public void internetContentsAreRemovedProperly() throws SQLException {
        InternetContent content = new InternetContent("title", "www.com", "desc");
        dao.add(content);
        ArrayList<InternetContent> contents = dao.findAll();
        content = contents.get(0);
        assertTrue(dao.delete(content.getId(), -1));
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
        assertEquals(-1, found.getUserId()); // -1 is the 'default' user id of an item
        assertEquals("kuvaus", found.getDescription());
    }

    @Test
    public void findOneReturnsNullIfNotFound() throws SQLException {
        InternetContent content = dao.findOne(25);
        assertEquals(null, content);
    }

    @Test
    public void updateReturnsFalseIfNotABook() {
        assertFalse(dao.update(new Book("name", "title", "desc")));
    }

    @Test
    public void updateReturnsTrueOnSuccess() throws SQLException {
        InternetContent content = new InternetContent("title", "www.com", "desc");
        dao.add(content);

        content = dao.findOne(1);

        content.setTitle("newTitle");
        content.setUrl("IdkWhyYouWouldWantToReplaceThis.url");

        boolean success = dao.update(content);

        assertEquals(true, success);
    }

    @Test
    public void updateReturnsFalseIfContentDoesNotExist() throws SQLException {
        assertFalse(dao.update(new InternetContent("title", "www.com", "desc")));
    }

    @Test
    public void findAllByUserIdReturnsCorrectItems() throws SQLException {
        User u1 = new User("käyttäjä1");
        User u2 = new User("käyttäjä2");
        u1.setId(1);
        u2.setId(2);

        InternetContent ic1 = new InternetContent("hello", "world.com", "desc");
        ic1.setUserId(u1.getId());
        InternetContent ic2 = new InternetContent("hei", "maailma.fi", "kuvaus");
        ic2.setUserId(u2.getId());
        InternetContent ic3 = new InternetContent("abc", "def.xyz", "ghi");
        ic3.setUserId(u1.getId());

        dao.add(ic1);
        dao.add(ic2);
        dao.add(ic3);

        List<InternetContent> userOneLinks = dao.findAllByUserId(u1.getId());
        assertEquals(2, userOneLinks.size());
        assertEquals(u1.getId(), userOneLinks.get(0).getUserId());
        assertEquals(u1.getId(), userOneLinks.get(1).getUserId());
    }
}
