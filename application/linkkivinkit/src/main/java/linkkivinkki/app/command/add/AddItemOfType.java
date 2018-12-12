
package linkkivinkki.app.command.add;

import linkkivinkki.app.App;
import linkkivinkki.app.command.Command;
import linkkivinkki.domain.Item;
import linkkivinkki.io.IO;
import linkkivinkki.dao.ItemDao;

public class AddItemOfType implements Command {
    private IO io;
    private ItemDao dao;
    private String type;

    public AddItemOfType(IO io, ItemDao dao, String type) {
        this.io = io;
        this.dao = dao;
        this.type = type;
    }

    @Override
    public boolean execute() {
        int userId = App.currentUser.getId();
        Item newItem;

        if (type.equals("book")) {
            newItem = io.newBook();
        } else if (type.equals("internetcontent")) {
            newItem = io.newInternetContent();
        } else {
            newItem = io.newPodcast();
        }

        newItem.setUserId(userId);
        return dao.add(newItem);
    }

}