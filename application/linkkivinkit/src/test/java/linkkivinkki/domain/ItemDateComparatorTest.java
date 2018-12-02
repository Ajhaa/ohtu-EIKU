package linkkivinkki.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class ItemDateComparatorTest {
    
    private ItemDateComparator comparator;
    private Item i1;
    private Item i2;
    private Item i3;
    
    public ItemDateComparatorTest() {
        comparator = new ItemDateComparator();
        i1 = new Item();
        i2 = new Item();
        
        // Artificially offset the creation times by 1000 milliseconds
        // This is to prevent errors due to the processor creating the items above during the same millisecond
        i2.getCreationDate().setTime(i2.getCreationDate().getTime() + 1000);
        
        // Finally create an item with an equal creation date to i2
        // This will likely never happen in actual use of the software
        i3 = new Item();
        i3.setCreationDate(i2.getCreationDate());
        
    }

    @Test
    public void comparatorReturnsOneIfFirstItemIsOlder() {
        assertEquals(1, comparator.compare(i1, i2));
    }
    
    @Test
    public void comparatorReturnsMinusOneIfSecondItemIsOlder() {
        assertEquals(-1, comparator.compare(i2, i1));
    }
    
    @Test
    public void comparatorReturnsZeroIfCreationDatesAreEqual() {
        assertEquals(0, comparator.compare(i2, i3));
    }
    
}
