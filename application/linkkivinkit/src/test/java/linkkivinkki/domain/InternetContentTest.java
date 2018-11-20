package linkkivinkki.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class InternetContentTest {
    private InternetContent content;
    
    @Before
    public void setUp() {
        this.content = new InternetContent("title", "url.fi");
    }
    
    @Test
    public void canSetTitleOfInternetContent() {
        content.setTitle("othertitle");
        assertEquals("othertitle", content.getTitle());
    }
    
    @Test
    public void canSetUrlOfInternetContent() {
        content.setUrl("google.com");
        assertEquals("google.com", content.getUrl());
    }
    
    @Test
    public void toStringOfInternetContentWorks() {
        assertEquals("title (url.fi)", content.toString());
    }
}
