package dk.itu.garbagev6_2025_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class AddItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item_container)
        setUpFragments()
    }

    private fun setUpFragments(){
        val fm = supportFragmentManager

        var fragmentAddItem: Fragment? = fm.findFragmentById(R.id.fragment_add_new_container)
        var fragmentListItems: Fragment? = fm.findFragmentById(R.id.fragment_list_items_container)

        if (fragmentAddItem == null && fragmentListItems == null) {

            fragmentAddItem = AddItemFragment()
            fragmentListItems = ListItemsFragment()

            fm.beginTransaction()
                .add(R.id.fragment_add_new_container, fragmentAddItem)
                .add(R.id.fragment_list_items_container, fragmentListItems)
                .commit()
        }
    }
}