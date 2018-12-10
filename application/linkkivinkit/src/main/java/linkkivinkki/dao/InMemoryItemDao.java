package linkkivinkki.dao;

import java.util.ArrayList;
import java.util.List;
import static jdk.nashorn.internal.runtime.Debug.id;

import linkkivinkki.domain.Item;
import linkkivinkki.domain.User;

public class InMemoryItemDao implements ItemDao {

    private List<Item> items;

    public InMemoryItemDao() {
        items = new ArrayList<Item>();
    }

    @Override
    public boolean add(Item i) {
        System.out.println("adding ");
        return items.add(i);
    }

    @Override
    public boolean delete(int id) {
        return true;
    }

    @Override
    public List<Item> findAll() {
        return items;
    }

    @Override
    public Item findOne(int id) {
        for (Item i : items) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    @Override
    public boolean update(Item i) {
        for (Item item : items) {
            if (item.getId() == i.getId()) {
                items.remove(item);
                items.add(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Item> findAllByUserId(int id) {
        List<Item> found = new ArrayList<>();
        
        for (Item i : items) {
            if (i.getUserId() == id) {
                found.add(i);
            }
        }
        
        return found;
    }

}
