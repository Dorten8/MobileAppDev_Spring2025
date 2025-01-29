package com.example.garbage_v1_2024;

import java.util.ArrayList;
import java.util.List;

public class ItemsDB {
    private List<Item> ItemsDB= new ArrayList<>();

    public ItemsDB() { }

    public String search(String word){
        String name = "";
        boolean exist = false;
        for (Item item : ItemsDB) {
            if(item.getWhat().equals(word)) {
                name = item.toString();
                return name;

            }
            }
        if(exist==false) {
            Item item = new Item(word, "not found");
            name = item.toString();
        }
        return name;
    }

    public void addItem(String what, String where){
        ItemsDB.add(new Item(what, where));
    }

    public void fillItemsDB() {
        ItemsDB.add(new Item("milk carton", "Food"));
        ItemsDB.add(new Item("can", "Metal"));
        ItemsDB.add(new Item("smartphone", "Electronic"));
        ItemsDB.add(new Item("battery", "Batteries"));
        ItemsDB.add(new Item("flowers", "Garden waste"));
        ItemsDB.add(new Item("box", "Pap"));
    }
}
