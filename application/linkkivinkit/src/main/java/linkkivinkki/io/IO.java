
package linkkivinkki.io;

import linkkivinkki.domain.Item;
import linkkivinkki.domain.Book;
import linkkivinkki.domain.InternetContent;
import linkkivinkki.domain.Podcast;

public interface IO {
    void print(String text);
    void printItem(Item item);
    void printColor(String text, Color color);
    String getString();
    Book newBook();
    InternetContent newInternetContent();
    Podcast newPodcast();
    void printCategories();
    void printDivide();
}
