package dk.itu.garbagev7_2025_kotlin

import android.content.Context
import androidx.lifecycle.Observer
import java.io.BufferedReader

class ItemsDB private constructor(context: Context) {

    //handling files
    private val itemsMap: MutableMap<String, String> = mutableMapOf()

    private var observers: MutableList<Observer<Any>> = mutableListOf()

    //Singleton pattern
    companion object {
        @Volatile
        private var sItemsDB: ItemsDB? = null

        fun getInstance(context: Context) =
            sItemsDB ?: synchronized(this){
                sItemsDB ?: ItemsDB(context).also { sItemsDB = it }
            }
    }


    //Constructor
    init { fillItemsDB(context,"garbage.txt") }

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
        updateData()
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

    fun addObserver (observer : Observer<Any>) {
        observers.add(observer)
    }

    fun updateData () {
        for (observer in observers){
            observer.onChanged("")
        }

    }

}