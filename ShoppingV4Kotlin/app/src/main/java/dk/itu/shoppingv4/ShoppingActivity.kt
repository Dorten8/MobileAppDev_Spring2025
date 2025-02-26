package dk.itu.shoppingv4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class ShoppingActivity : AppCompatActivity() {
    //Shopping V4 using Fragments and landscape orientation

    //Model: Database of items
    private var itemsDB: ItemsDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shopping)
        itemsDB = ItemsDB.get()
        setUpFragments()
    }

    private fun setUpFragments() {
        val fm = supportFragmentManager
        var fragmentUI: Fragment? = fm.findFragmentById(R.id.container_ui)
        var fragmentList : Fragment? = fm.findFragmentById(R.id.container_list)
        if (fragmentUI == null && fragmentList == null) {

            fragmentUI = UIFragment()
            fragmentList = ListFragment()

            fm.beginTransaction()
                .add(R.id.container_ui, fragmentUI)
                .add(R.id.container_list, fragmentList)
                .commit()
        }
    }
}