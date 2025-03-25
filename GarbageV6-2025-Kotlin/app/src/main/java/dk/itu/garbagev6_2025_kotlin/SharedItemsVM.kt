package dk.itu.garbagev6_2025_kotlin

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedItemsVM : ViewModel() {

    private val itemsDB: ItemsDB = ItemsDB.getInstance()

    val uiState: MutableLiveData<GarbageUiState> =
        MutableLiveData(GarbageUiState(itemsDB.listItems()))

    fun addItem(what: String, where: String, context: Context): String {
        val trimmedWhat = what.trim()
        val trimmedWhere = where.trim()

        return if (trimmedWhat.isEmpty() || trimmedWhere.isEmpty()) {
            context.getString(R.string.empty_toast) + " in both fields"
        } else {
            itemsDB.addItem(trimmedWhat, trimmedWhere)
            uiState.value = GarbageUiState(itemsDB.listItems())
            context.getString(R.string.add_item_notification)
        }
    }

    fun deleteItem(what: String, context: Context): String {
        val trimmedWhat = what.trim()

        return when {
            trimmedWhat.isEmpty() ->
                context.getString(R.string.empty_toast) + " in both fields"
            !itemsDB.isPresent(trimmedWhat) ->
                "$trimmedWhat not found"
            else -> {
                itemsDB.removeItem(trimmedWhat)
                uiState.value = GarbageUiState(itemsDB.listItems())
                context.getString(R.string.delete_item_notification)
            }
        }
    }

    fun getWhere(what: String): String {
        return itemsDB.getWhere(what.trim())
    }

    fun isPresent(what: String): Boolean {
        return itemsDB.isPresent(what.trim())
    }

    data class GarbageUiState(val listItems: String)
}
