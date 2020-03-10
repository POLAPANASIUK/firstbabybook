package com.example.firstbabybook.utils;

import android.content.Context;
import android.util.Log;
import com.example.firstbabybook.utils.Utils;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    private Context ctx;
    public ExceptionHandler(Context ctx){
        this.ctx = ctx;
    }
    public void uncaughtException(Thread thread, Throwable exception) {
        Utils.error(">>>>>>>>>>>>>>>>> Se produjo un error! <<<<<<<<<<<<<<<<<<:\n", exception, ctx);
        ctx = null;
        exit(10);
    }
    private static void exit(int status){
        try {
            android.os.Process.killProcess(android.os.Process.myPid());
        }catch(Exception ignored){
            Log.println(Log.ASSERT, "error", "ExceptionHandler.close()");
        }
        System.exit(status);
    }
}