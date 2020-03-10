package com.example.firstbabybook;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

public class DialogTest  extends android.app.Dialog {

    public DialogTest(@NonNull Context context) {
        super(context);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_test);
    }
}
