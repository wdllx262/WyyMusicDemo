package com.bianfeng.wyymusicdemo.main.mvp.presenter;

import com.bianfeng.wyymusicdemo.main.mvp.contract.MineContract;

public class MinePresenter extends MineContract.Presenter {
    private static final String TAG = "PersonalPresenter";
    public MinePresenter(MineContract.View v)
    {
        this.mView=v;
    }

    @Override
    public void getUserPlaylist(long uid) {

    }

    @Override
    public void getIntelligenceList(long id, long pid) {

    }

    @Override
    public void getMvSublist() {

    }

    @Override
    public void getArtistSublist() {

    }

    @Override
    public void getAlbumSublist() {

    }

    @Override
    public void getMyFM() {

    }
}
