package com.bianfeng.wyymusicdemo;

import android.app.Application;

import com.hjq.toast.ToastUtils;

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

        initDataBase();
    }

    private void initDataBase() {

    }

    public static App getContext() {
        return mContext;
    }


}
