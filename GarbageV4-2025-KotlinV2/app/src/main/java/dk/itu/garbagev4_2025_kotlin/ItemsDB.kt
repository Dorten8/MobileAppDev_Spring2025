package dk.itu.garbagev4_2025_kotlin

import android.content.Context
import java.io.BufferedReader

class ItemsDB private constructor(private val context: Context) {
    //handling files
    private val itemsMap: MutableMap<String, String> = mutableMapOf()
    //Constructor
    init { fillItemsDB(context, "garbage.txt") }

    //Singleton pattern
    companion object {
        private var sItemsDB: ItemsDB? = null

        fun get(context: Context): ItemsDB {
            return sItemsDB ?: ItemsDB(context).also { sItemsDB = it }
        }
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