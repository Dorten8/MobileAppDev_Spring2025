package dk.itu.garbagev6_2025_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class AddItemFragment: Fragment() {

    private lateinit var itemsDB: ItemsDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        itemsDB = ItemsDB.getInstance(requireContext())
    }



    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        super.onCreateView(inflater, container, savedInstanceState)

        val v = inflater.inflate(R.layout.fragment_add_item, container, false)

        val itemAddedNotification: TextView = v.findViewById(R.id.add_item_notification)
        itemAddedNotification.visibility = View.INVISIBLE

        val addNewItemButton = v.findViewById<Button>(R.id.add_item_bt)

        addNewItemButton.setOnClickListener {
            val userInputWhatToSort = v.findViewById<TextView>(R.id.add_item_what_et).text.toString()
            val userInputWhereToSort = v.findViewById<TextView>(R.id.add_item_where_et).text.toString()



            if(userInputWhatToSort == "" || userInputWhereToSort == ""){
                itemAddedNotification.text = getString(R.string.item_was_not_added_notification)
                itemAddedNotification.visibility = View.VISIBLE

            }
            else{
                itemsDB.addItem(userInputWhatToSort, userInputWhereToSort)
                itemAddedNotification.text = "Item was added!"
                itemAddedNotification.visibility = View.VISIBLE
                v.findViewById<TextView>(R.id.add_item_what_et).text = ""
                v.findViewById<TextView>(R.id.add_item_where_et).text = ""
                val inputMethodManager = requireContext().getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(addNewItemButton.windowToken, 0)
            }


        }
        return v

    }





}