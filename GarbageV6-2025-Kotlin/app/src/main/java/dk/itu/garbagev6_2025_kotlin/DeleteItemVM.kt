package dk.itu.garbagev6_2025_kotlin

import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DeleteItemVM : ViewModel(){

    private var itemsDB: ItemsDB = ItemsDB.getInstance()

    val uiState: MutableLiveData<GarbageUiState> = MutableLiveData<GarbageUiState>(GarbageUiState(itemsDB.listItems()))

    fun onDeleteItemButtonClick(delete_item_et: EditText, activity: FragmentActivity){

        val itemWhat = delete_item_et.text.toString().trim()

        val message = if (itemsDB.isPresent(itemWhat)){
            itemsDB.removeItem(itemWhat)
            uiState.value = GarbageUiState(itemsDB.listItems())
            delete_item_et.text.clear()
            "${activity.getString(R.string.delete_item_notification)}"
        } else if (itemWhat.isEmpty()){
            "${activity.getString(R.string.empty_toast)} in both fields"
        } else{
            "$itemWhat not found"
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