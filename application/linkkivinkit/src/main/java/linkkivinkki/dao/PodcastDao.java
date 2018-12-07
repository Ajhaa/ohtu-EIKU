package linkkivinkki.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static jdk.nashorn.internal.runtime.Debug.id;

import linkkivinkki.data.Database;
import linkkivinkki.domain.Item;
import linkkivinkki.domain.Podcast;
import linkkivinkki.domain.User;

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
                p.setCreationDate(results.getDate("date_created"));
                p.setUserId(results.getInt("user_id"));
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
                    + "(name, title, description, date_created, user_id) "
                    + "VALUES (?, ?, ?, ?, ?);");
            addPodcast.setString(1, p.getName());
            addPodcast.setString(2, p.getTitle());
            addPodcast.setString(3, p.getDescription());
            addPodcast.setDate(4, new java.sql.Date(p.getCreationDate().getTime()));
            addPodcast.setInt(5, i.getUserId());
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
                found.setCreationDate(results.getDate("date_created"));
                found.setUserId(results.getInt("user_id"));
            }

            results.close();
            findPodcast.close();
            conn.close();

        } catch (SQLException ex) {
            // Do nothing, null is returned at the end of the method
        }

        return found;
    }

    /**
     * Updates the given Podcast
     *
     * @param i - The Podcast to be updated
     * @return true if successful, false if unsuccessful
     */
    public boolean update(Item i) {
        if (i.getClass() != Podcast.class) {
            return false;
        }

        Podcast p = (Podcast) i;

        // Verify that the podcast actually exists in the database
        if (findOne(i.getId()) == null) {
            return false;
        }

        try {
            Connection conn = database.getConnection();
            PreparedStatement updatePodcast = conn.prepareStatement("UPDATE Podcast SET name=?, title=?, description=? WHERE id=?;");
            updatePodcast.setString(1, p.getName());
            updatePodcast.setString(2, p.getTitle());
            updatePodcast.setString(3, p.getDescription());
            updatePodcast.setInt(4, p.getId());
            updatePodcast.executeUpdate();

            updatePodcast.close();
            conn.close();
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    @Override
    public List<Podcast> findAllByUserId(int id) {
        ArrayList<Podcast> list = new ArrayList<>();

        try {
            Connection conn = database.getConnection();
            PreparedStatement fetch = conn.prepareStatement("SELECT * FROM Podcast WHERE user_id = ?;");
            fetch.setInt(1, id);
            ResultSet results = fetch.executeQuery();
            while (results.next()) {
                Podcast p = new Podcast(results.getString("name"), results.getString("title"), results.getString("description"));
                p.setId(results.getInt("id"));
                p.setCreationDate(results.getDate("date_created"));
                p.setUserId(id);
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
}
