package linkkivinkki.domain;

import linkkivinkki.io.Color;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class PodcastTest {

    private Podcast podcast;

    @Before
    public void setUp() {
        podcast = new Podcast("name", "title", "description");
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
        String shouldBe = Color.cyanText("-1.")+ " name - title";
        assertEquals(shouldBe, podcast.toString());
    }

}
