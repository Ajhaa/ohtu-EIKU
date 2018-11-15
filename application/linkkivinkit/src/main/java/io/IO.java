
package io;

import domain.Book;
import domain.InternetContent;
import domain.Podcast;

public interface IO {
    void print(String text);
    Book newBook();
    InternetContent newInternetContent();
    Podcast newPodcast();
}
