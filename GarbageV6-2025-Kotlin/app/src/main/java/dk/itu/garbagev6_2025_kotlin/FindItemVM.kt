package dk.itu.garbagev6_2025_kotlin

import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FindItemVM : ViewModel() {

    private var itemsDB: ItemsDB = ItemsDB.getInstance()

    private val _uiState = MutableLiveData<Boolean>()

    val navigateToAddItem: LiveData<Boolean> get() = _uiState
    val navigateToDeleteItem: LiveData<Boolean> get() = _uiState

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
        _uiState.value = true
    }

    fun onDeleteItemButtonClicked() {
        _uiState.value = true
    }

    // Reset event after navigation is handled
    fun onNavigationHandled() {
        _uiState.value = false
    }

    private fun showToast(activity: FragmentActivity, message: CharSequence) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

}