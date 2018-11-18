package dao;

import data.Database;
import domain.Book;
import domain.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public List<Book> findAll() {
        List<Book> list = new ArrayList<>();

        try {
            Connection conn = database.getConnection();
            PreparedStatement fetch = conn.prepareStatement("SELECT * FROM Book;");
            ResultSet results = fetch.executeQuery();

            while (results.next()) {
                Book b = new Book(results.getString("title"), results.getString("author"));
                b.setId(results.getInt("item_id"));
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
                    + "(title, author) "
                    + "VALUES (?, ?);");
            addBook.setString(1, b.getTitle());
            addBook.setString(2, b.getAuthor());
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

}
