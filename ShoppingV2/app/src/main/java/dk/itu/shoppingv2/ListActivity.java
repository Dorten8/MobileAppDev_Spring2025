package dk.itu.shoppingv2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ListActivity extends AppCompatActivity {
    private static ItemsDB itemsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        itemsDB= ItemsDB.get();
        TextView listThings= findViewById(R.id.listItems);
        listThings.setText("Shopping List"+itemsDB.listItems());
    }
}
