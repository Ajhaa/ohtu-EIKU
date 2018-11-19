package domain;

public class InternetContent extends Item {
    private String title;
    private String url;

    public InternetContent(String title, String url) {
        super();
        this.title = title;
        this.url = url;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    @Override
    public String toString() {
        return title + " (" + url + ")";
    }


}