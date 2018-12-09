
package linkkivinkki.app;

import linkkivinkki.domain.User;
import linkkivinkki.app.command.Command;
import linkkivinkki.app.command.CommandFactory;
import linkkivinkki.dao.Dao;
import linkkivinkki.dao.UserDao;
import linkkivinkki.io.IO;
import linkkivinkki.io.Color;

public class App {

    private IO io;

    private CommandFactory commandFactory;
    private String state;

    public static User currentUser;

    public App(IO io, Dao bookDao, Dao icDao, Dao podcastDao, UserDao userDao) {
        this.io = io;
        this.commandFactory = new CommandFactory(io, bookDao, icDao, podcastDao, userDao);
    }

    public void start() {
        io.print("App started.");

        LOGIN: while (true) {
            state = "login";

            io.printDivide();
            io.print(Color.cyanText("(L)og in") + " or " + Color.cyanText("(R)egister") + " a new account");
            io.print("Enter " + Color.cyanText("quit") + " to quit program");

            String input = io.getString();

            Command cmd = commandFactory.getCommand(state, input);

            if (input.equals("quit") || input.equals("q")) {
                break LOGIN;
            }

            cmd.execute();
        }


        io.print("\n" + "Shutting down.");
    }

}
