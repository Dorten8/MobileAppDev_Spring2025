package dk.itu.garbagev6_2025_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class DeleteItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_item_container)
        setUpFragments()
    }

    private fun setUpFragments(){
        val fm = supportFragmentManager

        var fragmentDeleteItem: Fragment? = fm.findFragmentById(R.id.fragment_delete_item_container)
        var fragmentListItems: Fragment? = fm.findFragmentById(R.id.fragment_list_items_container)

        if (fragmentDeleteItem == null && fragmentListItems == null) {

            fragmentDeleteItem = DeleteItemFragment()
            fragmentListItems = ListItemsFragment()

            fm.beginTransaction()
                .add(R.id.fragment_delete_item_container, fragmentDeleteItem)
                .add(R.id.fragment_list_items_container, fragmentListItems)
                .commit()
        }
    }
}