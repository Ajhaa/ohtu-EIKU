package linkkivinkki.app.command.view;

import java.util.HashMap;
import java.util.List;

import linkkivinkki.dao.ItemDao;
import linkkivinkki.domain.Item;
import linkkivinkki.io.IO;
import linkkivinkki.io.Color;
import linkkivinkki.app.command.Command;
import linkkivinkki.app.command.edit.Edit;

public class ViewOne {

    static boolean view(IO io, ItemDao dao, int id) {
        Item item = dao.findOne(id);

        /*switch (type.toLowerCase()) {
        case "book":
            item = (Item) bookDao.findOne(id);
            break;
        case "internetcontent":
            item = (Item) icDao.findOne(id);
            break;
        case "podcast":
            item = (Item) podcastDao.findOne(id);
            break;
        default:
            return false;
        } */



        if (item == null) {
            io.print(Color.redText("No item found with that id"));
            return false;
        }

        io.printDivide();
        io.printItem(item);

        while (true) {
            io.print("Commands: ");
            io.print(Color.cyanText("edit") + " - edit this item's information");
            io.print(Color.cyanText("return") + " - return to the previous list");
            io.print(Color.cyanText("main") + " - return to main menu");
            String input = io.getString();

            switch (input) {
            case "return":
            case "r":
                return true;
            case "main":
            case "m":
                return false;
            case "edit":
            case "e":
                new Edit(io, dao).edit(item);
                return false;
            default:
                io.print(Color.redText("Invalid command." + "\n"));
                break;
            }
        }
    }
}