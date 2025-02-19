package dk.itu.garbagev4_2025_kotlin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class AddItemActivity : AppCompatActivity() {

    private lateinit var itemsDB: ItemsDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_new_item)

        itemsDB = ItemsDB.getInstance(this)

        val itemAddedNotification: TextView = findViewById(R.id.notification_item_added)
        itemAddedNotification.visibility = View.INVISIBLE

        val addNewItemButton = findViewById<Button>(R.id.add_new_item_button)

        addNewItemButton.setOnClickListener {
            val userInputWhatToSort = findViewById<TextView>(R.id.add_new_item_text).text.toString()
            val userInputWhereToSort = findViewById<TextView>(R.id.add_where_to_sort_new_item_text).text.toString()

            itemsDB.addItem(userInputWhatToSort, userInputWhereToSort)
            itemAddedNotification.visibility = View.VISIBLE
            findViewById<TextView>(R.id.add_new_item_text).text = ""
            findViewById<TextView>(R.id.add_where_to_sort_new_item_text).text = ""
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(addNewItemButton.windowToken, 0)
        }

    }
}