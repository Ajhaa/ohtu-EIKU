package linkkivinkki.dao;

import java.util.ArrayList;
import java.util.List;

import linkkivinkki.domain.User;

public class InMemoryUserDao implements UserDao {

    private List<User> users;

    public InMemoryUserDao() {
        users = new ArrayList<User>();
    }

    @Override
    public boolean add(User u) {
        System.out.println("adding ");
        return users.add(u);
    }

    @Override
    public boolean delete(int id) {
        return true;
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findOne(int id) {
        for (User u : users) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    @Override
    public User findOneByName(String name) {
        for (User u : users) {
            if (u.getUsername() == name) {
                return u;
            }
        }
        return null;
    }

}
