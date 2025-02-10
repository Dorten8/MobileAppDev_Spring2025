package dk.itu.GarbageV3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddItemActivity extends AppCompatActivity {
    private static ItemsDB itemsDB;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
        itemsDB= ItemsDB.get();

        TextView itemAddedNotification= findViewById(R.id.item_was_added_notification);
        itemAddedNotification.setVisibility(TextView.INVISIBLE);

        EditText what= findViewById(R.id.add_new_item_what_edit_text);
        EditText where= findViewById(R.id.add_new_item_where_edit_text);
        Button addItem= findViewById(R.id.add_new_item_button);

        addItem.setOnClickListener(view -> {
            itemsDB.addItem(what.getText().toString().trim(), where.getText().toString().trim());
            itemAddedNotification.setVisibility(TextView.VISIBLE);
            what.setText("");
            where.setText("");
        });

    }
}
