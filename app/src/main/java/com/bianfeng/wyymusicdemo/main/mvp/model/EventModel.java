package com.bianfeng.wyymusicdemo.main.mvp.model;

import com.bianfeng.wyymusicdemo.bean.MainEventBean;
import com.bianfeng.wyymusicdemo.main.mvp.contract.EventContract;

import io.reactivex.Observable;

public class EventModel implements EventContract.Model {

    @Override
    public Observable<MainEventBean> getMainEvent() {
        return null;
    }

}