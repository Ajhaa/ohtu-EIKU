package linkkivinkki.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {

    public BookTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void bookIsInitializedCorrectly() {
        Book b = new Book("hello", "world");
        assertEquals("hello", b.getAuthor());
        assertEquals("world", b.getTitle());
    }

    @Test
    public void authorCanBeSet() {
        Book b = new Book("hello", "world");
        b.setAuthor("heyo");
        assertEquals("heyo", b.getAuthor());
    }

    @Test
    public void titleCanBeSet() {
        Book b = new Book("hello", "world");
        b.setTitle("maailma");
        assertEquals("maailma", b.getTitle());
    }
    
    @Test
    public void toStringReturnsAProperString() {
        Book b = new Book("hello", "world");
        String expected = "-1 world by hello";
        assertEquals(expected, b.toString());
    }

}
