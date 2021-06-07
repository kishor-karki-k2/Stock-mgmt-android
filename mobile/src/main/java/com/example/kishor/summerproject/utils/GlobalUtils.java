package com.example.kishor.summerproject.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


import com.example.kishor.summerproject.R;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by Wongel on 5/9/17.
 */
public class GlobalUtils {
    private static int radioId = 0;

    public static void navigateActivity(Context mContext, Boolean finish, Class className) {
        mContext.startActivity(new Intent(mContext, className));
        if (finish)
            ((AppCompatActivity) mContext).finish();
    }

    public static void navigateActivity(Context mContext, Boolean finish, Class className, int enterAnim, int exitAnim) {
        mContext.startActivity(new Intent(mContext, className));
        ((AppCompatActivity) mContext).overridePendingTransition(enterAnim, exitAnim);
        if (finish)
            ((AppCompatActivity) mContext).finish();
    }

    public static void navigateActivitywithData(Context mContext, Boolean finish, Class className, String data, int enterAnim, int exitAnim) {
        Intent intent = new Intent(mContext, className);
        intent.putExtra("DATA", data);
        mContext.startActivity(intent);
        ((AppCompatActivity) mContext).overridePendingTransition(enterAnim, exitAnim);
        if (finish)
            ((AppCompatActivity) mContext).finish();
    }

    public static void navigateActivitywithData(Context mContext, Boolean finish, Class className, Map<String, Object> data, int enterAnim, int exitAnim) {
        Intent intent = new Intent(mContext, className);
        for (String key : data.keySet()) {
            Object obj = data.get(key);
            if (obj instanceof String)
                intent.putExtra(key, (String) data.get(key));
            else if (obj instanceof Boolean)
                intent.putExtra(key, (boolean) data.get(key));
            else if (obj instanceof Integer)
                intent.putExtra(key, (int) data.get(key));
            else if (obj instanceof Double)
                intent.putExtra(key, (double) data.get(key));
            else if (obj instanceof Float)
                intent.putExtra(key, (float) data.get(key));
            else if (obj instanceof Long)
                intent.putExtra(key, (long) data.get(key));
        }
        mContext.startActivity(intent);
        ((AppCompatActivity) mContext).overridePendingTransition(enterAnim, exitAnim);
        if (finish)
            ((AppCompatActivity) mContext).finish();
    }

    public static void addFragment(Context context, Fragment fragment, int id, boolean addToBackstack) {
        FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager()
                .beginTransaction()
                .replace(id, fragment);

        if (addToBackstack)
            transaction.addToBackStack(null);

        transaction.commit();
    }

    public static boolean isNetworkAvailable(Context mContext) {
        boolean result = false;
        try {
            ConnectivityManager connectivity = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        result = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    //pattern eg "###,###,###,###.##"
    public static float formatNumber(float format, String pattern) {
        Double longval = Double.valueOf(format);
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        formatter.applyPattern(pattern);
        float formattedString = Float.parseFloat(formatter.format(longval));
        return formattedString;
    }

    public static void setLanguage(Context context, String languageToLoad) {
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocale(locale);
        } else {
            config.locale = locale;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            context = context.createConfigurationContext(config);
        } else {
            context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
        }
    }

    public static void restartActivity(Context context) {
        Intent intent = ((AppCompatActivity) context).getIntent();
        ((AppCompatActivity) context).finish();
        context.startActivity(intent);
    }

    public static void savePref(String name, String value, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(context.getString(R.string.PREF_TOKEN), Context.MODE_PRIVATE).edit();
        editor.putString(name, value);
        editor.apply();
    }

    public static void savePrefBoolean(String name, Boolean value, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(context.getString(R.string.PREF_TOKEN), Context.MODE_PRIVATE).edit();
        editor.putBoolean(name, value);
        editor.apply();
    }

    public static String getFromPref(String name, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(context.getString(R.string.PREF_TOKEN), Context.MODE_PRIVATE);
        String value;
        value = prefs.getString(name, "");
        return value;
    }

    public static Boolean getFromPrefBoolean(String name, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(context.getString(R.string.PREF_TOKEN), Context.MODE_PRIVATE);
        return prefs.getBoolean(name, true);
    }

    public static void clearNumber(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.PREF_TOKEN), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }


    public static String parseDateToddMMyyyy(String time, Context context) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "MMMM dd, yyyy hh:mm aa";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        String str = null;
        Date dateTimezone = null;

        try {
            dateTimezone = inputFormat.parse(time);
            inputFormat.setTimeZone(TimeZone.getDefault());
            String formattedDate = inputFormat.format(dateTimezone);
            date = inputFormat.parse(formattedDate);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static Object getJsonData(Object obj) {
        if (obj == null)
            return JSONObject.NULL;
        return obj;
    }


    public static void setRadioId(int id) {
        radioId = id;
    }

    public static int getRadioId() {
        return radioId;
    }
}
