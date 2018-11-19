package app;

import dao.BookDao;
import dao.InternetContentDao;
import data.Database;
import io.ConsoleIO;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        Database db = new Database("jdbc:sqlite:database.db");
        BookDao bookDao = new BookDao(db);
        InternetContentDao icDao = new InternetContentDao(db);
        ConsoleIO io = new ConsoleIO();
        
        App app = new App(io, bookDao, icDao);
        app.start();
    }
}