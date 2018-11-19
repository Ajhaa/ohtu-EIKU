
package linkkivinkki.io;

import linkkivinkki.domain.Book;
import linkkivinkki.domain.InternetContent;
import linkkivinkki.domain.Podcast;

public interface IO {
    void print(String text);
    String getString();
    Book newBook();
    InternetContent newInternetContent();
    Podcast newPodcast();
}
