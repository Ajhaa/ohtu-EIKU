package linkkivinkki.domain;

public class InternetContent extends Item {
    private String url;

    public InternetContent(String title, String url, String description) {
        super(title, description);
        this.url = url;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return getId() + " " + this.getTitle() + " (" + url + ")";
    }


}