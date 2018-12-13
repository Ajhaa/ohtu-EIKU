
package linkkivinkki.app.command.mainview;

import linkkivinkki.app.App;
import linkkivinkki.app.command.Command;
import linkkivinkki.app.command.CommandFactory;
import linkkivinkki.io.Color;
import linkkivinkki.io.IO;

public class MainView implements Command {
    private IO io;
    private CommandFactory commandFactory;

    public MainView(IO io, CommandFactory commandFactory) {
        this.io = io;
        this.commandFactory = commandFactory;
    }

    @Override
    public boolean execute() {
        while (true) {
            String state = "main";

            io.printDivide();
            io.print("MAIN MENU" + "\n");
            io.print("Commands:");
            io.print(Color.cyanText("view") + " - view existing items");
            io.print(Color.cyanText("add") + " - add a new memo item");
            io.print(Color.cyanText("delete") + " - view and delete items");
            io.print(Color.cyanText("logout") + " - log out");
            String input = io.getString();

            if (input.equals("logout") || input.equals("l")) {
                App.currentUser = null;
                return true;
            }

            Command cmd = commandFactory.getCommand(state, input);
            cmd.execute();
        }

    }
}