package linkkivinkki.app;

import linkkivinkki.dao.BookDao;
import linkkivinkki.dao.InternetContentDao;
import linkkivinkki.dao.PodcastDao;
import linkkivinkki.dao.UserDao;
import linkkivinkki.data.Database;
import linkkivinkki.io.ConsoleIO;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        Database db = new Database("jdbc:sqlite:database.db");
        BookDao bookDao = new BookDao(db);
        InternetContentDao icDao = new InternetContentDao(db);
        PodcastDao podcastDao = new PodcastDao(db);
        UserDao userDao = new UserDao(db);
        ConsoleIO io = new ConsoleIO();
        
        App app = new App(io, bookDao, icDao, podcastDao, userDao);
        app.start();
    }
}