
package linkkivinkki.app.command.view;

import linkkivinkki.app.command.Command;
import linkkivinkki.app.command.CommandFactory;

import linkkivinkki.io.Color;
import linkkivinkki.io.IO;
import linkkivinkki.app.command.Invalid;

public class View implements Command {
    private IO io;
    private CommandFactory commandFactory;

    public View(IO io, CommandFactory commandFactory) {
        this.io = io;
        this.commandFactory = commandFactory;
    }

    @Override
    public boolean execute() {
        boolean success = false;

        while (true) {
            io.printDivide();
            io.print("What kind of items do you wish to view?");

            io.print(Color.cyanText("all"));
            io.printCategories();

            io.print("Select a listing type or type " + Color.cyanText("return") + " to return to the main menu.");
            String input = io.getString().toLowerCase();

            if (input.equals("r") || input.equals("return")) {
                return false;
            }

            Command cmd = commandFactory.getCommand("view", input);
            success = cmd.execute();

            if (!cmd.getClass().equals(Invalid.class)) {
                return success;
            }
        }
    }
}