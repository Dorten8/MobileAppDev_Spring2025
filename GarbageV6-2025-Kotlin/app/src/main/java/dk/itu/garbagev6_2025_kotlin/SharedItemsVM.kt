package dk.itu.garbagev6_2025_kotlin

import android.content.Context
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//from ShoppingV5

class SharedItemsVM : ViewModel() {

    private val itemsDB: ItemsDB = ItemsDB.getInstance()

    val uiState: MutableLiveData<GarbageUiState> = MutableLiveData(GarbageUiState(itemsDB.listItems()))

    private val _navigateToAddItem = MutableLiveData<Boolean>()
    private val _navigateToDeleteItem = MutableLiveData<Boolean>()

    val navigateToAddItem: LiveData<Boolean> get() = _navigateToAddItem
    val navigateToDeleteItem: LiveData<Boolean> get() = _navigateToDeleteItem

    fun onFindItemButtonClick(find_item_et: EditText, activity: FragmentActivity) {

        val itemWhat = find_item_et.text.toString().trim()
        val itemWhere = itemsDB.getWhere(itemWhat)

        val message = if (itemsDB.isPresent(itemWhat)) {
            find_item_et.setText("Item ${itemWhat} should be placed in: ${itemWhere} container")
            "$itemWhat found"
        } else {
            "$itemWhat not found"
        }
        showToast(activity, message)
    }

    fun onFindItemEditTextClick(find_item_et: EditText){
        find_item_et.text.clear()
    }

    fun onAddItemButtonClicked() {
        _navigateToAddItem.value = true
        _navigateToDeleteItem.value = false
    }

    fun onDeleteItemButtonClicked() {
        _navigateToAddItem.value = false
        _navigateToDeleteItem.value = true
    }

    // Reset event after navigation is handled
    fun onNavigationHandled() {
        _navigateToAddItem.value = false
        _navigateToDeleteItem.value = false
    }

    private fun showToast(activity: FragmentActivity, message: CharSequence) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

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

    data class GarbageUiState(val listItems: String)
}
