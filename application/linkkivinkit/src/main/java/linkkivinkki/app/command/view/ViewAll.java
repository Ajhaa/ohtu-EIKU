package linkkivinkki.app.command.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import linkkivinkki.app.App;
import linkkivinkki.app.command.CommandFactory;
import linkkivinkki.dao.ItemDao;
import linkkivinkki.domain.Item;
import linkkivinkki.domain.ItemDateComparator;
import linkkivinkki.domain.ItemTitleComparator;
import linkkivinkki.io.IO;
import linkkivinkki.io.Color;
import linkkivinkki.app.command.Command;

public class ViewAll implements Command {
    IO io;
    ItemDao bookDao;
    ItemDao icDao;
    ItemDao podcastDao;
    CommandFactory commandFactory;

    public ViewAll(IO io, ItemDao bookDao, ItemDao icDao, ItemDao podcastDao, CommandFactory commandFactory) {
        this.io = io;
        this.bookDao = bookDao;
        this.icDao = icDao;
        this.podcastDao = podcastDao;
        this.commandFactory = commandFactory;
    }

    @Override
    public boolean execute() {
        int userId = App.currentUser.getId();

        ArrayList<Item> allItems = new ArrayList<>();
        allItems.addAll(bookDao.findAllByUserId(userId));
        allItems.addAll(icDao.findAllByUserId(userId));
        allItems.addAll(podcastDao.findAllByUserId(userId));

        io.printDivide();
        io.print("What do you want to order the items by?");
        io.print(Color.cyanText("alphabetical") + " - sorted by item title");
        io.print(Color.cyanText("date") + " - sorted by date created");
        io.print(Color.cyanText("random") + " - in random order");
        String order = io.getString();


        if (order.equals("alphabetical") || order.equals("a")) {
            Collections.sort(allItems, new ItemTitleComparator());
        } else if (order.equals("date") || order.equals("d")) {
            Collections.sort(allItems, new ItemDateComparator());
        } else if (order.equals("random") || order.equals("r")) {
            Collections.shuffle(allItems);
        } else {
            return false;
        }

        io.printDivide();

        while (true) {
            int serial = 1;

            for (Item item : allItems) {
                io.print(serial + ". " + item.info());
                serial++;
            }

            io.print("\n" + "Enter an item ID to view more information about the specified item or type "
                    + Color.cyanText("return") + " to return to the main menu.");

            String input = io.getString();
            if (input.equals("return") || input.equals("r") || input.length() == 0) {
                return true;
            } else {
                try {
                    int id = Integer.parseInt(input);
                    int index = id - 1;

                    Item itemToView = allItems.get(index);
                    
                    String type = itemToView.getClass().getSimpleName();

                    ItemDao dao;

                    switch (type) {
                        case "Book":
                            dao = bookDao;
                            break;
                        case "InternetContent":
                            dao = icDao;
                            break;
                        case "Podcast":
                            dao = podcastDao;
                            break;
                        default:
                            return false;            
                    }

                    boolean keepGoing = ViewOne.view(io, dao, itemToView.getId());

                    if (!keepGoing) {
                        return true;
                    }
                } catch (Exception e) {
                    io.print(Color.redText("Invalid command."));
                }
            }
        }
    }

}