package linkkivinkki.app.command.edit;

import linkkivinkki.dao.ItemDao;
import linkkivinkki.domain.Book;
import linkkivinkki.domain.InternetContent;
import linkkivinkki.domain.Item;
import linkkivinkki.domain.Podcast;
import linkkivinkki.io.Color;
import linkkivinkki.io.IO;

public class Edit {

    private IO io;
    private ItemDao dao;

    public Edit(IO io, ItemDao dao) {
        this.io = io;
        this.dao = dao;
    }

    public boolean edit(Item item) {
        io.printDivide();
        io.print("Insert the new information you want for this item. Leave fields blank if you do not wish to change them. \n");

        String name = "";
        String title = "";
        String url = "";
        String author = "";
        String description = "";

        if (item.getClass().equals(Podcast.class)) {
            io.print("Insert podcast name: ");
            name = io.getString();
        }

        io.print("Insert content title: ");
        title = io.getString();

        if (item.getClass().equals(Book.class)) {
            io.print("Insert book author: ");
            author = io.getString();
        } else if (item.getClass().equals(InternetContent.class)) {
            io.print("Insert content url: ");
            url = io.getString();
        }

        io.print("Insert a description: ");
        description = io.getString();

        return editAndUpdate(item, name, title, url, author, description);
    }

    private boolean editAndUpdate(Item item, String name, String title, String url, String author, String description) {
        if (title.length() > 0) {
            item.setTitle(title);
        }

        if (description.length() > 0) {
            item.setDescription(description);
        }

        boolean success = false;

        if (item.getClass().equals(Book.class)) {
            Book book = (Book) item;

            if (author.length() > 0) {
                book.setAuthor(author);
            }

            success = dao.update(book);
        } else if (item.getClass().equals(InternetContent.class)) {
            InternetContent ic = (InternetContent) item;

            if (url.length() > 0) {
                ic.setUrl(url);
            }

            success = dao.update(ic);
        } else if (item.getClass().equals(Podcast.class)) {
            Podcast podcast = (Podcast) item;

            if (name.length() > 0) {
                podcast.setName(name);
            }

            success = dao.update(podcast);
        }

        if (success) {
            io.print(Color.greenText("Item was updated successfully"));
        } else {
            io.print(Color.redText("Updating item failed."));
        }

        return success;
    }
}