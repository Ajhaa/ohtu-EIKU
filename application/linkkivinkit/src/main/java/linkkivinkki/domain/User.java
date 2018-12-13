package linkkivinkki.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    
    private int id;
    private String username;
    private List<Item> items;
    
    public User(int id, String username, List<Item> items) {
        this.id = id;
        this.username = username;
        this.items = items;
    }
    
    public User(String username, List<Item> items) {
        this(-1, username, items);
    }
    
    public User(String username) {
        this(-1, username, new ArrayList<>());
    }
    
    public User() {
        this(-1, "kÄytTäjÄ", new ArrayList<>());
    }
    
    public int getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    public List<Item> getItems() {
        return items;
    }
}
