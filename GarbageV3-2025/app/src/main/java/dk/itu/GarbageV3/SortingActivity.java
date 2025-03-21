package dk.itu.GarbageV3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class SortingActivity extends AppCompatActivity {

    //Shopping V1

    // Model: Database of items
    private static ItemsDB itemsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_item);

        ItemsDB.setContext(SortingActivity.this);

        itemsDB= ItemsDB.get();

        TextView items= findViewById(R.id.items);
        EditText sortInput= findViewById(R.id.find_item_to_sort);
        items.setText("Where to place item:");


        Button listItems= findViewById(R.id.list_button);
        listItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String itemWhat = sortInput.getText().toString();

                String itemWhere = itemsDB.findCategory(itemWhat);

                sortInput.setText(itemWhat + " should be placed in: " + itemWhere);

            }
        });
        sortInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortInput.setText("");
            }
        });

        Button addItem= findViewById(R.id.add_new_item_button);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SortingActivity.this, AddItemActivity.class);
                startActivity(intent);
            }
        });

    }
}