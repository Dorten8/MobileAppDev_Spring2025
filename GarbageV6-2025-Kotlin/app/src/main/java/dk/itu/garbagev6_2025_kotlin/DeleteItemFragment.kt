package dk.itu.garbagev6_2025_kotlin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class DeleteItemFragment: Fragment() {

    private lateinit var itemsDB: ItemsDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        itemsDB = ItemsDB.getInstance(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle? ): View? {


        super.onCreateView(inflater, container, savedInstanceState)

        val v = inflater.inflate(R.layout.fragment_delete_item, container, false)


        val deleteItemButton = v.findViewById<Button>(R.id.delete_item_button)

        deleteItemButton.setOnClickListener{
            val userInputWhatToDelete = v.findViewById<EditText>(R.id.enter_what_item_to_delete_input)

            val what = userInputWhatToDelete.text.toString().trim { it <= ' '}
            val message = if (itemsDB.isPresent(what)){
                itemsDB.removeItem(what)
                "Removed $what"
            } else {
                "$what not found"
            }
            showToast(requireContext(), message)

        }

        return v
    }

    private fun showToast(activity: Context, message: CharSequence) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }



}