package com.bianfeng.wyymusicdemo.main.mvp.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bianfeng.wyymusicdemo.base.BaseFragment;
import com.bianfeng.wyymusicdemo.bean.AlbumSublistBean;
import com.bianfeng.wyymusicdemo.bean.ArtistSublistBean;
import com.bianfeng.wyymusicdemo.bean.MvSublistBean;
import com.bianfeng.wyymusicdemo.bean.MyFmBean;
import com.bianfeng.wyymusicdemo.bean.PlayModeIntelligenceBean;
import com.bianfeng.wyymusicdemo.main.mvp.contract.MineContract;
import com.bianfeng.wyymusicdemo.main.mvp.view.presenter.MinePresenter;
import com.bianfeng.wyymusicdemo.persional.bean.UserPlaylistBean;

public class MyMvSubFragment   extends BaseFragment<MinePresenter> implements MineContract.View {
    private static final String TAG = "MyMvSubFragment";

    public MyMvSubFragment() {
        setFragmentTitle("视频");
    }


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    public MinePresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected void initVariables(Bundle bundle) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onGetUserPlaylistSuccess(UserPlaylistBean bean) {

    }

    @Override
    public void onGetUserPlaylistFail(String e) {

    }

    @Override
    public void onGetIntelligenceListSuccess(PlayModeIntelligenceBean bean) {


    }

    @Override
    public void onGetIntelligenceListFail(String e) {

    }

    @Override
    public void onGetMvSublistBeanSuccess(MvSublistBean bean) {

    }

    @Override
    public void onGetMvSublistBeanFail(String e) {

    }

    @Override
    public void onGetArtistSublistBeanSuccess(ArtistSublistBean bean) {

    }

    @Override
    public void onGetArtistSublistBeanFail(String e) {

    }

    @Override
    public void onGetAlbumSublistBeanSuccess(AlbumSublistBean bean) {

    }

    @Override
    public void onGetAlbumSublistBeanFail(String e) {

    }

    @Override
    public void onGetMyFMSuccess(MyFmBean bean) {

    }

    @Override
    public void onGetMyFMFail(String e) {

    }
}
