package linkkivinkki.domain;

public class Book extends Item {
    private String author;
    private String title;

    public Book(String author, String title) {
        super();
        this.author = author;
        this.title = title;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return getId() + " " + title + " by " + author;
    }
}