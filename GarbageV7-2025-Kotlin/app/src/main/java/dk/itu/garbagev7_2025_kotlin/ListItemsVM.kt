package dk.itu.garbagev7_2025_kotlin

import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

class ListItemsVM: ViewModel() {

    private lateinit var itemsDB: ItemsDB

    //exposing mutable map (V7) as live data
    private val _itemsMap = MutableLiveData<MutableMap<String, String>>()
    val itemsMap: LiveData<MutableMap<String, String>>
        get() = _itemsMap

    fun initilize(activity: FragmentActivity){
        itemsDB = ItemsDB.getInstance(activity.applicationContext)
        loadItems()
    }

    private fun loadItems(){
        _itemsMap.value = itemsDB.getItemsMap()
    }

    fun deleteItem(what: String){
        itemsDB.removeItem(what)
        loadItems()
    }

    fun onListItemsTextViewDisplay(list_items_rv: RecyclerView, activity: FragmentActivity){

        itemsDB = ItemsDB.getInstance(activity.applicationContext)

        if (itemsDB.listItems().isEmpty()){
            val message = "${activity.getString(R.string.list_items_notification)}"
            showToast(activity, message)
        }else{
            list_items_rv.text = "Garbage Sorting list \n" + itemsDB.listItems()
        }

    }

    private fun showToast(activity: FragmentActivity, message: CharSequence) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}