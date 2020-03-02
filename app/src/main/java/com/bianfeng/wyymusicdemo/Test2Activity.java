package com.bianfeng.wyymusicdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lzx.musiclibrary.aidl.model.SongInfo;
import com.lzx.musiclibrary.manager.MusicManager;

public class Test2Activity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SongInfo msongInfo = new SongInfo();
        msongInfo.setSongId("111");
        msongInfo.setSongUrl("file:///android_asset/music.mp3");
        MusicManager.get().playMusicByInfo(msongInfo);
    }
}
