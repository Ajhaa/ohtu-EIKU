
package linkkivinkki.app.command.delete;

import linkkivinkki.app.App;
import linkkivinkki.app.command.Command;
import linkkivinkki.io.Color;
import linkkivinkki.io.IO;
import linkkivinkki.dao.ItemDao;

public class DeleteItem implements Command {
    private IO io;
    private ItemDao dao;

    public DeleteItem(IO io, ItemDao dao) {
        this.io = io;
        this.dao = dao;
    }

    @Override
    public boolean execute() {
        boolean success = false;

        while (true) {
            io.print("\n" + "Enter the ID of the item you wish to delete or type " + Color.cyanText("return")
                    + " to return to the main menu.");
            String input = io.getString();

            if (input.equals("return") || input.equals("r")) {
                return false;
            } else {
                try {
                    int id = Integer.parseInt(input);
                    success = dao.delete(id, App.currentUser.getId());
                    break;
                } catch (Exception e) {
                    io.print(Color.redText("Invalid command."));
                }
            }
        }

        if (success) {
            io.print(Color.greenText("Item was deleted successfully"));
        } else {
            io.print(Color.redText("Deleting item failed."));
        }

        return success;
    }

}