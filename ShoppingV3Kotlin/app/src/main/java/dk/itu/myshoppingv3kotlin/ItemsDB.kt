package dk.itu.myshoppingv3kotlin

class ItemsDB private constructor(){
    private val itemsMap:HashMap<String,String> = HashMap<String,String>()

    init { fillItemsDB()}

    companion object {
        private var sItemsDB: ItemsDB? = null

        fun get(): ItemsDB {
            return sItemsDB ?: ItemsDB().also { sItemsDB = it }

        }
    }

    fun listItems(): String {
        var r = ""
        for ((key, value) in itemsMap)
            r = "$r\n Buy $key in: $value"
        return r
    }

    fun size(): Int {
        return itemsMap.size
    }
    fun getWhere(what: String): String? {
        return itemsMap[what]
    }

    fun addItem(what: String, where: String) {
        itemsMap[what] = where
    }

    fun removeItem(what: String) {
        if (itemsMap[what] != null) itemsMap.remove(what)
    }

    private fun fillItemsDB() {
        itemsMap.put("coffee", "Irma")
        itemsMap.put("carrots", "Netto")
        itemsMap.put("milk", "Netto")
        itemsMap.put("bread", "bakery")
        itemsMap.put("butter", "Irma")
    }
}