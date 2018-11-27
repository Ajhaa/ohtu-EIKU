package linkkivinkki.dao;

import linkkivinkki.data.Database;
import linkkivinkki.domain.Book;
import linkkivinkki.domain.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class BookDao implements Dao {

    private Database database;

    public BookDao(Database database) {
        this.database = database;
    }

    /**
     * Fetch all Books from the database
     *
     * @return a list containing every book
     */
    @Override
    public ArrayList<Book> findAll() {
        ArrayList<Book> list = new ArrayList<>();

        try {
            Connection conn = database.getConnection();
            PreparedStatement fetch = conn.prepareStatement("SELECT * FROM Book;");
            ResultSet results = fetch.executeQuery();
            while (results.next()) {
                Book b = new Book(results.getString("author"), results.getString("title"), results.getString("description"));
                b.setId(results.getInt("id"));
                // Other content related to the Item parent class should be inserted here when applicable

                list.add(b);
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
        // Make sure that the item we wish to add is a Book
        if (i.getClass() != Book.class) {
            return false;
        }

        // Cast
        Book b = (Book) i;

        try {
            Connection conn = database.getConnection();

            PreparedStatement addBook = conn.prepareStatement("INSERT INTO Book "
                    + "(title, author, description) "
                    + "VALUES (?, ?, ?);");
            addBook.setString(1, b.getTitle());
            addBook.setString(2, b.getAuthor());
            addBook.setString(3, b.getDescription());
            addBook.execute();

            // Close the connection
            addBook.close();
            conn.close();

        } catch (SQLException ex) {
            return false;
        }

        return true;
    }

    /**
     * Deletes a Book with the given id
     *
     * @param id - The id of the Book to be deleted
     * @return True if successful, false if something went wrong
     */
    @Override
    public boolean delete(int id) {
        try {
            Connection conn = database.getConnection();
            PreparedStatement deleteBook = conn.prepareStatement("DELETE FROM Book WHERE id = ?;");
            deleteBook.setInt(1, id);
            deleteBook.execute();

            // Close the connection
            deleteBook.close();
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
    @Override
    public Book findOne(int id) {
        Book found = null;
        try {
            Connection conn = database.getConnection();
            PreparedStatement findBook = conn.prepareStatement("SELECT * FROM Book WHERE id = ?;");
            findBook.setInt(1, id);
            ResultSet results = findBook.executeQuery();

            if (results.next()) {
                found = new Book(results.getString("author"), results.getString("title"), results.getString("description"));
                found.setId(id);
            }

            results.close();
            findBook.close();
            conn.close();

        } catch (SQLException ex) {
            // Do nothing, null is returned at the end of the method
        }

        return found;
    }
    /**
     * Updates a book
     *
     * @param i - The Book to be updated
     * @return true if successful, false if unsuccessful
     */
    public boolean update(Item i) {
        if (i.getClass() != Book.class) {
            return false;
        }
        
        Book b = (Book) i;

        // Verify that the book actually exists in the database
        if (findOne(i.getId()) == null) {
            return false;
        }
        
        try {
            Connection conn = database.getConnection();
            PreparedStatement updateBook = conn.prepareStatement("UPDATE Book SET title=?, author=?, description=? WHERE id=?;");
            updateBook.setString(1, b.getTitle());
            updateBook.setString(2, b.getAuthor());
            updateBook.setString(3, b.getDescription());
            updateBook.setInt(4, b.getId());
            updateBook.executeUpdate();
            
            updateBook.close();
            conn.close();
        } catch (SQLException e) {
            return false;
        }
        
        return true;
    }

}
