package linkkivinkki.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Item {
    private int id;
    private String title;
    private List<String> tags;
    private boolean read;
    private Date readDate;
    private String description;

    public Item(int id, String title, List<String> tags, boolean read, Date read_date, String description) {
        this.id = id;
        this.title = title;
        this.tags = tags;
        this.read = read;
        this.readDate = read_date;
        this.description = description;
    }

    public Item(String title, String description) {
        this(-1, title, new ArrayList<>(), false, null, description);
    }

    public Item() {
        this(-1, "", new ArrayList<>(), false, null, "");
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
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
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
     * @return the description
     */
    public String getDescription() {
        return description;
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

    /**
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}