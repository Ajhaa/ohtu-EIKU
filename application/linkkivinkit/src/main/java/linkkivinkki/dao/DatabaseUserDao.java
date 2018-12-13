package linkkivinkki.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import linkkivinkki.data.Database;
import linkkivinkki.domain.User;

public class DatabaseUserDao implements UserDao {

    private Database database;

    public DatabaseUserDao(Database database) {
        this.database = database;
    }

    @Override
    public List<User> findAll() {
        List<User> all = new ArrayList<>();

        try {
            Connection conn = database.getConnection();
            PreparedStatement fetch = conn.prepareStatement("SELECT * FROM User;");
            ResultSet results = fetch.executeQuery();

            while (results.next()) {
                User u = new User(results.getString("username"));
                u.setId(results.getInt("id"));
                all.add(new User());
            }

            results.close();
            fetch.close();
            conn.close();

        } catch (SQLException e) {
            // do nothing, an empty list is returned at the end of the method
        }

        return all;
    }

    @Override
    public User findOne(int id) {
        User u = null;

        try {
            Connection conn = database.getConnection();
            PreparedStatement fetch = conn.prepareStatement("SELECT * FROM USER WHERE id = ?;");
            fetch.setInt(1, id);
            ResultSet results = fetch.executeQuery();

            if (results.next()) {
                u = new User(results.getString("username"));
                u.setId(id);
            }

            results.close();
            fetch.close();
            conn.close();

        } catch (SQLException e) {
            // do nothing, null is returned at the end of the method
        }

        return u;
    }

    @Override
    public User findOneByName(String username) {
        User u = null;

        try {
            Connection conn = database.getConnection();
            PreparedStatement fetch = conn.prepareStatement("SELECT * FROM USER WHERE username = ?;");
            fetch.setString(1, username);
            ResultSet results = fetch.executeQuery();

            if (results.next()) {
                u = new User(username);
                u.setId(results.getInt("id"));
            }

            results.close();
            fetch.close();
            conn.close();

        } catch (SQLException e) {
            // do nothing, null is returned at the end of the method
        }

        return u;
    }

    @Override
    public boolean add(User u) {
        try {
            Connection conn = database.getConnection();
            
            // Make sure there are no users with identical usernames
            if (findOneByName(u.getUsername()) != null) {
                return false;
            }
            
            PreparedStatement addUser = conn.prepareStatement("INSERT INTO User (username) VALUES (?);");
            addUser.setString(1, u.getUsername());

            addUser.execute();
            addUser.close();
            conn.close();
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean delete(int id) {
        try {
            Connection conn = database.getConnection();
            PreparedStatement deleteUser = conn.prepareStatement("DELETE FROM User WHERE id = ?;");
            deleteUser.setInt(1, id);

            deleteUser.execute();
            deleteUser.close();
            conn.close();
        } catch (SQLException e) {
            return false;
        }

        return true;
    }
}
