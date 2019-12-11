package com.bianfeng.wyymusicdemo.main.mvp.contract;

import com.bianfeng.wyymusicdemo.base.BaseModel;
import com.bianfeng.wyymusicdemo.base.BasePresenter;
import com.bianfeng.wyymusicdemo.base.BaseView;
import com.bianfeng.wyymusicdemo.bean.AlbumSublistBean;
import com.bianfeng.wyymusicdemo.bean.ArtistSublistBean;
import com.bianfeng.wyymusicdemo.bean.MvSublistBean;
import com.bianfeng.wyymusicdemo.bean.MyFmBean;
import com.bianfeng.wyymusicdemo.bean.PlayModeIntelligenceBean;
import com.bianfeng.wyymusicdemo.persional.bean.UserPlaylistBean;

import io.reactivex.Observable;

public interface MineContract {
    interface View extends BaseView {
        void onGetUserPlaylistSuccess(UserPlaylistBean bean);

        void onGetUserPlaylistFail(String e);

        void onGetIntelligenceListSuccess(PlayModeIntelligenceBean bean);

        void onGetIntelligenceListFail(String e);

        void onGetMvSublistBeanSuccess(MvSublistBean bean);

        void onGetMvSublistBeanFail(String e);

        void onGetArtistSublistBeanSuccess(ArtistSublistBean bean);

        void onGetArtistSublistBeanFail(String e);

        void onGetAlbumSublistBeanSuccess(AlbumSublistBean bean);

        void onGetAlbumSublistBeanFail(String e);

        void onGetMyFMSuccess(MyFmBean bean);

        void onGetMyFMFail(String e);
    }

    interface Model extends BaseModel {
        Observable<UserPlaylistBean> getUserPlaylist(long uid);

        Observable<PlayModeIntelligenceBean> getIntelligenceList(long id, long pid);

        Observable<MvSublistBean> getMvSublist();

        Observable<ArtistSublistBean> getArtistSublist();

        Observable<AlbumSublistBean> getAlbumSublistBean();

        Observable<MyFmBean> getMyFM();
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getUserPlaylist(long uid);

        public abstract void getIntelligenceList(long id, long pid);

        public abstract void getMvSublist();

        public abstract void getArtistSublist();

        public abstract void getAlbumSublist();

        public abstract void getMyFM();
    }
}
