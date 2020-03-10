package com.example.firstbabybook;

import android.app.Application;

import com.example.firstbabybook.utils.ExceptionHandler;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(getApplicationContext()));
        super.onCreate();
    }
}
