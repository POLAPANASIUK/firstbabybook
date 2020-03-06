package com.example.firstbabybook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.firstbabybook.MESSAGE";
    @ViewById protected TextView  txtName = findViewById(R.id.txtName);
    @ViewById protected TextView  txtLastName = findViewById(R.id.txtLastName);
    @ViewById protected TextView  txtDateBorn = findViewById(R.id.txtDateBorn);
    @ViewById protected TextView  txtPlaceBorn = findViewById(R.id.txtPlaceBorn);
    @ViewById protected TextView  txtWeight = findViewById(R.id.txtWeight);
    @ViewById protected TextView  txtSize = findViewById(R.id.txtSize);

    public void saveFirstData(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText txtName = findViewById(R.id.txtName);
        EditText txtLastName = findViewById(R.id.txtLastName);
        String message = txtName.getText().toString();
        message.concat(txtLastName.getText().toString());
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
