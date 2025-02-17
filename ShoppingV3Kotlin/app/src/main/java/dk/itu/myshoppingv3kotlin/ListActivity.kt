package dk.itu.myshoppingv3kotlin

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ListActivity: AppCompatActivity() {
    private lateinit var itemsDB: ItemsDB
    private lateinit var listThings: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        itemsDB = ItemsDB.get()
        listThings = findViewById(R.id.listItems)
        listThings.setText("Shopping List" + itemsDB.listItems())
    }
}