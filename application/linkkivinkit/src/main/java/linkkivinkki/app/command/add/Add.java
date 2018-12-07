
package linkkivinkki.app.command.add;

import linkkivinkki.app.command.Command;
import linkkivinkki.app.command.CommandFactory;
import linkkivinkki.dao.Dao;
import linkkivinkki.domain.Book;
import linkkivinkki.domain.InternetContent;
import linkkivinkki.domain.Podcast;
import linkkivinkki.io.Color;
import linkkivinkki.io.IO;

public class Add implements Command {
    private IO io;
    private Dao bookDao;
    private Dao icDao;
    private Dao podcastDao;
    private CommandFactory commandFactory;

    public Add(IO io, Dao bookDao, Dao icDao, Dao podcastDao, CommandFactory commandFactory) {
        this.io = io;
        this.bookDao = bookDao;
        this.icDao = icDao;
        this.podcastDao = podcastDao;
        this.commandFactory = commandFactory;
    }

    @Override
    public boolean execute() {
        boolean success = false;

        LOOP: while (true) {
            io.printDivide();
            io.print("What kind of item do you wish to add?");

            io.printCategories();

            io.print("Select an item type or type " + Color.cyanText("return") + " to return to the main menu.");
            String input = io.getString();

            switch (input) {
            case "return":
            case "r":
                break LOOP;
            case "book":
            case "b":
                io.printDivide();
                Book newBook = io.newBook();
                success = bookDao.add(newBook);
                break LOOP;
            case "internetcontent":
            case "i":
                io.printDivide();
                InternetContent newInternetContent = io.newInternetContent();
                success = icDao.add(newInternetContent);
                break LOOP;
            case "podcast":
            case "p":
                io.printDivide();
                Podcast newPodcast = io.newPodcast();
                success = podcastDao.add(newPodcast);
                break LOOP;
            default:
                io.print(Color.redText("Invalid command."));
                break;
            }
        }

        if (success) {
            io.print(Color.greenText("Item was added successfully"));
        } else {
            io.print(Color.redText("Adding item failed."));
        }

        return success;
    }

}