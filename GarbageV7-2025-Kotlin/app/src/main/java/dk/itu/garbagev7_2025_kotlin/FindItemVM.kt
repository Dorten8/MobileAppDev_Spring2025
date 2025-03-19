package dk.itu.garbagev7_2025_kotlin

import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FindItemVM : ViewModel() {

    private lateinit var itemsDB: ItemsDB

    private val _navigateToAddItem = MutableLiveData<Boolean>()
    val navigateToAddItem: LiveData<Boolean> get() = _navigateToAddItem

    private val _navigateToDeleteItem = MutableLiveData<Boolean>()
    val navigateToDeleteItem: LiveData<Boolean> get() = _navigateToDeleteItem

    fun onFindItemButtonClick(find_item_et: EditText, activity: FragmentActivity) {

        itemsDB = ItemsDB.getInstance(activity.applicationContext)

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
    }

    fun onDeleteItemButtonClicked() {
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
}