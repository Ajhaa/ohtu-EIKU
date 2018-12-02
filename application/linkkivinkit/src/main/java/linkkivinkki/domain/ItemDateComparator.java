package linkkivinkki.domain;

import java.util.Comparator;

public class ItemDateComparator implements Comparator<Item> {

    /**
     * Compare the creation dates of two items
     * @param i1
     * @param i2
     * @return -1 if i1 was more recent, 1 if i2 was more recent, 0 if equal
     */
    @Override
    public int compare(Item i1, Item i2) {
        if (i1.getCreationDate().getTime() > i2.getCreationDate().getTime()) {
            return -1;
        } else if (i1.getCreationDate().getTime() < i2.getCreationDate().getTime()) {
            return 1;
        } else {
            return 0; // This likely never happens
        }
    }
    
}
