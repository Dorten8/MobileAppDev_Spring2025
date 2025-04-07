package dk.itu.garbagev7_2025_kotlin

import android.content.Context
import androidx.lifecycle.Observer
import java.io.BufferedReader

class ItemsDB private constructor() {

    //handling files
    val items = ArrayList<Item>()

    //Singleton pattern
    companion object {
        @Volatile
        private var sItemsDB: ItemsDB? = null
        private var context: Context? = null

        fun setContext(ctx: Context) {
            context = ctx
        }

        fun getInstance(): ItemsDB {
            if(sItemsDB == null){
                sItemsDB = ItemsDB()
            }
            return sItemsDB!!
        }
    }

    //Constructor
    init { fillItemsDB(context!!,"garbage.txt") }

    //ADD
    fun addItem(what: String, where: String) {
        items.add(Item(what, where))
    }

    //DELETE
    fun removeItem(what: String, where: String) {
        items.removeIf { it.what == what && it.where == where }
    }


    fun size(): Int {
        return items.size
    }

    fun getWhere(what: String): String {
        for (item in items)
            if (item.what == what) return item.where
        return "not found"
    }

    fun isPresent(what: String): Boolean {
        return getWhere(what) != "not found"
    }

    private fun fillItemsDB(context: Context, fileName: String) {
        try {
            val bufferedReader = BufferedReader(context.assets.open(fileName).reader())
            bufferedReader.useLines {
                    lines -> lines.forEach {
                    line -> addItem(line.split(", ")[0].trim(), line.split(", ")[1].trim())
            }
            }
        }
        catch (e: Exception) { }
    }



}