package dk.itu.garbagev5_2025_kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class SortingActivity : ComponentActivity() {

    private lateinit var itemsDB: ItemsDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.find_item_activity)
        itemsDB = ItemsDB.getInstance(this)

        val titleWhereToSortItems: TextView = findViewById(R.id.title_where_to_sort_items)
        val findWhereToSortItemsButton: Button = findViewById(R.id.find_item_button)
        val sortInput: EditText = findViewById<EditText?>(R.id.find_item_to_sort)
        val addItemButton: Button = findViewById(R.id.add_new_item_button)


        findWhereToSortItemsButton.setOnClickListener{
            val itemWhat = sortInput.text.toString()
            val itemWhere = itemsDB.getWhere(itemWhat)
            sortInput.setText("Item ${itemWhat} should be placed in: ${itemWhere} container")
        }

        sortInput.setOnClickListener{
            sortInput.setText("")
        }

        addItemButton.setOnClickListener{
            val intent = Intent(this, AddItemActivity::class.java)
            startActivity(intent)
        }

    }
}