package dk.itu.garbagev6_2025_kotlin

import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddItemVM : ViewModel() {

    private var itemsDB: ItemsDB = ItemsDB.getInstance()

    val uiState: MutableLiveData<GarbageUiState> = MutableLiveData<GarbageUiState>(GarbageUiState(itemsDB.listItems()))

    fun onAddItemButtonClick(add_item_what_et: EditText, add_item_where_et: EditText, activity: FragmentActivity){

        //itemsDB = ItemsDB.getInstance(activity.applicationContext)

        val itemWhat = add_item_what_et.text.toString().trim()
        val itemWhere = add_item_where_et.text.toString().trim()

        val message = if (itemWhat.isEmpty() || itemWhere.isEmpty()) {
            "${activity.getString(R.string.empty_toast)} in both fields"
        } else {
            itemsDB.addItem(itemWhat, itemWhere)
            uiState.value = GarbageUiState(itemsDB.listItems())
            add_item_what_et.text.clear()
            add_item_where_et.text.clear()
            "${activity.getString(R.string.add_item_notification)}"
        }

        showToast(activity, message)
    }

    private fun showToast(activity: FragmentActivity, message: CharSequence) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    data class GarbageUiState(
        val listItems: String
    )

}