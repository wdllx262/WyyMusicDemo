package com.bianfeng.wyymusicdemo.main.mvp.view.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bianfeng.wyymusicdemo.R;
import com.bianfeng.wyymusicdemo.base.BaseFragment;
import com.bianfeng.wyymusicdemo.bean.AlbumSublistBean;
import com.bianfeng.wyymusicdemo.bean.ArtistSublistBean;
import com.bianfeng.wyymusicdemo.bean.MvSublistBean;
import com.bianfeng.wyymusicdemo.bean.MyFmBean;
import com.bianfeng.wyymusicdemo.bean.PlayModeIntelligenceBean;
import com.bianfeng.wyymusicdemo.main.mvp.contract.MineContract;
import com.bianfeng.wyymusicdemo.main.mvp.presenter.MinePresenter;
import com.bianfeng.wyymusicdemo.persional.bean.UserPlaylistBean;
import com.bianfeng.wyymusicdemo.util.ThemeUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lzx.musiclibrary.aidl.model.SongInfo;
import com.lzx.musiclibrary.manager.MusicManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineFragment extends BaseFragment<MinePresenter> implements MineContract.View {
    private static final String TAG = "MineFragment";
    int flag=0;
    @BindView(R.id.rv_mine_playlist)
    RecyclerView rvPlayList;
    @BindView(R.id.iv_gif_music)
    ImageView ivGif;
    public MineFragment() {
        setFragmentTitle("我的");
    }



    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mine, container, false);
       ButterKnife.bind(this, rootView);
        ThemeUtil.switchThemeType(getActivity());
        flag = 0;

        Glide.with(this).load(R.drawable.test).into(ivGif);

        return rootView;
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
    public void onClick(View v) { ;
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



    @Override
    public void onPause() {
        super.onPause();
        if (flag==0) {
            ThemeUtil.switchThemeType(getActivity());
            flag=1;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (flag==0) {
            ThemeUtil.switchThemeType(getActivity());
            flag = 1;
        }
    }
}
