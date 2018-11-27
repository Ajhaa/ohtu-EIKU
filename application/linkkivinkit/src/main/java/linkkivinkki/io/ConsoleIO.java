
package linkkivinkki.io;

import linkkivinkki.domain.Item;
import linkkivinkki.domain.Book;
import linkkivinkki.domain.InternetContent;
import linkkivinkki.domain.Podcast;
import java.util.Scanner;
import java.util.List;

public class ConsoleIO implements IO {
    private Scanner scanner;

    public ConsoleIO() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void print(String text) {
        System.out.println(text);
    }

    @Override
    public void printItem(Item item) {
        System.out.println(item);
        System.out.println("Description: " + item.getDescription());

        //(waiting for tag implementation)
        //List<String> tags = item.getTags();
        //System.out.print("Tags: ");
        //System.out.println(tags.toString().substring(1, tags.toString().length() - 1));

        if (item.isRead()) {
            System.out.println("Read on: " + item.getReadDate());
        } else {
            System.out.println("Not yet read.");
        }

        System.out.println("");
    }

    @Override
    public String getString() {
        return scanner.nextLine();
    }

    @Override
    public Book newBook() {
        System.out.println("Insert book title: ");
        String title = scanner.nextLine();
        System.out.println("Insert book author: ");
        String author = scanner.nextLine();
        System.out.println("Insert a description (or leave blank): ");
        String description = scanner.nextLine();

        return new Book(author, title, description);
    }

    @Override
    public InternetContent newInternetContent() {
        System.out.println("Insert content title: ");
        String title = scanner.nextLine();
        System.out.println("Insert content url: ");
        String url = scanner.nextLine();
        System.out.println("Insert a description (or leave blank): ");
        String description = scanner.nextLine();

        return new InternetContent(title, url, description);
    }

    @Override
    public Podcast newPodcast() {
        System.out.println("Insert podcast name: ");
        String name = scanner.nextLine();
        System.out.println("Insert podcast title: ");
        String title = scanner.nextLine();
        System.out.println("Insert a description (or leave blank): ");
        String description = scanner.nextLine();

        Podcast p = new Podcast(name, title, description);

        return p;
    }
}
