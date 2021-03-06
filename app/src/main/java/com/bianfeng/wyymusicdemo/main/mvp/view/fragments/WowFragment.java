package com.bianfeng.wyymusicdemo.main.mvp.view.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bianfeng.wyymusicdemo.App;
import com.bianfeng.wyymusicdemo.R;
import com.bianfeng.wyymusicdemo.base.BaseFragment;

import com.bianfeng.wyymusicdemo.bean.BannerBean;
import com.bianfeng.wyymusicdemo.bean.DailyRecommendBean;
import com.bianfeng.wyymusicdemo.bean.HighQualityPlayListBean;
import com.bianfeng.wyymusicdemo.bean.MainRecommendPlayListBean;
import com.bianfeng.wyymusicdemo.bean.PlaylistBean;
import com.bianfeng.wyymusicdemo.bean.PlaylistDetailBean;
import com.bianfeng.wyymusicdemo.bean.RecommendPlayListBean;
import com.bianfeng.wyymusicdemo.bean.TopListBean;
import com.bianfeng.wyymusicdemo.main.mvp.contract.WowContract;
import com.bianfeng.wyymusicdemo.main.mvp.presenter.WowPresenter;
import com.bianfeng.wyymusicdemo.manager.bean.MusicCanPlayBean;
import com.bianfeng.wyymusicdemo.util.BannerGlideImageLoader;
import com.bianfeng.wyymusicdemo.util.ClickUtil;
import com.bianfeng.wyymusicdemo.util.LogUtil;
import com.hjq.toast.ToastUtils;
import com.lzx.musiclibrary.aidl.model.SongInfo;
import com.lzx.musiclibrary.manager.MusicManager;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WowFragment   extends BaseFragment<WowPresenter> implements WowContract.View{
    private static final String TAG = "WowFragment";

    public static final String PLAYLIST_NAME = "playlistName";
    public static final String PLAYLIST_PICURL = "playlistPicUrl";
    public static final String PLAYLIST_CREATOR_NICKNAME = "playlistCreatorNickname";
    public static final String PLAYLIST_CREATOR_AVATARURL = "playlistCreatorAvatarUrl";
    public static final String PLAYLIST_ID = "playlistId";
    private int[] imageResIds;

    @BindView(R.id.wow_banner)
    Banner banner;
    @BindView(R.id.rv_recommend_playlist)
    RecyclerView rvRecommendPlayList;

//    private PlayListAdapter recommendPlayListAdapter;
    //banner的图片集合
    List<String> bannerImageList = new ArrayList<>();
    //banner集合
    List<BannerBean.BannersBean> banners = new ArrayList<>();
    //推荐歌单集合
    List<MainRecommendPlayListBean.RecommendBean> recommends = new ArrayList<>();
    List<PlaylistBean> list = new ArrayList<>();

    public WowFragment() {
        setFragmentTitle(App.getContext().getString(R.string.wow));
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtil.d(TAG, "initView  isPrepared：" + isPrepared() + " isFragmentVisible：" + isFragmentVisible());
        View rootView = inflater.inflate(R.layout.fragment_wow, container, false);
        ButterKnife.bind(this, rootView);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setDelayTime(4000)
                .setImageLoader(new BannerGlideImageLoader())
                .isAutoPlay(true);
        return rootView;
    }


    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");
        list.clear();
        recommends.clear();
        bannerImageList.add("https://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        bannerImageList.add("https://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        bannerImageList.add("https://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        bannerImageList.add("https://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");

        banner.setImages(bannerImageList).start();
        banner.setOnBannerListener(position -> {
            ToastUtils.show("你以为轮播图可以点，但是我也找不到入口哒！");

            List<SongInfo> songInfos =new ArrayList<>();
        });
//        recommendPlayListAdapter = new PlayListAdapter(getContext());
//        recommendPlayListAdapter.setType(1);
//        GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
//        rvRecommendPlayList.setLayoutManager(manager);
//        rvRecommendPlayList.setHasFixedSize(true);
//        rvRecommendPlayList.setAdapter(recommendPlayListAdapter);
       // showDialog();
       // mPresenter.getBanner();
        //mPresenter.getRecommendPlayList();
    }

    @Override
    public WowPresenter onCreatePresenter() {
        return new WowPresenter(this);
    }

    @Override
    protected void initVariables(Bundle bundle) {
    }



    @Override
    public void onGetBannerSuccess(BannerBean bean) {
        LogUtil.d(TAG, "onGetBannerSuccess" + bean);
//        banners.addAll(bean.getBanners());
//        loadImageToList();
    }



    @Override
    @OnClick({R.id.rl_day_rec, R.id.rl_play_list, R.id.rl_rank, R.id.rl_radio, R.id.rl_live,
            R.id.tv_playlist_playground})
    public void onClick(View v) {
        if (ClickUtil.isFastClick(1000, v)) {
            return;
        }
        switch (v.getId()) {
//            case R.id.rl_day_rec:
//                startActivity(new Intent(activity, DailyRecommendActivity.class));
//                break;
//            case R.id.rl_play_list:
//                startActivity(new Intent(activity, PlayListRecommendActivity.class));
//                break;
//            case R.id.rl_rank:
//                startActivity(new Intent(activity, RankActivity.class));
//                break;
//            case R.id.rl_radio:
//                startActivity(new Intent(activity, RadioRecommendActivity.class));
//                break;
//            case R.id.rl_live:
//                ToastUtils.show("没有提供直播接口哦，你想看我跳舞也行");
//                break;
//            case R.id.tv_playlist_playground:
//                startActivity(new Intent(activity, PlayListRecommendActivity.class));
//                break;
        }
    }

    @Override
    public void onGetBannerFail(String e) {
        ToastUtils.show(e);
        LogUtil.e(TAG, "onGetBannerFail : " + e);
    }

    @Override
    public void onGetRecommendPlayListSuccess(MainRecommendPlayListBean bean) {
        hideDialog();
        LogUtil.d(TAG, "onGetRecommendPlayListSuccess" + bean);
        recommends.addAll(bean.getRecommend());
        for (int i = 0; i < recommends.size(); i++) {
            PlaylistBean beanInfo = new PlaylistBean();
            beanInfo.setPlaylistName(recommends.get(i).getName());
            beanInfo.setPlaylistCoverUrl(recommends.get(i).getPicUrl());
            list.add(beanInfo);
        }
//        recommendPlayListAdapter.setListener(listClickListener);
//        recommendPlayListAdapter.notifyDataSetChanged(list);
    }

//    private PlayListAdapter.OnPlayListClickListener listClickListener = position -> {
//        if (recommends != null && !recommends.isEmpty()) {
//            //进入歌单详情页面
//            Intent intent = new Intent(getActivity(), PlayListActivity.class);
//            MainRecommendPlayListBean.RecommendBean bean = recommends.get(position);
//            String playlistName = bean.getName();
//            intent.putExtra(PLAYLIST_NAME, playlistName);
//            String playlistPicUrl = bean.getPicUrl();
//            intent.putExtra(PLAYLIST_PICURL, playlistPicUrl);
//            String playlistCreatorNickname = bean.getCreator().getNickname();
//            intent.putExtra(PLAYLIST_CREATOR_NICKNAME, playlistCreatorNickname);
//            String playlistCreatorAvatarUrl = bean.getCreator().getAvatarUrl();
//            intent.putExtra(PLAYLIST_CREATOR_AVATARURL, playlistCreatorAvatarUrl);
//            long playlistId = bean.getId();
//            intent.putExtra(PLAYLIST_ID, playlistId);
//            startActivity(intent);
//        }
//    };

    @Override
    public void onGetRecommendPlayListFail(String e) {
        hideDialog();
        ToastUtils.show(e);
        LogUtil.e(TAG, "onGetRecommendPlayListFail : " + e);
    }

    @Override
    public void onGetDailyRecommendSuccess(DailyRecommendBean bean) {

    }

    @Override
    public void onGetDailyRecommendFail(String e) {

    }

    @Override
    public void onGetTopListSuccess(TopListBean bean) {

    }

    @Override
    public void onGetTopListFail(String e) {

    }

    @Override
    public void onGetPlayListSuccess(RecommendPlayListBean bean) {

    }

    @Override
    public void onGetPlayListFail(String e) {

    }

    @Override
    public void onGetPlaylistDetailSuccess(PlaylistDetailBean bean) {

    }

    @Override
    public void onGetPlaylistDetailFail(String e) {

    }

    @Override
    public void onGetMusicCanPlaySuccess(MusicCanPlayBean bean) {

    }

    @Override
    public void onGetMusicCanPlayFail(String e) {

    }

    @Override
    public void onGetHighQualitySuccess(HighQualityPlayListBean bean) {

    }

    @Override
    public void onGetHighQualityFail(String e) {

    }

}
