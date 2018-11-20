package linkkivinkki.domain;

public class InternetContent extends Item {
    private String url;

    public InternetContent(String title, String url) {
        super();
        this.url = url;
        this.setTitle(title);
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