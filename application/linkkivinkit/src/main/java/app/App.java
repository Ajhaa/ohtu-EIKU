package app;

import dao.Dao;
import domain.Book;
import domain.InternetContent;
import domain.Podcast;
import io.IO;

public class App {

    private IO io;
    private Dao bookDao;
    private Dao icDao;

    public App(IO io, Dao bookDao, Dao icDao) {
        this.io = io;
        this.bookDao = bookDao;
        this.icDao = icDao;
    }

    public void start() {
        io.print("App started.");

        LOOP:
        while (true) {
            io.print("");
            io.print("Type 'view' to view existing memo items or type 'add' to add a new memo item.");
            io.print("Type 'quit' to exit the app.");
            String input = io.getString();

            switch (input) {
                case "quit":
                case "q":
                    break LOOP;
                case "view":
                case "v":
                    this.view();
                    break;
                case "add":
                case "a":
                    this.add();
                    break;
                default:
                    break;
            }
        }

        io.print("\n" + "Shutting down.");
    }

    public boolean view() {
        LOOP:
        while (true) {
            io.print("What kind of items do you wish to view?");
            io.print("Available listings: books, internetcontent, podcasts");
            io.print("Select a listing type or type 'return' to return to the main menu.");
            String input = io.getString();

            switch (input) {
                case "return":
                case "r":
                    break LOOP;
                case "books":
                case "book":
                case "b":
                    bookDao.findAll().forEach((book) -> {
                        io.print(book.toString());
                    });
                    return true;
                case "internetcontent":
                case "i":
                    icDao.findAll().forEach((ic) -> {
                        io.print(ic.toString());
                    });
                    return true;
                case "podcasts":
                case "podcast":
                case "p":
                    Podcast newPodcast = io.newPodcast();
                    return true;
                default:
                    break;
            }
        }

        return false;
    }

    public boolean add() {
        LOOP:
        while (true) {
            io.print("What kind of item do you wish to add?");
            io.print("Available types: book, internetcontent, podcast");
            io.print("Select an item type or type 'return' to return to the main menu.");
            String input = io.getString();

            switch (input) {
                case "return":
                case "r":
                    break LOOP;
                case "book":
                case "b":
                    Book newBook = io.newBook();
                    return bookDao.add(newBook);
                case "internetcontent":
                case "i":
                    InternetContent newInternetContent = io.newInternetContent();
                    return icDao.add(newInternetContent);
                case "podcast":
                case "p":
                    Podcast newPodcast = io.newPodcast();
                    io.print("not supported");
                    break;
                default:
                    break;
            }
        }

        return false;
    }
}
