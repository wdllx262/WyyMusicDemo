package com.bianfeng.wyymusicdemo.main.mvp.contract;


import com.bianfeng.wyymusicdemo.base.BaseModel;
import com.bianfeng.wyymusicdemo.base.BasePresenter;
import com.bianfeng.wyymusicdemo.base.BaseView;
import com.bianfeng.wyymusicdemo.bean.BannerBean;
import com.bianfeng.wyymusicdemo.bean.DailyRecommendBean;
import com.bianfeng.wyymusicdemo.bean.HighQualityPlayListBean;
import com.bianfeng.wyymusicdemo.bean.MainRecommendPlayListBean;
import com.bianfeng.wyymusicdemo.bean.PlaylistDetailBean;
import com.bianfeng.wyymusicdemo.bean.RecommendPlayListBean;
import com.bianfeng.wyymusicdemo.bean.TopListBean;
import com.bianfeng.wyymusicdemo.manager.bean.MusicCanPlayBean;

import io.reactivex.Observable;


public interface WowContract {
    interface View extends BaseView {
        void onGetBannerSuccess(BannerBean bean);

        void onGetBannerFail(String e);

        void onGetRecommendPlayListSuccess(MainRecommendPlayListBean bean);

        void onGetRecommendPlayListFail(String e);

        void onGetDailyRecommendSuccess(DailyRecommendBean bean);

        void onGetDailyRecommendFail(String e);

        void onGetTopListSuccess(TopListBean bean);

        void onGetTopListFail(String e);

        void onGetPlayListSuccess(RecommendPlayListBean bean);

        void onGetPlayListFail(String e);

        void onGetPlaylistDetailSuccess(PlaylistDetailBean bean);

        void onGetPlaylistDetailFail(String e);

        void onGetMusicCanPlaySuccess(MusicCanPlayBean bean);

        void onGetMusicCanPlayFail(String e);

        void onGetHighQualitySuccess(HighQualityPlayListBean bean);

        void onGetHighQualityFail(String e);
    }

    interface Model extends BaseModel {
        Observable<BannerBean> getBanner();

        Observable<MainRecommendPlayListBean> getRecommendPlayList();

        Observable<DailyRecommendBean> getDailyRecommend();

        Observable<TopListBean> getTopList();

        Observable<RecommendPlayListBean> getPlayList(String type, int limit);

        Observable<PlaylistDetailBean> getPlaylistDetail(long id);

        Observable<MusicCanPlayBean> getMusicCanPlay(long id);

        Observable<HighQualityPlayListBean> getHighQuality(int limit, long before);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getBanner();

        public abstract void getRecommendPlayList();

        public abstract void getDailyRecommend();

        public abstract void getTopList();

        public abstract void getPlayList(String type, int limit);

        public abstract void getPlaylistDetail(long id);

        public abstract void getMusicCanPlay(long id);

        public abstract void getHighQuality(int limit, long before);
    }
}
