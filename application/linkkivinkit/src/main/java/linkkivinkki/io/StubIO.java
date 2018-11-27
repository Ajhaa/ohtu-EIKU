package linkkivinkki.io;

import linkkivinkki.domain.Book;
import linkkivinkki.domain.InternetContent;
import linkkivinkki.domain.Item;
import linkkivinkki.domain.Podcast;
import java.util.ArrayList;
import java.util.List;

public class StubIO implements IO {
    private ArrayList<String> prints;
    private List<String> lines;
    private int i;

    public StubIO(List<String> lines) {
        this.lines = lines;
        prints = new ArrayList<>();
    }

    @Override
    public void print(String text) {
        prints.add(text);
    }

    @Override
    public void printItem(Item item) {
        prints.add(item.toString());
    }

    @Override
    public String getString() {
        if (i < lines.size()) {
            return lines.get(i++);
        }
        return "";
    }

    @Override
    public Book newBook() {
        String title = getString();
        String author = getString();
        String description = getString();

        return new Book(author, title, description);
    }

    @Override
    public InternetContent newInternetContent() {
        String title = getString();
        String url = getString();
        String description = getString();

        return new InternetContent(title, url, description);
    }

    @Override
    public Podcast newPodcast() {
        String name = getString();
        String title = getString();
        String description = getString();

        return new Podcast(name, title, description);
    }


}