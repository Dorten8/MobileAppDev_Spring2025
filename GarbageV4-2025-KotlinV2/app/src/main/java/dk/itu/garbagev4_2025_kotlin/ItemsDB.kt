package dk.itu.garbagev4_2025_kotlin

import android.content.Context
import java.io.BufferedReader
import java.io.File

class ItemsDB private constructor() {

    //Singleton pattern
    companion object {
        private var sItemsDB: ItemsDB? = null

        fun get(): ItemsDB {
            return sItemsDB ?: ItemsDB().also { sItemsDB = it }
        }

        fun getInstance() =
            sItemsDB ?: synchronized(this){
                sItemsDB ?: ItemsDB().also { sItemsDB = it }
            }
    }

    //handling files
    private val itemsMap: MutableMap<String, String> = mutableMapOf()

    //Constructor
    init { fillItemsDB2("assets/garbage.txt") }

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

    private fun fillItemsDB2(filePath: String) {

        File(filePath).useLines { lines ->
            lines.forEach { line ->
                val parts = line.split(", ")
                itemsMap[parts[0]] = parts[1]
            }
        }
    }
}