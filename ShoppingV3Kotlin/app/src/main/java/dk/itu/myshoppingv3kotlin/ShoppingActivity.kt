package dk.itu.myshoppingv3kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class ShoppingActivity : AppCompatActivity() {
    // Model: Database of items
    private lateinit var itemsDB: ItemsDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shopping)

        itemsDB = ItemsDB.get()

        //Text Fields
        val newWhat: TextView = findViewById(R.id.what_text)
        val newWhere: TextView = findViewById(R.id.where_text)

        val listItems: Button = findViewById(R.id.list)
        listItems.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

        val addItem: Button = findViewById(R.id.add_button)
        addItem.setOnClickListener {
            val whatS = newWhat.text.toString().trim()
            val whereS = newWhere.text.toString().trim()
            if ((whatS.length > 0) && (whereS.length > 0)) {
                itemsDB.addItem(whatS, whereS)
                newWhat.text = ""
                newWhere.text = ""
            } else {
                Toast.makeText(this, R.string.empty_toast, Toast.LENGTH_LONG).show();
            }
        }
    }
}