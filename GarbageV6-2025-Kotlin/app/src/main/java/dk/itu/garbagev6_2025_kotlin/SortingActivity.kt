package dk.itu.garbagev6_2025_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class SortingActivity : AppCompatActivity() {

    private lateinit var itemsDB: ItemsDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_item)
        itemsDB = ItemsDB.getInstance(this)
        setUpFragments()
    }

    private fun setUpFragments() {
        val fm = supportFragmentManager
        var fragmentFindItem: Fragment? = fm.findFragmentById(R.id.fragment_find_item_container)
        var fragmentListItems: Fragment? = fm.findFragmentById(R.id.fragment_list_items_container)
        if (fragmentFindItem == null && fragmentListItems == null) {

            fragmentFindItem = FindItemFragment()
            fragmentListItems = ListItemsFragment()

            fm.beginTransaction()
                .add(R.id.fragment_find_item_container, fragmentFindItem)
                .add(R.id.fragment_list_items_container, fragmentListItems)
                .commit()
        }
    }
}