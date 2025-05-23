package dk.itu.garbagev5_2025_kotlin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class AddItemActivity : AppCompatActivity() {

    private lateinit var itemsDB: ItemsDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_item)
        itemsDB = ItemsDB.getInstance(this)
        setUpFragments()
    }

    private fun setUpFragments(){
        val fm = supportFragmentManager
        var fragmentAddItem: Fragment? = fm.findFragmentById(R.id.fragment_add_new_container)
        var fragmentListItems: Fragment? = fm.findFragmentById(R.id.fragment_list_items_container)
        if (fragmentAddItem == null && fragmentListItems == null) {

            fragmentAddItem = AddNewFragment()
            fragmentListItems = listItemsFragment()

            fm.beginTransaction()
                .add(R.id.fragment_add_new_container, fragmentAddItem)
                .add(R.id.fragment_list_items_container, fragmentListItems)
                .commit()
        }
    }
}