package dk.itu.garbagev6_2025_kotlin

import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListItemsVM: ViewModel() {

    //private lateinit var itemsDB: ItemsDB
    private var itemsDB: ItemsDB = ItemsDB.getInstance()

    val uiState: MutableLiveData<GarbageUiState> = MutableLiveData<GarbageUiState>(GarbageUiState(itemsDB.listItems()))


    fun onListItemsTextViewDisplay(list_items_tw: TextView, activity: FragmentActivity){


        if (itemsDB.listItems().isEmpty()){
            val message = "${activity.getString(R.string.list_items_notification)}"
            showToast(activity, message)
        }else{
            list_items_tw.text = "Garbage Sorting list \n" + itemsDB.listItems()
        }

    }

    private fun showToast(activity: FragmentActivity, message: CharSequence) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }


    data class GarbageUiState(
        val listItems: String
    )
}