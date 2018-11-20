package linkkivinkki.domain;

public class Podcast extends Item {
    private String name;
    private String description;

    public Podcast(String name, String title, String description) {
        super();
        this.name = name;
        this.description = description;
        this.setTitle(title);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return getId() + " " + name + " - " + this.getTitle() + "\n" + description;
    }
}