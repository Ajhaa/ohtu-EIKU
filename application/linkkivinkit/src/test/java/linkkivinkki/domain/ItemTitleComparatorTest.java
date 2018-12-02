package linkkivinkki.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class ItemTitleComparatorTest {
    
    private ItemTitleComparator comparator;
    private Item i1;
    private Item i2;
    private Item i3;
    
    public ItemTitleComparatorTest() {
        comparator = new ItemTitleComparator();
        i1 = new Item("Aapinen", "");
        i2 = new Item("Ötökkäopas", "");
        i3 = new Item("Aapinen", "Mutta tällä onkin kuvaus!");
        
    }

    @Test
    public void comparatorReturnsMinusOneIfFirstItemComesFirstAlphabetically() {
        assertEquals(-1, comparator.compare(i1, i2));
    }
    
    @Test
    public void comparatorReturnsOneIfSecondItemComesFirstAlphabetically() {
        assertEquals(1, comparator.compare(i2, i1));
    }
    
    @Test
    public void comparatorReturnsZeroIfNamesAreEqual() {
        assertEquals(0, comparator.compare(i1, i3));
    }
    
}
