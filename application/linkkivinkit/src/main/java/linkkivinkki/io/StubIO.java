package linkkivinkki.io;

import linkkivinkki.domain.Book;
import linkkivinkki.domain.InternetContent;
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
        return null;
    }

    @Override
    public Podcast newPodcast() {
        return null;
    }


}