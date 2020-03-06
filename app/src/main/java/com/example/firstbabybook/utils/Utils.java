package com.example.firstbabybook.utils;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.firstbabybook.BuildConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
/**
 * Adrian Cataldo adrian.cataldo093@gmail.com
 * Creado: 02-06-16
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class Utils {
    @Nullable
    private static Toast toast;
    //CONSTRUCTOR
    private Utils(){}
    public static void log(Object title, Object text, @Nullable Context ctx){
        if(text==null || title==null)return;
        saveLog(title.toString() + " - " + text.toString(), true);
    }
    public static void log(Object text, @Nullable Context ctx){
        if(text==null)return;
        saveLog(text.toString(), true);
    }
    public static void error(Object title, Object error, @Nullable Context ctx){
        try {
            String trace = "";
            String msg = "";
            if(error==null || title==null){return;}
            if(error instanceof Exception){
                StringWriter errors = new StringWriter();
                ((Exception)error).printStackTrace(new PrintWriter(errors));
                trace = "\n"+errors.toString();
                msg = ((Exception)error).getMessage();
                if(msg!=null) msg=":"+msg; else msg="";
            }
            String text = "ERROR"+msg+" ("+title.toString() + ") " + error.toString()+trace;
            //if(!BuildConfig.DEBUG_MODE) Crashlytics.log(text);
            println(text);
        }catch(Exception e){
            Log.println(Log.ASSERT, "error: errors log", e.toString());
        }
    }
    private static void saveLog(String text, boolean send){
        //if(send && !BuildConfig.DEBUG_MODE) Crashlytics.log(text);
        if(BuildConfig.DEBUG_MODE){
            println(text);
        }
    }
    private static void println(String text){
        int maxLogSize = 500;
        for(int i = 0; i <= text.length() / maxLogSize; i++) {
            int start = i * maxLogSize;
            int end = (i+1) * maxLogSize;
            end = end > text.length() ? text.length() : end;
            Log.println(Log.ASSERT, "LOG", text.substring(start, end));
        }
    }
    public static boolean tryParseBoolean(String bool){
        try {
            int intVal = tryParseInt(bool);
            return intVal > 0 ||
                    bool.trim().equalsIgnoreCase("TRUE") ||
                    bool.trim().equalsIgnoreCase("T") ||
                    bool.trim().equalsIgnoreCase("YES") ||
                    bool.trim().equalsIgnoreCase("Y");
        }catch (Exception ignored){}
        return false;
    }
    public static long tryParseLong(String number){
        long num = 0;
        try{num = Long.parseLong(number.trim());}catch(Exception ignored){}
        return num;
    }
    public static int tryParseInt(String number){
        int num = 0;
        try{num = Integer.parseInt(number.trim());}catch(Exception ignored){}
        return num;
    }
    public static float tryParseFloat(String number){
        float num = 0;
        try{num = Float.parseFloat(number.trim());}catch(Exception ignored){}
        return num;
    }
    public static double tryParseDouble(String number){
        double num = 0;
        try{num = Double.parseDouble(number.trim());}catch(Exception ignored){}
        return num;
    }
    public static int tryParseStringColorToInt(String color){
        try {
            if(!color.isEmpty()){
                color = color.trim();
                String strColor = color.startsWith("#") ? color : ("#"+color);
                return Color.parseColor(strColor);
            }
        }catch (Exception e){
            Log.println(Log.ERROR, "strColorToInt", color);
        }
        return 0;
    }
    public static boolean intToBoolean(int val){
        return val>0;
    }
    public static String notNull(@Nullable String st){
        return st==null ? "" : st;
    }
    public static Integer notNull(@Nullable Integer number){
        return number==null ? 0 : number;
    }
    public static Float notNull(@Nullable Float number){
        return number==null ? 0 : number;
    }
    public static boolean notNull(@Nullable Boolean bool){
        return bool==null ? false : bool;
    }
    public static boolean empty(@Nullable String st){
        return st==null || st.isEmpty();
    }
    /** Save preferences */
    @SuppressLint({"CommitPrefEdits", "ApplySharedPref"})
    public static void saveStringSP(String var, String value, Context context){
        try {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(var, value).commit();
        }catch(Exception ex){
            Utils.error("saveStringSP String", "" + ex, context);}
    }
    @SuppressLint({"CommitPrefEdits", "ApplySharedPref"})
    public static void saveIntSP(String var, int value, Context context){
        try {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(var, value).commit();
        }catch(Exception ex){
            Utils.error("saveStringSP int", "" + ex, context);}
    }
    @SuppressLint({"CommitPrefEdits", "ApplySharedPref"})
    public static void saveLongSP(String var, long value, Context context){
        try {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(var, value).commit();
        }catch(Exception ex){
            Utils.error("saveStringSP long", "" + ex, context);}
    }
    @SuppressLint({"CommitPrefEdits", "ApplySharedPref"})
    public static void saveFloatSP(String var, float value, Context context){
        try {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putFloat(var, value).commit();
        }catch(Exception ex){
            Utils.error("saveStringSP float", "" + ex, context);}
    }
    @SuppressLint({"CommitPrefEdits", "ApplySharedPref"})
    public static void saveBooleanSP(String var, boolean value, Context context){
        try {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(var, value).commit();
        }catch(Exception ex){
            Utils.error("saveStringSP boolean", "" + ex, context);}
    }
    public static void saveArrayLongSP(String var, List<Long> nums, Context context){
        if(nums!=null) {
            StringBuilder strSave = new StringBuilder();
            for (long n : nums) {
                strSave.append(strSave.toString().trim().isEmpty() ? "" : ",").append(n);}
            saveStringSP(var, strSave.toString(), context);
        }
    }
    public static void saveArrayStringSP(String var, List<String> strs, Context context){
        if(strs!=null) {
            StringBuilder strSave = new StringBuilder();
            for (String n : strs) {
                strSave.append(strSave.toString().trim().isEmpty() ? "" : ",").append(n);}
            saveStringSP(var, strSave.toString(), context);
        }
    }
    /** read preferences */
    public static String readStringSP(String var, @Nullable String defValue, Context context){
        try { return PreferenceManager.getDefaultSharedPreferences(context).getString(var, defValue);
        }catch(Exception ex){
            Utils.error("Utils.readStringSP("+var+")", ex, context);}
        return defValue;
    }
    public static int readIntSP(String var, int defValue, Context context){
        try {return PreferenceManager.getDefaultSharedPreferences(context).getInt(var, defValue);
        }catch(Exception ex){
            Utils.error("Utils.readIntSP("+var+")", ex, context);}
        return defValue;
    }
    public static long readLongSP(String var, long defValue, Context context){
        try {return PreferenceManager.getDefaultSharedPreferences(context).getLong(var, defValue);
        }catch(Exception ex){
            Utils.error("Utils.readLongSP("+var+")", ex, context);}
        return defValue;
    }
    public static float readFloatSP(String var, float defValue, Context context){
        try {return PreferenceManager.getDefaultSharedPreferences(context).getFloat(var, defValue);
        }catch(Exception ex){
            Utils.error("Utils.readFloatSP("+var+")", ex, context);}
        return defValue;
    }
    public static boolean readBooleanSP(String var, boolean defValue, Context context){
        try {return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(var, defValue);
        }catch(Exception ex){
            Utils.error("Utils.readBooleanSP("+var+")", ex, context);}
        return defValue;
    }
    @NonNull
    public static ArrayList<Long> readArrayLongSP(String var, Context context){
        ArrayList<Long> arr = new ArrayList<>();
        try {
            String str = Utils.readStringSP(var, "", context);
            if(!str.trim().isEmpty()){
                String[] strArr = str.split(",");
                for (String n : strArr) {
                    long num = Long.parseLong(n);
                    arr.add(num);
                }
            }
        }catch(Exception ignored){
        }
        return arr;
    }
    @NonNull
    public static ArrayList<String> readArrayStringSP(String var, Context context){
        try {
            String str = Utils.readStringSP(var, "", context);
            if(!str.trim().isEmpty()){
                return new ArrayList<>(Arrays.asList(str.split(",")));
            }
        }catch(Exception ignored){
        }
        return new ArrayList<>();
    }
    public static float dp(float px, Context ctx){
        return pxFromDp(px, ctx);
    }
    public static float pxFromDp(final float dp, final Context context) {
        return context==null ? 0 : dp * context.getResources().getDisplayMetrics().density;
    }
    public static float dpFromPx(final float px, final Context context) {
        return context==null ? 0 : px / context.getResources().getDisplayMetrics().density;
    }
    public static float sp(float px, Context ctx){
        return pxFromSp(px, ctx);
    }
    public static float pxFromSp(final float sp, final Context context) {
        return context==null ? 0 : sp * context.getResources().getDisplayMetrics().scaledDensity;
    }
    public static float spFromPx(final float px, final Context context) {
        return context==null ? 0 : px / context.getResources().getDisplayMetrics().scaledDensity;
    }
    @SuppressLint("SimpleDateFormat")
    public static String dateToString(@Nullable Date date){
        return dateToString(date, "yyyy-MM-dd HH:mm:ss");
    }
    @SuppressLint("SimpleDateFormat")
    public static String dateToString(@Nullable Date date, String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        String datetime = "";
        try{ datetime = dateFormat.format(date); }catch(Exception ignored){}
        return datetime;
    }
    @Nullable
    @SuppressLint("SimpleDateFormat")
    public static Date stringToDate(@Nullable String strDate, @Nullable Context ctx){
        if(strDate==null || strDate.trim().isEmpty())return null;
        Date dateTime = stringToDate(strDate, "yyyy-MM-dd HH:mm:ss", ctx);
        if(dateTime!=null)return dateTime;
        return stringToDate(strDate, "yyyy-MM-dd", ctx);
    }
    @Nullable
    @SuppressLint("SimpleDateFormat")
    public static Date stringToDate(@Nullable String strDate, @Nullable String format, @Nullable Context ctx){
        if(strDate==null || strDate.equalsIgnoreCase("null") || strDate.trim().isEmpty()){return null;}
        if(format==null || format.trim().isEmpty()){return null;}
        Date date = null;
        try {
            date = new SimpleDateFormat(format).parse(strDate);
        } catch (ParseException ignored) {
        }
        return date;
    }
    public static String dateToUserFormatString(@Nullable Date date, Context ctx){
        String datetime = "";
        if(date==null || ctx==null)return datetime;
        try {
            final String format = Settings.System.getString(ctx.getContentResolver(), Settings.System.DATE_FORMAT);
            DateFormat dateFormat;
            if (format == null || format.isEmpty()) {
                dateFormat = android.text.format.DateFormat.getMediumDateFormat(ctx);
            } else {
                dateFormat = new SimpleDateFormat(format, Locale.getDefault());
            }
            datetime = dateFormat.format(date);
            return datetime;
        }catch (Exception e){
            Utils.error("dateToUserFormatString", e, ctx);
        }
        return datetime;
    }
    public static void openUrl(String url, Context context){
        try{
            if(url==null || url.isEmpty())return;
            if(context==null)return;
            Utils.log("openUrl", url, context);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
        }catch (Exception e){
            Utils.error("open Url", url, context);
        }
    }
    //rellena con caracteres a la izquierda
    //Ej: lpad("7", "0", 2) -> "07"
    public static String lpad(Object text, String character, int length){
        StringBuilder out = new StringBuilder(text.toString());
        if(character.length()>0 && length>0) {
            while (out.length() < length) {
                out.insert(0, character);
            }
        }
        return out.toString();
    }
    /** Devuelve true si hay conexión a internet
     */
    public static boolean isNetworkAvailable(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm!=null){
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
        }
        return false;
    }
    public static void openMarket(Context context, String packageName){
        try {
            String marketURL = "market://details?id="+packageName;
            Uri uri = Uri.parse(marketURL);
            Intent goToMarket;
            goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
            } else {
                flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
            }
            goToMarket.addFlags(flags);
            Utils.log("startActivity", marketURL, context);
            context.startActivity(goToMarket);
        }catch (ActivityNotFoundException ignored) {
            String appURL = "https://play.google.com/store/apps/details?id="+packageName;
            Uri uri = Uri.parse(appURL);
            Utils.log("startActivity", appURL, context);
            context.startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }catch(Exception e){
            Utils.error("openMarket", e, context);
        }
    }
    public static void openMarket(Context context) {
        openMarket(context, context.getPackageName());
    }
    public static String getAppURL(Context context) {
        return "https://play.google.com/store/apps/details?id="+context.getPackageName();
    }
    /**
     * revisa si una cadena está en formato json válido
     */
    public static boolean isJSONValid(@Nullable String jsonString) {
        if(jsonString==null || jsonString.isEmpty())return false;
        try {
            new JSONObject(jsonString);
        } catch (JSONException ex) {
            try {
                new JSONArray(jsonString);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
    public static void customToast(final Context ctx, final String txt){
        if(txt==null || txt.isEmpty())return;
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            try{
                if(toast!=null){
                    toast.cancel();
                }else {
                    toast = new Toast(ctx);
                }
                toast = Toast.makeText(ctx, txt, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
                Utils.log("TOAST: "+txt, ctx);
            }catch (Exception e){
                Utils.error("toast", e, ctx);
            }
        });
    }
    @SuppressLint("ShowToast")
    public static void customToast(@Nullable Context ctx, int stringResource){
        String txt = "";
        if(ctx!=null && stringResource>0) {
            txt = ctx.getResources().getString(stringResource);
        }
        customToast(ctx, txt);
    }
    /**
     * x1: mdpi
     * x2: xhdpi
     * x3: xxhdpi
     * @return int scale (1, 2, or 3)
     */
    public static int getDeviceScale(Context ctx){
        float density = ctx.getResources().getDisplayMetrics().density;
        return (int)Math.min(Math.max(1, Math.ceil(density)), 3);
    }
}
