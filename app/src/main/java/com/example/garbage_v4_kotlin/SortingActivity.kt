package com.example.garbage_v4_kotlin

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
        itemsDB = ItemsDB.get(this)

        val titleWhereToSortItems: TextView = findViewById(R.id.title_where_to_sort_items)
        val findWhereToSortItemsButton: Button = findViewById(R.id.find_item_button)
        val sortInput: EditText = findViewById<EditText?>(R.id.find_item_to_sort)


        findWhereToSortItemsButton.setOnClickListener{
            val itemWhat = sortInput.text.toString()
            val itemWhere = itemsDB.getWhere(itemWhat)
            sortInput.setText("Item ${itemWhat} should be placed in: ${itemWhere} container")
        }

    }






}
