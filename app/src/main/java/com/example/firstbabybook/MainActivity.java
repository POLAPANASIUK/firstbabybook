package com.example.firstbabybook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.polaapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtName = findViewById(R.id.name);
        TextView txtLastName = findViewById(R.id.lastname);
        TextView txtDate_born = findViewById(R.id.date_born);
        TextView txtPlace_born = findViewById(R.id.place_born);
        TextView txtweight = findViewById(R.id.weight);
        TextView txtSize = findViewById(R.id.size);
        setContentView(R.layout.activity_main);
    }
    public void saveFirstData(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText txtName = findViewById(R.id.name);
        String message = txtName.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
