package linkkivinkki.app;

import java.util.List;
import linkkivinkki.dao.Dao;
import linkkivinkki.domain.Item;
import linkkivinkki.domain.Book;
import linkkivinkki.domain.InternetContent;
import linkkivinkki.domain.Podcast;
import linkkivinkki.io.IO;

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
            io.print("Type 'view' to view existing memo items, 'add' to add a new memo item or 'delete' to view and delete items.");
            io.print("Type 'quit' to exit the app.");
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
                    break LOOP;
            }
        }

        io.print("\n" + "Shutting down.");
    }

    public boolean view() {
        LOOP:
        while (true) {
            io.print("What kind of items do you wish to view?");
            io.print("Available listings: books, internetcontent, podcasts");
            io.print("Select a listing type or type 'return' to return to the main menu.");
            String input = io.getString();
            switch (input) {
                case "return":
                case "r":
                    break LOOP;
                case "books":
                case "book":
                case "b":
                    return viewItems("book");
                case "internetcontent":
                case "i":
                    return viewItems("internetContent");
                case "podcasts":
                case "podcast":
                case "p":
                    return viewItems("podcast");
                default:
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

            io.print("\n" + "Enter an item ID to view more information about the specified item or type 'return' to return to the main menu.");
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
                    io.print("Not a valid input.");
                    io.print(e.getMessage());
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
            io.print("No item found with that id");
            return false;
        }

        io.printItem(item);
        while (true) {
            io.print("Type 'edit' if you want to edit this item's information. type 'return' to return to the previous list or 'main' to return to the main menu.");
            io.print("Otherwise type 'return' to return to the previous list or 'main' to return to the main menu.");
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
        } else if (item.getClass().equals(InternetContent.class))  {
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
            io.print("Item was updated successfully");
        } else {
            io.print("Updating item failed.");
        }

        return success;
    }

    public boolean add() {
        LOOP:
        while (true) {
            io.print("What kind of item do you wish to add?");
            io.print("Available types: book, internetcontent, podcast");
            io.print("Select an item type or type 'return' to return to the main menu.");
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
                    break;
            }
        }

        return false;
    }

    public boolean delete() {
        LOOP:
        while (true) {
            io.print("What kind of item do you wish to delete?");
            io.print("Available types: book, internetcontent, podcast");
            io.print("Select an item type or type 'return' to return to the main menu.");
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
        while(true) {
            io.print("\n" + "Enter the ID of the item you wish to delete or type 'return' to return to the main menu.");
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
                    io.print("Not a valid input.");
                }
            }
        }
    }
}
