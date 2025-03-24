package dk.itu.garbagev6_2025_kotlin

import android.content.Context
import androidx.lifecycle.Observer
import java.io.BufferedReader

class ItemsDB private constructor() {

    //handling files
    private val itemsMap: MutableMap<String, String> = mutableMapOf()

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
    init {
        fillItemsDB(context!!,"garbage.txt")
    }

    fun listItems(): String {
        var r = ""
        for ((key, value) in itemsMap) {
            r += "Put $key: in $value container\n "
        }
        return r
    }

    fun getWhere(what: String): String {
        return itemsMap[what] ?: "Not found"
    }

    fun addItem(what: String, where: String) {
        itemsMap[what] = where
    }

    fun removeItem(what: String){
        itemsMap.remove(what)
    }

    fun isPresent(what: String): Boolean {
        return itemsMap[what] != null
    }

    private fun fillItemsDB(context: Context, fileName: String) {
        try {
            val inputStream = context.assets.open(fileName)
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