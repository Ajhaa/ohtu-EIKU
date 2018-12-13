
package linkkivinkki.app.command.register;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import linkkivinkki.app.command.Command;
import linkkivinkki.app.command.CommandFactory;
import linkkivinkki.dao.UserDao;
import linkkivinkki.domain.Book;
import linkkivinkki.domain.InternetContent;
import linkkivinkki.domain.Item;
import linkkivinkki.domain.ItemDateComparator;
import linkkivinkki.domain.ItemTitleComparator;
import linkkivinkki.domain.Podcast;
import linkkivinkki.domain.User;
import linkkivinkki.io.Color;
import linkkivinkki.io.IO;
import linkkivinkki.dao.ItemDao;

public class Register implements Command {
    private IO io;
    private UserDao userDao;
    private CommandFactory commandFactory;

    public Register(IO io, UserDao userDao, CommandFactory commandFactory) {
        this.io = io;
        this.userDao = userDao;
        this.commandFactory = commandFactory;
    }

    @Override
    public boolean execute() {
        while (true) {
            io.printDivide();
            io.print("Registering a new user");
            io.print("Choose a username or enter " + Color.cyanText("r") + " to return");

            String input = io.getString();

            if (input.equals("r")) {
                return true;
            }

            User newUser = new User(input);

            if (userDao.add(newUser)) {
                io.print(Color.greenText("User " + input + " successfully created"));
                return true;
            } else {
                io.print(Color.redText("Username already taken"));
            }
        }

    }
}