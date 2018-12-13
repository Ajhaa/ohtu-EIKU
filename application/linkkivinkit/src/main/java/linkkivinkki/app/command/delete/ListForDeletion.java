
package linkkivinkki.app.command.delete;

import java.util.List;

import linkkivinkki.app.App;
import linkkivinkki.app.command.Command;
import linkkivinkki.app.command.CommandFactory;
import linkkivinkki.domain.Item;
import linkkivinkki.io.IO;
import linkkivinkki.dao.ItemDao;

public class ListForDeletion implements Command {
    private IO io;
    private ItemDao dao;
    private CommandFactory commandFactory;
    private String type;

    public ListForDeletion(IO io, ItemDao dao, CommandFactory commandFactory, String type) {
        this.io = io;
        this.dao = dao;
        this.commandFactory = commandFactory;
        this.type = type;
    }

    @Override
    public boolean execute() {
        int userId = App.currentUser.getId();
        List<Item> items = dao.findAllByUserId(userId);

        for (Item item : items) {
            io.print("id: " + item.getId() + ", title: " + item.getTitle());
        }

        io.printDivide();

        Command cmd = commandFactory.getCommand("listfordeletion", type);
        return cmd.execute();
    }

}