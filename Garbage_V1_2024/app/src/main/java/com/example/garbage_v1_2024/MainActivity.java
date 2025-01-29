package com.example.garbage_v1_2024;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ItemsDB itemsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.garbage);

        TextView header = findViewById(R.id.header_title);
        itemsDB= new ItemsDB();
        itemsDB.fillItemsDB();


        EditText text = (EditText)findViewById(R.id.edit);

        Button where = findViewById(R.id.where_button);

        TextView output = findViewById(R.id.result);

        where.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = text.getText().toString();
                String result = itemsDB.search(name);

                output.setText(itemsDB.search(name));
            }
        });
    }
}