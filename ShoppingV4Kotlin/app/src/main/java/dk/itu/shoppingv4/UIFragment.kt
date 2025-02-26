package dk.itu.shoppingv4

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class UIFragment: Fragment() {
    private lateinit var itemsDB: ItemsDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemsDB = ItemsDB.get()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val v= inflater.inflate(R.layout.fragment_ui,container,false)

        //Text Fields
        val itemWhat: TextView = v.findViewById(R.id.what_text)
        val itemWhere: TextView = v.findViewById(R.id.where_text)

        //Buttons
        val addItem: Button = v.findViewById(R.id.add_button)
        // adding a new thing
        addItem.setOnClickListener {
            val whatS = itemWhat.text.toString().trim()
            val whereS = itemWhere.text.toString().trim()
            if (whatS.length > 0 && whereS.length > 0) {
                itemsDB.addItem(whatS, whereS)
                itemWhat.text = ""
                itemWhere.text = ""
                itemWhat.onEditorAction(EditorInfo.IME_ACTION_DONE); //to close the keyboard when done with the text
                itemWhere.onEditorAction(EditorInfo.IME_ACTION_DONE);
            } else Toast.makeText(activity, R.string.empty_toast, Toast.LENGTH_LONG).show()
        }

        val findItems: Button = v.findViewById<Button>(R.id.find_button)
        findItems.setOnClickListener {
            val what = itemWhat.text.toString().trim()
            itemWhat.setBackgroundColor(Color.parseColor("#FFFFFF"))
            itemWhere.text = itemsDB.getWhere(what)
            itemWhat.onEditorAction(EditorInfo.IME_ACTION_DONE); //to close the keyboard when done with the text
            itemWhere.onEditorAction(EditorInfo.IME_ACTION_DONE);
        }

        return v
    }
}