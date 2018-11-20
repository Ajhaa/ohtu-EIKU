
package linkkivinkki.domain;

import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ItemTest {
    private Item item;
    
    @Before
    public void setUp() {
        ArrayList<String> list = new ArrayList<>();
        list.add("tag1");
        list.add("tag2");
        this.item = new Item(1, list, false, new Date()) ;
    }
    
    @After
    public void tearDown() {
        
    }
    
    @Test
    public void itemConstructorWithoutArgumentsReturnsItem() {
        Item i = new Item();
        assertTrue( Item.class == i .getClass());
    }
    
    @Test
    public void canAddOneTagToItem() {
        item.addTag("tag3");
        assertEquals(3, item.getTags().size());
    }
    
    @Test
    public void canAddManyTagsToItem() {
        ArrayList<String> tags = new ArrayList<>();
        tags.add("tag3");        
        tags.add("tag4");
        tags.add("tag5");

        item.addTags(tags);
        assertEquals(5, item.getTags().size());
    }
    
    @Test
    public void canRemoveTagFromItem() {
        item.removeTag("tag2");
        assertEquals(1, item.getTags().size());
    }
    
    @Test
    public void removingNonExistentTagFromItemDoesNothing() {
        item.removeTag("nonexistent");
        assertEquals(2, item.getTags().size());
    }
    
    @Test
    public void canGetReadDateFromItem() {
        assertEquals(Date.class, item.getReadDate().getClass());
    }
    
    @Test
    public void canSetReadDateOfItem() {
        Date date = new Date();
        item.setReadDate(date);
        assertEquals(date, item.getReadDate());
    }
    
    @Test
    public void canGetIsReadFromItem() {
        assertEquals(false, item.isRead());
    }
    
    @Test
    public void canSetReadOfItem() {
        boolean read = true;
        item.setRead(read);
        assertTrue(item.isRead());
    }
    
}
