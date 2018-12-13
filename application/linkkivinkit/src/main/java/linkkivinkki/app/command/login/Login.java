
package linkkivinkki.app.command.login;

import linkkivinkki.app.App;
import linkkivinkki.app.command.Command;
import linkkivinkki.app.command.CommandFactory;
import linkkivinkki.dao.UserDao;
import linkkivinkki.domain.User;
import linkkivinkki.io.Color;
import linkkivinkki.io.IO;

public class Login implements Command {
    private IO io;
    private UserDao userDao;
    private CommandFactory commandFactory;

    public Login(IO io, UserDao userDao, CommandFactory commandFactory) {
        this.io = io;
        this.userDao = userDao;
        this.commandFactory = commandFactory;
    }

    @Override
    public boolean execute() {
        while (true) {
            io.printDivide();
            io.print("Enter your username or " + Color.cyanText("r") + " to return");

            String input = io.getString();

            if (input.equals("r")) {
                return true;
            }

            User user = userDao.findOneByName(input);

            if (user == null) {
                io.print(Color.redText("Invalid username"));
            } else {
                io.print(Color.greenText("User " + user.getUsername() + " logged in"));
                App.currentUser = user;

                Command main = commandFactory.getCommand("login", "mainView");
                return main.execute();
            }
        }

    }
}