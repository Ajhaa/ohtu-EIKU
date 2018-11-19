
package app;

import dao.Dao;
import domain.Book;
import domain.InternetContent;
import domain.Podcast;
import io.IO;

public class App {
    private IO io;
    private Dao bookDao;
    
    public App(IO io, Dao bookDao) {
        this.io = io;
        this.bookDao = bookDao;
    }
    
    public void start() {
        io.print("App started.");
        
        LOOP:
        while (true) {
            io.print("Type 'view' to view existing memo items or type 'add' to add a new memo item.");
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
                default:
                    break;
            }
        }
        
        io.print("Shutting down.");
    }
    
    public boolean view() {
        return true;
    }
    
    public boolean add() {
        while (true) {
            io.print("What kind of item do you wish to add?");
            io.print("Available types: book/internetcontent/podcast");
            io.print("Type 'return' to return to the main menu.");
            String input = io.getString();
            
            if (input.equals("return") || input.equals("r")) {
                break;
            }
            
            switch (input) {
                case "book":
                case "b":
                    Book newBook = io.newBook();
                    return bookDao.add(newBook);
                case "internetcontent":
                case "i":
                    InternetContent newInternetContent = io.newInternetContent();
                    io.print("not supported");
                    break;
                case "podcast":
                case "p":
                    Podcast newPodcast = io.newPodcast();
                    io.print("not supported");
                    break;
                default:
                    break;
            }
        }
        
        return false;
    } 
}
