package linkkivinkki.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Item {
    private int id;
    private User owner;
    private String title;
    private List<String> tags;
    private boolean read;
    private Date dateCreated;
    private Date readDate;
    private String description;

    public Item(int id, User u, String title, List<String> tags, boolean read, Date dateCreated, Date read_date, String description) {
        this.id = id;
        this.owner = u;
        this.title = title;
        this.tags = tags;
        this.read = read;
        this.dateCreated = dateCreated;
        this.readDate = read_date;
        this.description = description;
    }

    public Item(String title, String description) {
        this(-1, new User(), title, new ArrayList<>(), false, new Date(), null, description);
    }

    public Item() {
        this(-1, new User(), "", new ArrayList<>(), false, new Date(), null, "");
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
    
    public void setCreationDate(Date date) {
        this.dateCreated = date;
    }
    
    public Date getCreationDate() {
        return this.dateCreated;
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
    
    public void setOwner(User u) {
        this.owner = u;
    }
    
    public User getOwner() {
        return this.owner;
    }

    public String info() {
        return this.title;
    }
}