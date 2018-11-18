package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Handles connecting to the Database.
 * Current database structure: http://yuml.me/edit/17c7d172
 */
public class Database {

    private String address;

    public Database(String address) throws SQLException {
        this.address = address;
        init();
    }
    
    /**
     * Attempts to connect to the database specified in the constructor
     * @return A Connection to the database, if successful
     * @throws SQLException 
     */
    public Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(address);

        return conn;
    }

    private void init() throws SQLException {
        try {
            Connection conn = getConnection();
            
            PreparedStatement initBook = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Book ("
                    + "id integer PRIMARY KEY, "
                    + "title varchar(255), "
                    + "author varchar(255)); "
            );
            initBook.execute();
            initBook.close();

            PreparedStatement initLink = conn.prepareStatement("CREATE TABLE IF NOT EXISTS InternetContent ("
                    + "id integer PRIMARY KEY, "
                    + "title varchar(255), "
                    + "url varchar(255));"
            );
            initLink.execute();
            initLink.close();

            PreparedStatement initPodcast = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Podcast ("
                    + "id integer PRIMARY KEY, "
                    + "name varchar(255), " // 'name' is the name of the Podcast, E.G. 'The EIKU Podcast'
                    + "title varchar(255), " // 'title' is the title of the episode, E.G. 'Ajhaa talks about MongoDB'
                    + "description varchar(255));"
            );
            initPodcast.execute();
            initPodcast.close();
            
            conn.close();

        } catch (SQLException e) {
            // Everything explodes
        }
    }

}
