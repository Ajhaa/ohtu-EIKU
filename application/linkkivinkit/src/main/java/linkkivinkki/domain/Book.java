package linkkivinkki.domain;

import linkkivinkki.io.Color;

public class Book extends Item {
    private String author;

    public Book(String author, String title, String description) {
        super(title, description);
        this.author = author;
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
        return Color.cyanText("" + getId()) + " " + this.getTitle() + " by " + author;
    }
}