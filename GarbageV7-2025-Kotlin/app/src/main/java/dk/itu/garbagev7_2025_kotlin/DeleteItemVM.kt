package dk.itu.garbagev7_2025_kotlin

import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel

class DeleteItemVM : ViewModel(){

    private lateinit var itemsDB: ItemsDB

    // Update this method to accept a String
    fun onDeleteItemButtonClick(what: String, activity: FragmentActivity) {
        itemsDB = ItemsDB.getInstance(activity.applicationContext)

        val message = if (itemsDB.isPresent(what)) {
            itemsDB.removeItem(what)
            "${activity.getString(R.string.delete_item_notification)}"
        } else if (what.isEmpty()) {
            "${activity.getString(R.string.empty_toast)} in both fields"
        } else {
            "$what not found"
        }

        showToast(activity, message)
    }

    private fun showToast(activity: FragmentActivity, message: CharSequence) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}