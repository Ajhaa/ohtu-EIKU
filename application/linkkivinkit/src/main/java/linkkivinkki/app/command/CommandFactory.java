
package linkkivinkki.app.command;

import java.util.HashMap;

import linkkivinkki.app.command.add.Add;
import linkkivinkki.app.command.add.AddItemOfType;
import linkkivinkki.app.command.delete.Delete;
import linkkivinkki.app.command.delete.DeleteItem;
import linkkivinkki.app.command.delete.ListForDeletion;
import linkkivinkki.app.command.register.Register;
import linkkivinkki.app.command.login.Login;
import linkkivinkki.app.command.mainview.MainView;
import linkkivinkki.app.command.view.View;
import linkkivinkki.app.command.view.ViewAll;
import linkkivinkki.app.command.view.ViewAllOfType;

import linkkivinkki.dao.UserDao;
import linkkivinkki.io.IO;
import linkkivinkki.dao.BookDao;
import linkkivinkki.dao.ItemDao;

public class CommandFactory {
    private IO io;
    private ItemDao bookDao;
    private ItemDao icDao;
    private ItemDao podcastDao;
    private UserDao userDao;
    private HashMap<String, HashMap<String, Command>> commands;

    public CommandFactory(IO io, ItemDao bookDao, ItemDao icDao, ItemDao podcastDao, UserDao userDao) {
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

        setupLoginCommands();
        setupMainCommands();
        setupAddCommands();
        setupDeleteCommands();
        setupViewCommands();

        commands.put("misc", new HashMap<>());
        commands.get("misc").put("invalid", new Invalid(io));
    }

    private void setupLoginCommands() {
        commands.put("login", new HashMap<>());
        commands.get("login").put("r", new Register(io, userDao, this));
        commands.get("login").put("l", new Login(io, userDao, this));
        commands.get("login").put("mainView", new MainView(io, this));
    }

    private void setupMainCommands() {
        commands.put("main", new HashMap<>());
        commands.get("main").put("add", new Add(io, this));
        commands.get("main").put("a", new Add(io, this));
        commands.get("main").put("delete", new Delete(io, this));
        commands.get("main").put("d", new Delete(io, this));
        commands.get("main").put("view", new View(io, this));
        commands.get("main").put("v", new View(io, this));
    }

    private void setupViewCommands() {
        commands.put("view", new HashMap<>());
        commands.get("view").put("book", new ViewAllOfType(io, bookDao, this));
        commands.get("view").put("b", new ViewAllOfType(io, bookDao, this));
        commands.get("view").put("internetcontent", new ViewAllOfType(io, icDao, this));
        commands.get("view").put("i", new ViewAllOfType(io, icDao, this));
        commands.get("view").put("podcast", new ViewAllOfType(io, podcastDao, this));
        commands.get("view").put("p", new ViewAllOfType(io, podcastDao, this));
        commands.get("view").put("all", new ViewAll(io, bookDao, icDao, podcastDao, this));
        commands.get("view").put("a", new ViewAll(io, bookDao, icDao, podcastDao, this));
        
        
    }

    private void setupAddCommands() {
        commands.put("add", new HashMap<>());
        commands.get("add").put("book", new AddItemOfType(io, bookDao, "book"));
        commands.get("add").put("b", new AddItemOfType(io, bookDao, "book"));
        commands.get("add").put("internetcontent", new AddItemOfType(io, icDao, "internetcontent"));
        commands.get("add").put("i", new AddItemOfType(io, icDao, "internetcontent"));
        commands.get("add").put("podcast", new AddItemOfType(io, podcastDao, "podcast"));
        commands.get("add").put("p", new AddItemOfType(io, podcastDao, "podcast"));
    }

    private void setupDeleteCommands() {
        commands.put("delete", new HashMap<>());
        commands.get("delete").put("book", new ListForDeletion(io, bookDao, this, "book"));
        commands.get("delete").put("b", new ListForDeletion(io, bookDao, this, "book"));
        commands.get("delete").put("internetcontent", new ListForDeletion(io, icDao, this, "internetcontent"));
        commands.get("delete").put("i", new ListForDeletion(io, icDao, this, "internetcontent"));
        commands.get("delete").put("podcast", new ListForDeletion(io, podcastDao, this, "podcast"));
        commands.get("delete").put("p", new ListForDeletion(io, podcastDao, this, "podcast"));

        commands.put("listfordeletion", new HashMap<>());
        commands.get("listfordeletion").put("book", new DeleteItem(io, bookDao));
        commands.get("listfordeletion").put("internetcontent", new DeleteItem(io, icDao));
        commands.get("listfordeletion").put("podcast", new DeleteItem(io, podcastDao));
    }
}