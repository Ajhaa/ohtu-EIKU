package linkkivinkki.domain;

import java.util.Comparator;

public class ItemTitleComparator implements Comparator<Item> {

    /**
     * Compare the names of two items
     *
     * @param i1
     * @param i2
     * @return -1 if i1 comes first alphabetically, 1 if i2 does, 0 if equal
     */
    @Override
    public int compare(Item i1, Item i2) {
        int comparison = i1.getTitle().compareTo(i2.getTitle());
        if (comparison < 0) {
            return -1;
        } else if (comparison > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
