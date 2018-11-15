package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Item {
    // could only use read_date, no need for read?
    private List<String> tags;
    private boolean read;
    private Date readDate;

    public Item(List<String> tags, boolean read, Date read_date) {
        this.tags = tags;
        this.read = read;
        this.readDate = read_date;
    }

    public Item() {
        this(new ArrayList<>(), false, null);
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void addTags(List<String> tags) {
        for (String tag : tags) {
            addTag(tag);
        }
    }

    public boolean removeTag(String tag) {
        return tags.remove(tag);
    }

    /**
     * @return the tags
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * @return the readDate
     */
    public Date getReadDate() {
        return readDate;
    }

    /**
     * @return the read
     */
    public boolean isRead() {
        return read;
    }

    /**
     * @param read the read to set
     */
    public void setRead(boolean read) {
        this.read = read;
    }

    /**
     * @param readDate the readDate to set
     */
    public void setReadDate(Date readDate) {
        this.readDate = readDate;
    }


}