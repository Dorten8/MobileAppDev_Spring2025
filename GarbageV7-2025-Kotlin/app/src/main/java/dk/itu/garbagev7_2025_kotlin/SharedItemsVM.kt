package dk.itu.garbagev7_2025_kotlin

import android.content.Context
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedItemsVM : ViewModel() {

    private val itemsDB: ItemsDB = ItemsDB.getInstance()

    val uiState: MutableLiveData<GarbageUiState> = MutableLiveData(GarbageUiState(itemsDB.items, itemsDB.size()))

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

    fun addItem(what: String, where: String, activity: FragmentActivity){
        val trimmedWhat = what.trim()
        val trimmedWhere = where.trim()

        val message = if (trimmedWhat.isEmpty() || trimmedWhere.isEmpty()){
            R.string.empty_toast
        }else{
            itemsDB.addItem(trimmedWhat, trimmedWhere)
            uiState.value = GarbageUiState(itemsDB.items, itemsDB.size())
            R.string.add_item_notification
        }
        showToast(activity, activity.getString(message))
    }

    fun deleteItem(what: String, activity: FragmentActivity) {
        val trimmedWhat = what.trim()
        val where = itemsDB.getWhere(trimmedWhat)

        val message = if ( trimmedWhat.isEmpty() ) {
            activity.getString(R.string.empty_toast)
        }else if ( !itemsDB.isPresent(trimmedWhat) ) {
            "$trimmedWhat not found"
        }else{
            itemsDB.removeItem(trimmedWhat, where)
            uiState.value = GarbageUiState(itemsDB.items, itemsDB.size())
            activity.getString(R.string.delete_item_notification)
        }

        showToast(activity, message)
    }

    private fun showToast(activity: FragmentActivity, message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    data class GarbageUiState(
        val itemList: ArrayList<Item>,
        val itemListSize: Int
    )
}
