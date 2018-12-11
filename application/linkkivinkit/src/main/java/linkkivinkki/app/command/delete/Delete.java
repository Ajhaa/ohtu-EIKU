
package linkkivinkki.app.command.delete;

import java.util.List;

import linkkivinkki.app.App;
import linkkivinkki.app.command.Command;
import linkkivinkki.app.command.CommandFactory;
import linkkivinkki.domain.Item;
import linkkivinkki.io.Color;
import linkkivinkki.io.IO;
import linkkivinkki.dao.ItemDao;

public class Delete implements Command {
    private IO io;
    private ItemDao bookDao;
    private ItemDao icDao;
    private ItemDao podcastDao;
    private CommandFactory commandFactory;

    public Delete(IO io, ItemDao bookDao, ItemDao icDao, ItemDao podcastDao, CommandFactory commandFactory) {
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
            io.print("What kind of item do you wish to delete?");
            io.printCategories();
            io.print("Select an item type or type " + Color.cyanText("return") + " to return to the main menu.");
            String input = io.getString();

            switch (input) {
            case "return":
            case "r":
                break LOOP;
            case "book":
            case "b":
                success = listForDeletion("book");
                break LOOP;
            case "internetcontent":
            case "i":
                success = listForDeletion("internetContent");
                break LOOP;
            case "podcast":
            case "p":
                success = listForDeletion("podcast");
                break LOOP;
            default:
                io.print(Color.redText("Invalid command."));
                break;
            }
        }

        if (success) {
            io.print(Color.greenText("Item was deleted successfully"));
        } else {
            io.print(Color.redText("Deleting item failed."));
        }

        return success;
    }

    private boolean listForDeletion(String type) {
        int userId = App.currentUser.getId();

        List<Item> items;

        switch (type) {
        case "book":
            items = bookDao.findAllByUserId(userId);
            break;
        case "internetContent":
            items = icDao.findAllByUserId(userId);
            break;
        case "podcast":
            items = podcastDao.findAllByUserId(userId);
            break;
        default:
            return false;
        }

        for (Item item : items) {
            io.print("id: " + item.getId() + ", title: " + item.getTitle());
        }

        return deleteItem(type);
    }

    private boolean deleteItem(String type) {
        while (true) {
            io.print("\n" + "Enter the ID of the item you wish to delete or type " + Color.cyanText("return")
                    + " to return to the main menu.");
            String input = io.getString();

            if (input.equals("return") || input.equals("r")) {
                return true;
            } else {
                try {
                    int id = Integer.parseInt(input);

                    if (type.equals("book")) {
                        return bookDao.delete(id);
                    } else if (type.equals("internetContent")) {
                        return icDao.delete(id);
                    } else {
                        return podcastDao.delete(id);
                    }
                } catch (Exception e) {
                    io.print(Color.redText("Invalid command."));
                }
            }
        }
    }

}