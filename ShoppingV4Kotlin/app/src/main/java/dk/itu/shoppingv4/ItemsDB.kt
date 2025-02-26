package dk.itu.shoppingv4

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class ItemsDB {

    private val _itemsMap = MutableLiveData<MutableMap<String, String>>(mutableMapOf())

    private var observers: MutableList<Observer<Any>> = mutableListOf()

    init {
        fillItemsDB()
    }

    companion object {

        private var sItemsDB: ItemsDB? = null

        fun get(): ItemsDB {
            return sItemsDB ?: ItemsDB().also{sItemsDB = it}
        }
    }

    fun listItems(): String {
        return _itemsMap.value?.entries?.joinToString { "${it.key}: ${it.value}" } ?: "No items"
    }

    fun addItem(what: String, where: String) {
        val currentMap = _itemsMap.value ?: mutableMapOf()
        currentMap[what] = where
        _itemsMap.value = currentMap // This will notify observers
        updateData(currentMap)
    }

    fun removeItem(what: String) {
        val currentMap = _itemsMap.value ?: mutableMapOf()
        currentMap.remove(what)
        _itemsMap.value = currentMap // This will notify observers
    }

    fun getWhere(what: String): String? {
        val currentMap = _itemsMap.value ?: mutableMapOf()
        return currentMap[what]
    }

    fun fillItemsDB() {
        val currentMap = _itemsMap.value ?: mutableMapOf()
        currentMap["coffee"] = "Irma"
        currentMap["carrots"] = "Netto"
        currentMap["milk"] = "Netto"
        currentMap["bread"] = "bakery"
        currentMap["butter"] = "Irma"
        _itemsMap.value = currentMap // Notify observers
    }

    // Observer pattern implementation

    fun addObserver(observer: Observer<Any>) {
        observers.add(observer)
    }

    fun removeObserver(observer: Observer<Any>){
        if(observers.isNotEmpty())
            observers.remove(observer)
    }

    fun updateData(newMap: MutableMap<String, String>){
        for(observer in observers)
            observer.onChanged(newMap)
    }


}