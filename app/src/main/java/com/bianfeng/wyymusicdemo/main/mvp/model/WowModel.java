package com.bianfeng.wyymusicdemo.main.mvp.model;

import com.bianfeng.wyymusicdemo.bean.BannerBean;
import com.bianfeng.wyymusicdemo.bean.DailyRecommendBean;
import com.bianfeng.wyymusicdemo.bean.HighQualityPlayListBean;
import com.bianfeng.wyymusicdemo.bean.MainRecommendPlayListBean;
import com.bianfeng.wyymusicdemo.bean.PlaylistDetailBean;
import com.bianfeng.wyymusicdemo.bean.RecommendPlayListBean;
import com.bianfeng.wyymusicdemo.bean.TopListBean;
import com.bianfeng.wyymusicdemo.main.mvp.contract.WowContract;
import com.bianfeng.wyymusicdemo.manager.bean.MusicCanPlayBean;

import io.reactivex.Observable;

public class WowModel implements WowContract.Model {
    @Override
    public Observable<BannerBean> getBanner() {
        return null;
    }

    @Override
    public Observable<MainRecommendPlayListBean> getRecommendPlayList() {
        return null;
}

    @Override
    public Observable<DailyRecommendBean> getDailyRecommend() {
        return null;
    }

    @Override
    public Observable<TopListBean> getTopList() {
        return null;
    }

    @Override
    public Observable<RecommendPlayListBean> getPlayList(String type, int limit) {
        return null;
    }

    @Override
    public Observable<PlaylistDetailBean> getPlaylistDetail(long id) {
        return null;
    }

    @Override
    public Observable<MusicCanPlayBean> getMusicCanPlay(long id) {
        return null;
    }

    @Override
    public Observable<HighQualityPlayListBean> getHighQuality(int limit, long before) {
        return null;
    }
}
