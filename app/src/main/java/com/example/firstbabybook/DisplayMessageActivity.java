package com.example.firstbabybook;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

@EActivity(R.layout.activity_display_message)
public class DisplayMessageActivity extends AppCompatActivity {
    @Extra(MainActivity.EXTRA_MESSAGE)
    protected String message;
    @AfterViews
    protected void init() {
        Log.println(Log.ASSERT, "LOG", "message:"+message);
    }
}