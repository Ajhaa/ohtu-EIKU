package linkkivinkki.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class PodcastTest {
    
    private Podcast podcast;
    
    @Before
    public void setUp() {
        this.podcast = new Podcast("name", "title");
    }
    
    @Test
    public void canSetNameOfPodcast() {
        podcast.setName("othername");
        assertEquals("othername", podcast.getName());
    }
    
    @Test
    public void canSetTitleOfPodcast() {
        podcast.setTitle("othertitle");
        assertEquals("othertitle", podcast.getTitle());
    }
    
    @Test
    public void canSetDescriptionOfPodcast() {
        podcast.setDescription("otherdescription");
        assertEquals("otherdescription", podcast.getDescription());
    }
    
    @Test
    public void toStringOfPodcastWorks() {
        String shouldBe = "-1 name - title" + "\n" + "";
        assertEquals(shouldBe, podcast.toString());
    }
    
}
