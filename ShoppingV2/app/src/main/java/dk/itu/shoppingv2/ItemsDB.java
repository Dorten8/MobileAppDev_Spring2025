package dk.itu.shoppingv2;

import java.util.ArrayList;
import java.util.List;

public class ItemsDB {
    private static ItemsDB sItemsDB;
    private List<Item> itemsDB= new ArrayList<>();;

    private ItemsDB() { fillItemsDB(); }

    public static ItemsDB get() {
        if (sItemsDB == null) sItemsDB= new ItemsDB();
        return sItemsDB;
    }

    public String listItems() {
        String r= "";
        for(Item i: itemsDB)
            r= r+"\n Buy " + i.toString();
        return r;
    }

    public void addItem(String what, String where){
        itemsDB.add(new Item(what, where));
    }

    public void fillItemsDB() {
        itemsDB.add(new Item("coffee", "Irma"));
        itemsDB.add(new Item("carrots", "Netto"));
        itemsDB.add(new Item("milk", "Netto"));
        itemsDB.add(new Item("bread", "bakery"));
        itemsDB.add(new Item("butter", "Irma"));
    }
}
