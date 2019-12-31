package com.bianfeng.wyymusicdemo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


public class SharedPreferencesUtils {

    private String wyyTheme="wyyTheme";//用于保存theme

    private volatile static SharedPreferencesUtils utils;
    private SharedPreferencesUtils(Context context){
        this.context=context;
        sp=context.getSharedPreferences(wyyTheme, Context.MODE_PRIVATE);
    }

    public static SharedPreferencesUtils getInstance(Context context){
        if (utils==null){
            synchronized (SharedPreferencesUtils.class){
                if (utils==null){
                    utils=new SharedPreferencesUtils(context);
                }
            }
        }
        return utils;
    }
    private Context context;
    private SharedPreferences sp;
    public void init(Context context){
        this.context=context;
        sp=context.getSharedPreferences(wyyTheme, Context.MODE_PRIVATE);
    }

    public void put(String key, String value){
        try {
            SharedPreferences.Editor editor=sp.edit();
            editor.putString(key,value);
            editor.commit();
        }catch (Exception e){
            Log.i("misdk","存储数据的报异常了");
        }
    }

    public void put(String key, Boolean value){
        try {
            SharedPreferences.Editor editor=sp.edit();
            editor.putBoolean(key,value);
            editor.commit();
        }catch (Exception e){
            Log.i("misdk","存储数据的报异常了");
        }
    }

    public boolean read(String key)
    {
        boolean miSession;
        miSession=sp.getBoolean(key, true);
        return miSession;
    }

    public void clear()
    {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

}
