package com.bianfeng.wyymusicdemo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.lzx.musiclibrary.aidl.model.SongInfo;

import java.util.List;


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

    public void putSongInfo(SongInfo songInfo,long process,int index)
    {
        try {
            SharedPreferences.Editor editor=sp.edit();
            editor.putString("SongId",songInfo.getSongId());
            editor.putString("SongName",songInfo.getSongName());
            editor.putString("SongUrl",songInfo.getSongUrl());
            editor.putString("Artist",songInfo.getArtist());
            editor.putString("Duration",String.valueOf(songInfo.getDuration()));
            editor.putLong("Process",process);
            editor.putInt("Index",index);
            editor.commit();
        }catch (Exception e){
            Log.i("misdk","存储数据的报异常了");
        }
    }

    public SongInfo readSongInfo()
    {
        SongInfo songInfo=new SongInfo();
        songInfo.setSongId(sp.getString("SongId","0"));
        songInfo.setSongName(sp.getString("SongName","0"));
        songInfo.setSongUrl(sp.getString("SongUrl","0"));
        songInfo.setArtist(sp.getString("Artist","0"));
        songInfo.setDuration(Integer.valueOf(sp.getString("Duration","0")));
        return songInfo;
    }

    public long readProcess()
    {
        return sp.getLong("Process",0);
    }

    public int readIndex()
    {
        return sp.getInt("Index",0);
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
