package linkkivinkki.dao;

import java.util.List;
import linkkivinkki.domain.User;

public interface UserDao {
    public List findAll();
    public User findOne(int id);
    public User findOneByName(String name);
    public boolean add(User u);
    public boolean delete(int id);
}
