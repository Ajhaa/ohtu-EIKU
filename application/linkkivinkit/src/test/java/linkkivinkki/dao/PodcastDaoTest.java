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
import linkkivinkki.domain.Podcast;

public class PodcastDaoTest {

    private Database db;
    private PodcastDao dao;
    private Connection conn;

    @Before
    public void setUp() throws SQLException {
        this.db = new Database("jdbc:sqlite:test.db");
        this.dao = new PodcastDao(db);
        this.conn = db.getConnection();
    }

    @After
    public void tearDown() throws SQLException {
        File databaseFile = new File("test.db");
        databaseFile.delete();
        conn.close();
    }

    @Test
    public void podcastsAreAddedCorrectly() {
        Podcast p = new Podcast("name", "title", "desc");
        assertTrue(dao.add(p));
    }

    @Test
    public void tryingToAddNonPodcastFails() {
        Book b = new Book("author", "title", "desc");
        assertFalse(dao.add(b));
    }

    @Test
    public void podcastsAreFetchedProperly() throws SQLException {
        Podcast p = new Podcast("name", "title", "desc");
        dao.add(p);
        ArrayList<Podcast> podcasts = dao.findAll();
        assertEquals(p.getName(), podcasts.get(0).getName());
        assertEquals(p.getTitle(), podcasts.get(0).getTitle());
        assertEquals(p.getDescription(), podcasts.get(0).getDescription());
    }

    @Test
    public void podcastsAreRemovedProperly() throws SQLException {
        Podcast p = new Podcast("name", "title", "desc");
        dao.add(p);
        ArrayList<Podcast> podcasts = dao.findAll();
        p = podcasts.get(0);
        assertTrue(dao.delete(p.getId()));
    }

    @Test
    public void findOneFindsOne() throws SQLException {
        Podcast p = new Podcast("name", "title", "desc");
        dao.add(p);
        p = new Podcast("nimi", "otsikko", "kuvaus");
        dao.add(p);

        Podcast found = dao.findOne(2);
        assertEquals(2, found.getId());
        assertEquals("nimi", found.getName());
        assertEquals("otsikko", found.getTitle());
        assertEquals("kuvaus", found.getDescription());
    }

    @Test
    public void findOneReturnsNullIfNoneFound() throws SQLException {
        Podcast found = dao.findOne(71);
        assertEquals(null, found);
    }

        @Test
    public void updateReturnsFalseIfNotABook() {
        assertFalse(dao.update(new Book("name", "title", "desc")));
    }

    @Test
    public void updateReturnsTrueOnSuccess() throws SQLException {
        Podcast p = new Podcast("name", "title", "desc");
        dao.add(p);

        p = dao.findOne(1);

        p.setName("newName");
        p.setTitle("newTitle");

        boolean success = dao.update(p);

        assertEquals(true, success);
    }

    @Test
    public void updateReturnsFalseIfContentDoesNotExist() throws SQLException {
        assertFalse(dao.update(new Podcast("name", "title", "desc")));
    }
}
