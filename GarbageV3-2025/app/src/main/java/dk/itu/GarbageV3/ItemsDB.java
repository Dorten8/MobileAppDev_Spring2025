package dk.itu.GarbageV3;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ItemsDB {
    private static ItemsDB sItemsDB;

    private final HashMap<String, String> ItemsDB_map = new HashMap<String, String>();

    private static Context context;

    private ItemsDB(){
        if(context == null){
            throw new IllegalStateException("context must be set first!");
        }
        fillItemsDB(context, "garbage.txt");
    }

    public static void setContext(Context aContext) {
        context = aContext;
    }

    // Singleton pattern get implementation
    public static ItemsDB get(){
        if (sItemsDB == null) sItemsDB = new ItemsDB();
        return sItemsDB;
    }

    public HashMap<String, String> getItemsDB_map() {
        return ItemsDB_map;
    }

    public String listItems() {
        String r= "";
        for(HashMap.Entry<String, String> item : ItemsDB_map.entrySet())
            r= r+"\n Put " + item.getKey() + " in: " + item.getValue();
        return r;
    }

    public String findCategory(String item){

        for (HashMap.Entry<String, String> i : ItemsDB_map.entrySet()){
            if (i.getKey().equals(item))
                return i.getValue();
        }
        return "Not found";

    }

    // Will be used later
    public void addItem(String what, String where){
        ItemsDB_map.put(what, where);
    }

    public void fillItemsDB(Context context, String filename) {
        try {
            BufferedReader reader = new BufferedReader( new InputStreamReader(context.getAssets().open(filename)) );
            String line = reader.readLine();
            while (line != null){
                String[] what_where = line.split(", ");
                String what = what_where[0];
                String where = what_where[1];
                ItemsDB_map.put(what, where);
                line = reader.readLine();
            }
        } catch (IOException e) { }
    }

}
