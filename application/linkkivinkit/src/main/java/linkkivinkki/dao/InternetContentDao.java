package linkkivinkki.dao;

import linkkivinkki.data.Database;
import linkkivinkki.domain.InternetContent;
import linkkivinkki.domain.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InternetContentDao implements Dao {

    private Database database;

    public InternetContentDao(Database database) {
        this.database = database;
    }

    /**
     * Fetch all InternetContents from the database
     *
     * @return a list containing every InternetContent
     */
    @Override
    public ArrayList<InternetContent> findAll() {
        ArrayList<InternetContent> list = new ArrayList<>();

        try {
            Connection conn = database.getConnection();
            PreparedStatement fetch = conn.prepareStatement("SELECT * FROM InternetContent;");
            ResultSet results = fetch.executeQuery();

            while (results.next()) {
                InternetContent content = new InternetContent(results.getString("title"), results.getString("url"), results.getString("description"));
                content.setId(results.getInt("id"));
                // Other content related to the Item parent class should be inserted here when applicable

                list.add(content);
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
        // Make sure that the item we wish to add is a InternetContent
        if (i.getClass() != InternetContent.class) {
            return false;
        }

        // Cast
        InternetContent content = (InternetContent) i;

        try {
            Connection conn = database.getConnection();

            PreparedStatement addContent = conn.prepareStatement("INSERT INTO InternetContent "
                    + "(title, url, description) "
                    + "VALUES (?, ?, ?);");
            addContent.setString(1, content.getTitle());
            addContent.setString(2, content.getUrl());
            addContent.setString(3, content.getDescription());
            addContent.execute();

            // Close the connection
            addContent.close();
            conn.close();

        } catch (SQLException ex) {
            return false;
        }

        return true;
    }

    /**
     * Deletes a InternetContent with the given id
     *
     * @param id - The id of the InternetContent to be deleted
     * @return True if successful, false if something went wrong
     */
    @Override
    public boolean delete(int id) {
        try {
            Connection conn = database.getConnection();
            PreparedStatement deleteContent = conn.prepareStatement("DELETE FROM InternetContent WHERE id = ?;");
            deleteContent.setInt(1, id);
            deleteContent.execute();

            // Close the connection
            deleteContent.close();
            conn.close();

        } catch (SQLException ex) {
            return false;
        }

        return true;
    }

    /**
     * Connects to the database and looks for an InternetContent with the given
     * id
     *
     * @param id - The id of the InternetContent to be searched for
     * @return InternetContent with the given id if successful, null otherwise
     */
    public InternetContent findOne(int id) {
        InternetContent found = null;
        try {
            Connection conn = database.getConnection();
            PreparedStatement findContent = conn.prepareStatement("SELECT * FROM InternetContent WHERE id = ?;");
            findContent.setInt(1, id);
            ResultSet results = findContent.executeQuery();

            if (results.next()) {
                found = new InternetContent(results.getString("title"), results.getString("url"), results.getString("description"));
                found.setId(id);
            }
            
            results.close();
            findContent.close();
            conn.close();

        } catch (SQLException ex) {
            // Do nothing, null is returned at the end of the method
        }

        return found;
    }
}
