package linkkivinkki.app;

import java.util.List;

import linkkivinkki.dao.Dao;
import linkkivinkki.domain.Item;
import linkkivinkki.domain.Book;
import linkkivinkki.domain.InternetContent;
import linkkivinkki.domain.Podcast;
import linkkivinkki.io.IO;
import linkkivinkki.io.Color;

public class App {

    private IO io;
    private Dao bookDao;
    private Dao icDao;
    private Dao podcastDao;

    public App(IO io, Dao bookDao, Dao icDao, Dao podcastDao) {
        this.io = io;
        this.bookDao = bookDao;
        this.icDao = icDao;
        this.podcastDao = podcastDao;
    }

    public void start() {
        io.print("App started.");

        LOOP:
        while (true) {
            io.print("");
            io.print("Commands:");
            io.print(Color.cyanText("view") + " - view existing items");
            io.print(Color.cyanText("add") + " - add a new memo item");
            io.print(Color.cyanText("delete") + " - view and delete items");
            io.print(Color.cyanText("quit") + " - exit the app.");
            String input = io.getString();

            switch (input) {
                case "quit":
                case "q":
                    break LOOP;
                case "view":
                case "v":
                    this.view();
                    break;
                case "add":
                case "a":
                    this.add();
                    break;
                case "delete":
                case "d":
                    this.delete();
                    break;
                default:
                    io.print(Color.redText("Invalid command."));
                    break;
            }
        }

        io.print("\n" + "Shutting down.");
    }

    public boolean view() {
        LOOP:
        while (true) {
            io.print("\n");
            io.print("What kind of items do you wish to view?");

            printCategories();

            io.print("Select a listing type or type " + Color.cyanText("return") + " to return to the main menu.");
            String input = io.getString();

            if ("return".startsWith(input)) {
                break LOOP;
            } else if ("books".startsWith(input)) {
                return viewItems("book");
            } else if ("internetcontent".startsWith(input)) {
                return viewItems("internetContent");
            } else if ("podcasts".startsWith(input)) {
                return viewItems("podcast");
            } else {
                break LOOP;
            }
        }

        return false;
    }

    public boolean viewItems(String type) {
        List<Item> items;

        switch (type) {
            case "book":
                items = bookDao.findAll();
                break;
            case "internetContent":
                items = icDao.findAll();
                break;
            case "podcast":
                items = podcastDao.findAll();
                break;
            default:
                return false;
        }

        while (true) {
            for (Item item : items) {
                io.print(item.toString());
            }

            io.print("\n" + "Enter an item ID to view more information about the specified item or type " + Color.cyanText("return") + " to return to the main menu.");
            String input = io.getString();
            if (input.equals("return") || input.equals("r") || input.length() == 0) {
                return true;
            } else {
                try {
                    int id = Integer.parseInt(input);

                    boolean keepGoing = viewOne(type, id);
                    if (!keepGoing) {
                        return true;
                    }
                } catch (Exception e) {
                    io.print(Color.redText("Invalid command." + "\n"));
                }
            }
        }
    }

    public boolean viewOne(String type, int id) {
        Item item;

        switch (type) {
            case "book":
                item = (Item) bookDao.findOne(id);
                break;
            case "internetContent":
                item = (Item) icDao.findOne(id);
                break;
            case "podcast":
                item = (Item) podcastDao.findOne(id);
                break;
            default:
                return false;
        }

        if (item == null) {
            io.print(Color.redText("No item found with that id"));
            return false;
        }

        io.printItem(item);

        while (true) {
            io.print("Commands: ");
            io.print(Color.cyanText("edit") + " - edit this item's information");
            io.print(Color.cyanText("return") + " - return to the previous list");
            io.print(Color.cyanText("main") + " - return to main menu");
            String input = io.getString();

            switch (input) {
                case "return":
                case "r":
                    return true;
                case "main":
                case "m":
                    return false;
                case "edit":
                case "e":
                    edit(item);
                    return false;
                default:
                    io.print(Color.redText("Invalid command." + "\n"));
                    break;
            }
        }
    }

    public boolean edit(Item item) {
        io.print("Insert the new information you want for this item. Leave fields blank if you do not wish to change them." + "\n");

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

    public boolean editAndUpdate(Item item, String name, String title, String url, String author, String description) {
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

            success = bookDao.update(book);
        } else if (item.getClass().equals(InternetContent.class)) {
            InternetContent ic = (InternetContent) item;

            if (url.length() > 0) {
                ic.setUrl(url);
            }

            success = icDao.update(ic);
        } else if (item.getClass().equals(Podcast.class)) {
            Podcast podcast = (Podcast) item;

            if (name.length() > 0) {
                podcast.setName(name);
            }

            success = podcastDao.update(podcast);
        }

        if (success) {
            io.print(Color.greenText("Item was updated successfully"));
        } else {
            io.print(Color.redText("Updating item failed."));
        }

        return success;
    }

    public boolean add() {
        LOOP:
        while (true) {
            printEmpty();
            io.print("What kind of item do you wish to add?");

            printCategories();

            io.print("Select an item type or type " + Color.cyanText("return") + " to return to the main menu.");
            String input = io.getString();

            switch (input) {
                case "return":
                case "r":
                    break LOOP;
                case "book":
                case "b":
                    Book newBook = io.newBook();
                    return bookDao.add(newBook);
                case "internetcontent":
                case "i":
                    InternetContent newInternetContent = io.newInternetContent();
                    return icDao.add(newInternetContent);
                case "podcast":
                case "p":
                    Podcast newPodcast = io.newPodcast();
                    return podcastDao.add(newPodcast);
                default:
                    io.print(Color.redText("Invalid command."));
                    break;
            }
        }

        return false;
    }

    public boolean delete() {
        LOOP:
        while (true) {
            printEmpty();
            io.print("What kind of item do you wish to delete?");
            printCategories();
            io.print("Select an item type or type " + Color.cyanText("return") + " to return to the main menu.");
            String input = io.getString();

            switch (input) {
                case "return":
                case "r":
                    break LOOP;
                case "book":
                case "b":
                    return listForDeletion("book");
                case "internetcontent":
                case "i":
                    return listForDeletion("internetContent");
                case "podcast":
                case "p":
                    return listForDeletion("podcast");
                default:
                    io.print(Color.redText("Invalid command."));
                    break;
            }
        }

        return false;
    }

    public boolean listForDeletion(String type) {
        List<Item> items;

        switch (type) {
            case "book":
                items = bookDao.findAll();
                break;
            case "internetContent":
                items = icDao.findAll();
                break;
            case "podcast":
                items = podcastDao.findAll();
                break;
            default:
                return false;
        }

        for (Item item : items) {
            io.print("id: " + item.getId() + ", title: " + item.getTitle());
        }

        return deleteItem(type);
    }

    public boolean deleteItem(String type) {
        while (true) {
            io.print("\n" + "Enter the ID of the item you wish to delete or type " + Color.cyanText("return") + " to return to the main menu.");
            String input = io.getString();

            if (input.equals("return") || input.equals("r")) {
                return false;
            } else {
                try {
                    int id = Integer.parseInt(input);

                    if (type.equals("book")) {
                        return bookDao.delete(id);
                    } else if (type.equals("internetContent")) {
                        return icDao.delete(id);
                    } else {
                        return podcastDao.delete(id);
                    }
                } catch (Exception e) {
                    io.print(Color.redText("Invalid command."));
                }
            }
        }
    }

    private void printCategories() {
        io.print(Color.cyanText("books"));
        io.print(Color.cyanText("internetcontent"));
        io.print(Color.cyanText("podcasts"));
        io.print("");
    }

    private void printEmpty() {
        io.print("\n\n");
    }
}
