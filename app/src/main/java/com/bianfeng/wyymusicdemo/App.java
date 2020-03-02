package com.bianfeng.wyymusicdemo;

import android.app.Application;

import com.hjq.toast.ToastUtils;
import com.lzx.musiclibrary.manager.MusicManager;
import com.lzx.musiclibrary.utils.BaseUtil;

import org.greenrobot.greendao.database.Database;

public class App extends Application {
    private static final String TAG = "App";
    public static final String DATA_BASE_NAME = "RikkaMusicDao";

    private static App mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        ToastUtils.init(this);
        if (!BaseUtil.getCurProcessName(this).contains(":musicLibrary")) {
            MusicManager.get().setContext(this).init();
        }
//        MusicManager.initMusicManager(this);
//        if (BaseUtil.getCurProcessName(this).contains("com.bianfeng.wyymusicdemo")) {
//            NotificationCreater creater = new NotificationCreater.Builder()
//                    .setTargetClass("com.bianfeng.wyymusicdemo.main.mvp.MainActivity")
//                    .setCreateSystemNotification(true)
//                    .setNotificationCanClearBySystemBtn(true)
//                    .setSystemNotificationShowTime(true)
//                    .setPendingIntentMode(PendingIntentMode.MODE_ACTIVITY)
//                    .build();
//
////边播边存配置
//            CacheConfig cacheConfig = new CacheConfig.Builder()
//                    .setOpenCacheWhenPlaying(true)
//                    .setCachePath(CacheUtils.getStorageDirectoryPath() + "/NiceMusic/Cache/")
//                    .build();
//
//            MusicLibrary musicLibrary = new MusicLibrary.Builder(this)
//                    .setNotificationCreater(creater)
//                    .setCacheConfig(cacheConfig)
//                    .setAutoPlayNext(true)
//                    .build();
//            musicLibrary.startMusicService();
//        }
        initDataBase();
    }

    private void initDataBase() {

    }

    public static App getContext() {
        return mContext;
    }


}
