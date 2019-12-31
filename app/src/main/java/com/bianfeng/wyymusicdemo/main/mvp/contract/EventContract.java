package com.bianfeng.wyymusicdemo.main.mvp.contract;

import com.bianfeng.wyymusicdemo.base.BaseModel;
import com.bianfeng.wyymusicdemo.base.BasePresenter;
import com.bianfeng.wyymusicdemo.base.BaseView;
import com.bianfeng.wyymusicdemo.bean.MainEventBean;

import io.reactivex.Observable;

public interface EventContract {
    interface View extends BaseView {
        void onGetMainEventSuccess(MainEventBean bean);

        void onGetMainEventFail(String e);

    }

    interface Model extends BaseModel {
        Observable<MainEventBean> getMainEvent();

    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getMainEvent();

    }
}
