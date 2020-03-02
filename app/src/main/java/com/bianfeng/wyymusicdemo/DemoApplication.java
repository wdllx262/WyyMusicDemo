package com.bianfeng.wyymusicdemo;

import android.app.Application;
import android.util.Log;

import com.lzx.musiclibrary.manager.MusicManager;
import com.lzx.musiclibrary.utils.BaseUtil;

/**
 * Application初始化时，初始化一下PPS SDK的日志，初始化日志不会有网络操作和采集用户信息。
 */
public class DemoApplication extends Application
{
    private static final String TAG = "DemoApplication";

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.e(TAG, "onCreate application");

        if (!BaseUtil.getCurProcessName(this).contains(":musicLibrary")) {
            MusicManager.get().setContext(this).init();
        }
    }
}
