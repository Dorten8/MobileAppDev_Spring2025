package dk.itu.garbagev5_2025_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class findItemFragment: Fragment() {
    private lateinit var itemsDB: ItemsDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemsDB = ItemsDB.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val v=inflater.inflate(R.layout.fragment_find_item, container, false)

        val findWhereToSortItemsButton: Button = v.findViewById(R.id.find_item_button)
        val sortInput: EditText = v.findViewById<EditText?>(R.id.find_item_to_sort)
//        val addItemButton: Button = v.findViewById(R.id.add_new_item_button)


        findWhereToSortItemsButton.setOnClickListener{
            val itemWhat = sortInput.text.toString()
            val itemWhere = itemsDB.getWhere(itemWhat)
            sortInput.setText("Item ${itemWhat} should be placed in: ${itemWhere} container")
        }

        sortInput.setOnClickListener{
            sortInput.setText("")
        }

//        addItemButton.setOnClickListener{
//            val intent = Intent(this, AddItemActivity::class.java)
//            startActivity(intent)
//        }

        return v
    }
}