package com.bianfeng.wyymusicdemo.main.mvp.view.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.lzx.musiclibrary.aidl.listener.OnPlayerEventListener;
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
        //playOneSong();
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
    public void playOneSong()
    {
        //播放一首歌曲
        List<SongInfo> songInfos = new ArrayList<>();
        SongInfo songInfo = new SongInfo();
        songInfo.setSongId("0");
        songInfo.setSongName("音乐1");
        songInfo.setSongUrl("http://audio04.dmhmusic.com/71_53_T10052305999_128_4_1_0_sdk-cpm/cn/0311/M00/C5/E1/ChAKDF2paMGAbb9FADw1Kf2Dc7Y029.mp3?xcode=57de1e849f8a78065f4dde57e38046bf02e26ac");
        songInfo.setArtist("11");
        songInfo.setDuration(10000);
        songInfos.add(songInfo);
        SongInfo songInfo1 = new SongInfo();
        songInfo1.setSongId("1");
        songInfo1.setSongName("音乐1");
        songInfo1.setSongUrl("http://audio04.dmhmusic.com/71_53_T10052305999_128_4_1_0_sdk-cpm/cn/0311/M00/C5/E1/ChAKDF2paMGAbb9FADw1Kf2Dc7Y029.mp3?xcode=57de1e849f8a78065f4dde57e38046bf02e26ac");
        songInfo1.setArtist("11");
        songInfo1.setDuration(10000);
        songInfos.add(songInfo1);
        Log.e("11111111","播放一首歌曲");
        MusicManager.get().playMusic(songInfos, 0, true);
    }
}
