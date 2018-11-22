package linkkivinkki.dao;

import linkkivinkki.data.Database;
import linkkivinkki.domain.Podcast;
import linkkivinkki.domain.Book;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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

}
