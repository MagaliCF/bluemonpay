package com.example.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.models.Login;
import com.example.myapplication.models.ProductsItem;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Utils {

    public static void setToken(Context context, String key, String token){
        SharedPreferences sp = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key,token);
        editor.apply();
    }

    public static String getToken(Context context, String key){
        SharedPreferences sp = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        String token = sp.getString(key, "");

        return token;
    }

    public static void setHora(Context context, String key, float hora){
        SharedPreferences sp = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key,hora);
        editor.apply();
    }

    public static float getHora(Context context, String key){
        SharedPreferences sp = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        float hora = sp.getFloat(key,0);

        return hora;
    }
}
