
package linkkivinkki.io;

import linkkivinkki.domain.Item;
import linkkivinkki.domain.Book;
import linkkivinkki.domain.InternetContent;
import linkkivinkki.domain.Podcast;

public interface IO {
    void print(String text);
    void printItem(Item item);
    String getString();
    Book newBook();
    InternetContent newInternetContent();
    Podcast newPodcast();
}
