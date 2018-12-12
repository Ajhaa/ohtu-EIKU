
package linkkivinkki.app.command.delete;

import linkkivinkki.app.command.Command;
import linkkivinkki.app.command.CommandFactory;
import linkkivinkki.app.command.Invalid;
import linkkivinkki.io.Color;
import linkkivinkki.io.IO;

public class Delete implements Command {
    private IO io;
    private CommandFactory commandFactory;

    public Delete(IO io, CommandFactory commandFactory) {
        this.io = io;
        this.commandFactory = commandFactory;
    }

    @Override
    public boolean execute() {
        boolean success = false;

        while (true) {
            printInstructions();
            String input = io.getString().toLowerCase();

            if (input.equals("return") || input.equals("r")) {
                return false;
            }

            io.printDivide();

            Command cmd = commandFactory.getCommand("delete", input);
            success = cmd.execute();

            if (!cmd.getClass().equals(Invalid.class)) {
                return success;
            }
        }
    }

    private void printInstructions() {
        io.printDivide();
        io.print("What kind of item do you wish to delete?");
        io.printCategories();
        io.print("Select an item type or type " + Color.cyanText("return") + " to return to the main menu.");
    }
}