package linkkivinkki.domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class UserTest {

    @Before
    public void setUp() {

    }

    @Test
    public void userIsInitializedCorrectly() {
        List<Item> items = new ArrayList<>();
        items.add(new Book("kirjailija", "kirja", "kuvaus"));

        User u = new User(1, "testikäyttäjä", items);
        assertEquals(1, u.getId());
        assertEquals("testikäyttäjä", u.getUsername());
        assertEquals(items, u.getItems());
    }

    @Test
    public void userIsInitializedCorrectlyWithUsernameAndItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Book("kirjailija", "kirja", "kuvaus"));
        User u = new User("tunnus", items);
        
        assertEquals(-1, u.getId());
        assertEquals("tunnus", u.getUsername());
        assertEquals(items, u.getItems());
    }
    
    @Test
    public void userIsInitializedCorrectlyWithJustUsername() {
        User u = new User("tunnus");
        assertEquals("tunnus", u.getUsername());
    }
    
    @Test
    public void dummyUserIsInitialized() {
        User u = new User();
        assertEquals(-1, u.getId());
        assertEquals("kÄytTäjÄ", u.getUsername());
        assertEquals(0, u.getItems().size());
    }

    @Test
    public void idCanBeSet() {
        User u = new User();
        u.setId(2);
        assertEquals(2, u.getId());
    }

    @Test
    public void itemsCanBeSet() {
        User u = new User();
        List<Item> newItems = new ArrayList<>();
        newItems.add(new Podcast("hello", "world", "helloworld"));
        newItems.add(new InternetContent("sivu", "www.fi", "dank memes"));
        u.setItems(newItems);
        assertEquals(2, u.getItems().size());
    }

}
