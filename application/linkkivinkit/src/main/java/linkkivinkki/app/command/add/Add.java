
package linkkivinkki.app.command.add;

import linkkivinkki.app.command.Command;
import linkkivinkki.app.command.CommandFactory;
import linkkivinkki.app.command.Invalid;
import linkkivinkki.io.Color;
import linkkivinkki.io.IO;

public class Add implements Command {
    private IO io;
    private CommandFactory commandFactory;

    public Add(IO io, CommandFactory commandFactory) {
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
                break;
            }

            io.printDivide();

            Command cmd = commandFactory.getCommand("add", input);
            success = cmd.execute();

            if (!cmd.getClass().equals(Invalid.class)) {
                break;
            }
        }

        return success;
    }

    private void printInstructions() {
        io.printDivide();
        io.print("What kind of item do you wish to add?");
        io.printCategories();
        io.print("Select an item type or type " + Color.cyanText("return") + " to return to the main menu.");
    }
}