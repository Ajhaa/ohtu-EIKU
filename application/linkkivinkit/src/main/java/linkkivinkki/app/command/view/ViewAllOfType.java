package linkkivinkki.app.command.view;

import java.util.ArrayList;
import java.util.List;

import linkkivinkki.app.App;
import linkkivinkki.app.command.CommandFactory;
import linkkivinkki.dao.ItemDao;
import linkkivinkki.domain.Item;
import linkkivinkki.io.IO;
import linkkivinkki.io.Color;
import linkkivinkki.app.command.Command;

public class ViewAllOfType implements Command {
    IO io;
    ItemDao dao;
    CommandFactory commandFactory;

    public ViewAllOfType(IO io, ItemDao dao, CommandFactory commandFactory) {
        this.io = io;
        this.dao = dao;
        this.commandFactory = commandFactory;
    }

    @Override
    public boolean execute() {
        int userId = App.currentUser.getId();

        List<Item> items = dao.findAllByUserId(userId);

        io.printDivide();

        while (true) {
            int serial = 1;

            for (Item item : items) {
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

                    Item itemToView = items.get(index);
                    
                    String type = itemToView.getClass().getSimpleName();

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