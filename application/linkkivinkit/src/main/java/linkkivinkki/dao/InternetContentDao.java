package linkkivinkki.dao;

import linkkivinkki.data.Database;
import linkkivinkki.domain.InternetContent;
import linkkivinkki.domain.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import linkkivinkki.domain.User;

public class InternetContentDao implements ItemDao {

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
                content.setCreationDate(results.getDate("date_created"));
                content.setUserId(results.getInt("user_id"));

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
                    + "(title, url, description, date_created, user_id) "
                    + "VALUES (?, ?, ?, ?, ?);");
            addContent.setString(1, content.getTitle());
            addContent.setString(2, content.getUrl());
            addContent.setString(3, content.getDescription());
            addContent.setDate(4, new java.sql.Date(content.getCreationDate().getTime()));
            addContent.setInt(5, i.getUserId());
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
     * @param userId
     * @return True if successful, false if something went wrong
     */
    @Override
    public boolean delete(int id, int userId) {
        try {
            Connection conn = database.getConnection();
            PreparedStatement deleteContent = conn.prepareStatement("DELETE FROM InternetContent WHERE id = ? AND user_id = ?;");
            deleteContent.setInt(1, id);
            deleteContent.setInt(2, id);
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
                found.setCreationDate(results.getDate("date_created"));
                found.setUserId(results.getInt("user_id"));
            }

            results.close();
            findContent.close();
            conn.close();

        } catch (SQLException ex) {
            // Do nothing, null is returned at the end of the method
        }

        return found;
    }

    /**
     * Updates an InternetContent
     *
     * @param i - The InternetContent to be updated
     * @return true if successful, false if unsuccessful
     */
    public boolean update(Item i) {
        if (i.getClass() != InternetContent.class) {
            return false;
        }

        InternetContent ic = (InternetContent) i;

        // Verify that the content actually exists in the database
        if (findOne(i.getId()) == null) {
            return false;
        }

        try {
            Connection conn = database.getConnection();
            PreparedStatement updateContent = conn.prepareStatement("UPDATE InternetContent SET title=?, url=?, description=? WHERE id=?;");
            updateContent.setString(1, ic.getTitle());
            updateContent.setString(2, ic.getUrl());
            updateContent.setString(3, ic.getDescription());
            updateContent.setInt(4, ic.getId());
            updateContent.executeUpdate();

            updateContent.close();
            conn.close();
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    @Override
    public List<InternetContent> findAllByUserId(int id) {
        ArrayList<InternetContent> list = new ArrayList<>();

        try {
            Connection conn = database.getConnection();
            PreparedStatement fetch = conn.prepareStatement("SELECT * FROM InternetContent where user_id = ?;");
            fetch.setInt(1, id);
            ResultSet results = fetch.executeQuery();

            while (results.next()) {
                InternetContent content = new InternetContent(results.getString("title"), results.getString("url"), results.getString("description"));
                content.setId(results.getInt("id"));
                content.setCreationDate(results.getDate("date_created"));
                content.setUserId(id);
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
}
