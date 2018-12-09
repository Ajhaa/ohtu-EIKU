
package linkkivinkki.app.command;

import java.util.HashMap;

import linkkivinkki.app.command.add.Add;
import linkkivinkki.app.command.delete.Delete;
import linkkivinkki.app.command.register.Register;
import linkkivinkki.app.command.login.Login;
import linkkivinkki.app.command.mainview.MainView;
import linkkivinkki.app.command.view.View;
import linkkivinkki.dao.Dao;
import linkkivinkki.dao.UserDao;
import linkkivinkki.io.IO;

public class CommandFactory {
    private IO io;
    private Dao bookDao;
    private Dao icDao;
    private Dao podcastDao;
    private UserDao userDao;
    private HashMap<String, HashMap<String, Command>> commands;

    public CommandFactory(IO io, Dao bookDao, Dao icDao, Dao podcastDao, UserDao userDao) {
        this.io = io;
        this.bookDao = bookDao;
        this.icDao = icDao;
        this.podcastDao = podcastDao;
        this.userDao = userDao;

        setupCommands();
    }

    public Command getCommand(String state, String name) {
        Command command = commands.get(state).get(name);

        if (command == null) {
            return commands.get("misc").get("invalid");
        }

        return command;
    }

    private void setupCommands() {
        commands = new HashMap<>();

        commands.put("login", new HashMap<>());
        commands.get("login").put("r", new Register(io, userDao, this));
        commands.get("login").put("l", new Login(io, userDao, this));
        commands.get("login").put("mainView", new MainView(io, this));

        commands.put("main", new HashMap<>());
        commands.get("main").put("add", new Add(io, bookDao, icDao, podcastDao, this));
        commands.get("main").put("a", new Add(io, bookDao, icDao, podcastDao, this));
        commands.get("main").put("delete", new Delete(io, bookDao, icDao, podcastDao, this));
        commands.get("main").put("d", new Delete(io, bookDao, icDao, podcastDao, this));
        commands.get("main").put("view", new View(io, bookDao, icDao, podcastDao, this));
        commands.get("main").put("v", new View(io, bookDao, icDao, podcastDao, this));

        commands.put("misc", new HashMap<>());
        commands.get("misc").put("invalid", new Invalid(io));
    }
}