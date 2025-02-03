package dk.itu.GarbageV2;

import java.util.ArrayList;
import java.util.List;

public class ItemsDB {
    private static ItemsDB sItemsDB;
    private List<Item> ItemsDB= new ArrayList<>();

    public ItemsDB() { }

    // Singleton pattern get implementation
    public static ItemsDB get() {
        if (sItemsDB == null) {
            sItemsDB = new ItemsDB();
        }
        return sItemsDB;
    }

    public String listItems() {
        String r= "";
        for(Item i: ItemsDB)
            r= r+"\n Buy " + i.toString();
        return r;
    }

public String findCategory(String item){

        for (Item i:ItemsDB){
            if (i.getWhat().equals(item))
                return i.getWhere();
        }
        return "Not found";

}

    // Will be used later
    public void addItem(String what, String where){
        ItemsDB.add(new Item(what, where));
    }

    public void fillItemsDB() {
        ItemsDB.add(new Item("Milk Carton", "Food"));
        ItemsDB.add(new Item("Carrots", "Organic"));
        ItemsDB.add(new Item("Can", "Metal"));
        ItemsDB.add(new Item("Bottle", "Glass"));
        ItemsDB.add(new Item("Plate", "Porcelain"));
    }
}
