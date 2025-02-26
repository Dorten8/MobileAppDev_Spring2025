package dk.itu.garbagev5_2025_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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

        val v = inflater.inflate(R.layout.fragment_find_item, container, false)

        val findWhereToSortItemsButton: Button = v.findViewById(R.id.find_item_button)
        val addNewItemButton: Button = v.findViewById(R.id.add_new_item_button)
        val sortInput: EditText = v.findViewById<EditText?>(R.id.find_item_to_sort)

        findWhereToSortItemsButton.setOnClickListener{
            if(sortInput.text.toString().trim().isNotEmpty()){
                val itemWhat = sortInput.text.toString().trim()
                val itemWhere = itemsDB.getWhere(itemWhat)
                sortInput.setText("Item ${itemWhat} should be placed in: ${itemWhere} container")
            }else{
                Toast.makeText(requireContext(), R.string.empty_toast, Toast.LENGTH_LONG).show()
            }
        }

        addNewItemButton.setOnClickListener{
            val intent = Intent(requireContext(), AddItemActivity::class.java)
            startActivity(intent)
        }

        sortInput.setOnClickListener{
            sortInput.text.clear()
        }

        return v
    }
}