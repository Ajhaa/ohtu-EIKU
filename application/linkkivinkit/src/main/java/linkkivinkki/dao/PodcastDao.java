package linkkivinkki.dao;

import linkkivinkki.data.Database;
import linkkivinkki.domain.Item;
import linkkivinkki.domain.Podcast;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PodcastDao implements Dao {

    private Database database;

    public PodcastDao(Database database) {
        this.database = database;
    }

    /**
     * Fetch all Podcasts from the database
     *
     * @return a list containing every Podcast
     */
    @Override
    public ArrayList<Podcast> findAll() {
        ArrayList<Podcast> list = new ArrayList<>();

        try {
            Connection conn = database.getConnection();
            PreparedStatement fetch = conn.prepareStatement("SELECT * FROM Podcast;");
            ResultSet results = fetch.executeQuery();
            while (results.next()) {
                Podcast p = new Podcast(results.getString("name"), results.getString("title"), results.getString("description"));
                p.setId(results.getInt("id"));
                // Other content related to the Item parent class should be inserted here when applicable

                list.add(p);
            }

            // Close the connection
            results.close();
            fetch.close();
            conn.close();

        } catch (SQLException ex) {
            // Nothing happens, an empty list is returned at the end of the method
        }

        return list;
    }

    /**
     *
     * @param i - The item to be added
     * @return
     */
    @Override
    public boolean add(Item i) {
        // Make sure that the item we wish to add is a Podcast
        if (i.getClass() != Podcast.class) {
            return false;
        }

        // Cast
        Podcast p = (Podcast) i;

        try {
            Connection conn = database.getConnection();

            PreparedStatement addPodcast = conn.prepareStatement("INSERT INTO Podcast "
                    + "(name, title, description) "
                    + "VALUES (?, ?, ?);");
            addPodcast.setString(1, p.getName());
            addPodcast.setString(2, p.getTitle());
            addPodcast.setString(3, p.getDescription());
            addPodcast.execute();

            // Close the connection
            addPodcast.close();
            conn.close();

        } catch (SQLException ex) {
            return false;
        }

        return true;
    }

    /**
     * Deletes a Podcast with the given id
     *
     * @param id - The id of the Podcast to be deleted
     * @return True if successful, false if something went wrong
     */
    @Override
    public boolean delete(int id) {
        try {
            Connection conn = database.getConnection();
            PreparedStatement deletePodcast = conn.prepareStatement("DELETE FROM Podcast WHERE id = ?;");
            deletePodcast.setInt(1, id);
            deletePodcast.execute();

            // Close the connection
            deletePodcast.close();
            conn.close();

        } catch (SQLException ex) {
            return false;
        }

        return true;
    }

    /**
     * Connects to the database and looks for a Podcast with the given id
     *
     * @param id - The id of the Podcast to be searched for
     * @return Podcast with the given id if successful, null otherwise
     */
    public Podcast findOne(int id) {
        Podcast found = null;
        try {
            Connection conn = database.getConnection();
            PreparedStatement findPodcast = conn.prepareStatement("SELECT * FROM Podcast WHERE id = ?;");
            findPodcast.setInt(1, id);
            ResultSet results = findPodcast.executeQuery();

            if (results.next()) {
                found = new Podcast(results.getString("name"), results.getString("title"), results.getString("description"));
                found.setId(id);
            }

            results.close();
            findPodcast.close();
            conn.close();

        } catch (SQLException ex) {
            // Do nothing, null is returned at the end of the method
        }

        return found;
    }
}
