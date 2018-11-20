package linkkivinkki.domain;

public class Book extends Item {
    private String author;

    public Book(String author, String title) {
        super();
        this.author = author;
        this.setTitle(title);
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return getId() + " " + this.getTitle() + " by " + author;
    }
}