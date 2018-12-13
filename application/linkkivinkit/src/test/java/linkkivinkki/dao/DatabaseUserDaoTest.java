package linkkivinkki.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import linkkivinkki.data.Database;
import linkkivinkki.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DatabaseUserDaoTest {
    
    private Database db;
    private UserDao dao;
    private Connection conn;

    @Before
    public void setUp() throws SQLException {
        this.db = new Database("jdbc:sqlite:test.db");
        this.dao = new DatabaseUserDao(db);
        this.conn = db.getConnection();
    }

    @After
    public void tearDown() throws SQLException {
        File databaseFile = new File("test.db");
        databaseFile.delete();
        conn.close();
    }
    
    @Test
    public void usersAreAddedCorrectly() {
        assertTrue(dao.add(new User()));
    }

    @Test
    public void tryingToCreateDuplicateUserFails() {
        dao.add(new User());
        assertFalse(dao.add(new User()));
    }

    @Test
    public void usersAreFetchedProperly() throws SQLException {
        dao.add(new User());
        dao.add(new User("testi2"));
        assertEquals(2, dao.findAll().size());
    }

    @Test
    public void booksAreRemovedProperly() throws SQLException {
        dao.add(new User());
        assertTrue(dao.delete(1));
    }

    @Test
    public void findOneFindsOne() throws SQLException {
        dao.add(new User());
        assertEquals(User.class, dao.findOne(1).getClass());
    }

    @Test
    public void findOneReturnsNullIfIdIsInvalid() throws SQLException {
        assertEquals(null, dao.findOne(1));
    }
    
    @Test
    public void findOneByNameFindsOne() throws SQLException {
        dao.add(new User());
        assertEquals(User.class, dao.findOneByName("kÄytTäjÄ").getClass());
    }
    
    @Test
    public void findOneByNameReturnsNullIfNameIsInvalid() throws SQLException {
        assertEquals(null, dao.findOneByName("käyttäjö"));
    }
}
