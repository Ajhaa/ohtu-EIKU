package linkkivinkki.dao;

import java.util.List;
import java.util.ArrayList;
import linkkivinkki.domain.Item;

public class InMemoryDao implements Dao {

    private List<Item> items;

    public InMemoryDao() {
        items = new ArrayList<Item>();
    }

    @Override
    public boolean add(Item i) {
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
    
    public Item findOne(int id) {
        return items.get(id);
    }

}