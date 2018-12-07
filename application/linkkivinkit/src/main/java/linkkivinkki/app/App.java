
package linkkivinkki.app;

import linkkivinkki.app.command.Command;
import linkkivinkki.app.command.CommandFactory;
import linkkivinkki.dao.Dao;
import linkkivinkki.io.IO;
import linkkivinkki.io.Color;

public class App {

    private IO io;

    private CommandFactory commandFactory;
    private String state;

    public App(IO io, Dao bookDao, Dao icDao, Dao podcastDao) {
        this.io = io;
        this.commandFactory = new CommandFactory(io, bookDao, icDao, podcastDao);
    }

    public void start() {
        io.print("App started.");

        LOOP: while (true) {
            state = "main";

            io.printDivide();
            io.print("MAIN MENU" + "\n");
            io.print("Commands:");
            io.print(Color.cyanText("view") + " - view existing items");
            io.print(Color.cyanText("add") + " - add a new memo item");
            io.print(Color.cyanText("delete") + " - view and delete items");
            io.print(Color.cyanText("quit") + " - exit the app.");
            String input = io.getString();

            if (input.equals("quit") || input.equals("q")) {
                break LOOP;
            }

            Command cmd = commandFactory.getCommand(state, input);
            cmd.execute();
        }

        io.print("\n" + "Shutting down.");
    }

}
