package linkkivinkki.dao;

import linkkivinkki.domain.Item;
import java.util.List;

public interface ItemDao {
    public List findAll(); // Find all items of a type
    public Item findOne(int id); // Find one item of a type
    public boolean add(Item i); // Save one item of a type (!!! SQLite generates IDs automatically, DO NOT SAVE AN ID)
    public boolean delete(int id, int userId); // Delete an item (!!! Must be removed from both the 'Item' and type (E.G. 'Book') tables)
    public boolean update(Item i);
    public List findAllByUserId(int id);
}
