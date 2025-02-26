package dk.itu.garbagev5_2025_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

class listItemsFragment: Fragment(), Observer<Any> {

    private lateinit var itemsDB: ItemsDB
    private lateinit var listThings: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemsDB = ItemsDB.getInstance(requireContext())
        itemsDB.addObserver(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_list_items, container, false)

        listThings = v.findViewById(R.id.list_all_items)

        listThings.text = "Garbage Sorting list \n" + itemsDB.listItems()

        return v
    }

    override fun onChanged(value: Any) {
        listThings.text = "Garbage Sorting list \n" + itemsDB.listItems()
    }
}