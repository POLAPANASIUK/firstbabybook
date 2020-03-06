package com.example.firstbabybook;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.Click;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.firstbabybook.MESSAGE";
    @ViewById protected TextView  txtName;
    @ViewById protected TextView  txtLastName;
    @ViewById protected TextView  txtDateBorn;
    @ViewById protected TextView  txtPlaceBorn;
    @ViewById protected TextView  txtWeight;
    @ViewById protected TextView  txtSize;

    @Click(R.id.btnSave)
    public void saveFirstData(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        String message = this.txtName.getText().toString();
        message.concat(this.txtLastName.getText().toString());
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
