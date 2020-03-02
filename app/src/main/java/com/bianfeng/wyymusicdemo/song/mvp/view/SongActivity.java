package com.bianfeng.wyymusicdemo.song.mvp.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bianfeng.wyymusicdemo.R;
import com.bianfeng.wyymusicdemo.base.BaseActivity;
import com.bianfeng.wyymusicdemo.bean.LikeListBean;
import com.bianfeng.wyymusicdemo.manager.event.MusicPauseEvent;
import com.bianfeng.wyymusicdemo.manager.event.MusicStartEvent;
import com.bianfeng.wyymusicdemo.song.bean.CommentLikeBean;
import com.bianfeng.wyymusicdemo.song.bean.LikeMusicBean;
import com.bianfeng.wyymusicdemo.song.bean.LyricBean;
import com.bianfeng.wyymusicdemo.song.bean.MusicCommentBean;
import com.bianfeng.wyymusicdemo.song.bean.PlayListCommentBean;
import com.bianfeng.wyymusicdemo.song.bean.SongDetailBean;
import com.bianfeng.wyymusicdemo.song.mvp.contract.SongContract;
import com.bianfeng.wyymusicdemo.song.mvp.presenter.SongPresenter;
import com.bianfeng.wyymusicdemo.util.LogUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.toast.ToastUtils;
import com.lzx.musiclibrary.aidl.model.SongInfo;
import com.lzx.musiclibrary.manager.MusicManager;
import com.lzx.musiclibrary.manager.TimerTaskManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.BlurTransformation;



public class SongActivity extends BaseActivity<SongPresenter> implements SongContract.View {
    private static final String TAG = "SongActivity";

    public static final String SONG_INFO = "songInfo";

    @BindView(R.id.iv_record)
    CircleImageView ivRecord;
    @BindView(R.id.iv_like)
    ImageView ivLike;
    @BindView(R.id.tv_past_time)
    TextView tvPastTime;
    @BindView(R.id.total_time)
    TextView tvTotalTime;
    @BindView(R.id.seek_bar)
    AppCompatSeekBar seekBar;
    @BindView(R.id.iv_play)
    ImageView ivPlay;
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.iv_play_mode)
    ImageView ivPlayMode;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;


    private SongInfo currentSongInfo;
    private long ids;

    private TimerTaskManager mTimerTask;
    private boolean isLike = false;
    private int playMode;
    private ObjectAnimator rotateAnimator;
    private ObjectAnimator alphaAnimator;
    private boolean isShowLyrics = false;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMusicStartEvent(MusicStartEvent event) {
        LogUtil.d(TAG, "onMusicStartEvent");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMusicPauseEvent(MusicPauseEvent event) {
        LogUtil.d(TAG, "onMusicPauseEvent");
        checkMusicPlaying();
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_song);

        ImmersionBar.with(this)
                .transparentBar()
                .statusBarDarkFont(false)
                .init();

    }

    @Override
    protected SongPresenter onCreatePresenter() {
        return new SongPresenter(this);
    }

    @Override
    protected void initModule() {
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "initData");
        getIntentData();
        setBackBtn(getString(R.string.colorWhite));
    }


    private void initTimerTaskWork() {
    }


    private void getIntentData() {
        Intent intent = getIntent();
        currentSongInfo = intent.getParcelableExtra(SONG_INFO);
    }



    private void checkMusicPlaying() {

    }


    /**
     * 该方法主要使用正则表达式来判断字符串中是否包含字母
     */
    public boolean judgeContainsStr(String cardNum) {
        String regex = ".*[a-zA-Z]+.*";
        Matcher m = Pattern.compile(regex).matcher(cardNum);
        return m.matches();
    }

    private ObjectAnimator getRotateAnimator() {
        if (rotateAnimator == null) {
            rotateAnimator = ObjectAnimator.ofFloat(ivRecord, "rotation", 360f);
            rotateAnimator.setDuration(25 * 1000);
            rotateAnimator.setInterpolator(new LinearInterpolator());
            rotateAnimator.setRepeatCount(100000);
            rotateAnimator.setRepeatMode(ValueAnimator.RESTART);
        }
        return rotateAnimator;
    }

    private ObjectAnimator getAlphaAnimator() {
        if (alphaAnimator == null) {
            alphaAnimator = ObjectAnimator.ofFloat(ivBg, "alpha", 0f, 0.13f);
            alphaAnimator.setDuration(1500);
            alphaAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        }
        return alphaAnimator;
    }

    private void setSongDetailBean(SongDetailBean songDetail) {
        String coverUrl = songDetail.getSongs().get(0).getAl().getPicUrl();
        Glide.with(this)
                .load(coverUrl)
                .placeholder(R.drawable.shape_record)
                .into(ivRecord);
        Glide.with(this)
                .load(coverUrl)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 12)))
                .transition(new DrawableTransitionOptions().crossFade(1500))
                .into(ivBg);
//        calculateColors(coverUrl);
    }

    @Override
    @OnClick({R.id.iv_play, R.id.iv_like, R.id.iv_download, R.id.iv_comment, R.id.iv_info,
            R.id.iv_play_mode, R.id.iv_pre, R.id.iv_next, R.id.iv_list, R.id.rl_center, R.id.lrc})
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.rl_center:
                isShowLyrics = true;
                showLyrics(true);
                break;

            case R.id.iv_like:
                if (isLike) {
                    ToastUtils.show("Sorry啊，我没有找到取消喜欢的接口");
                } else {
                    mPresenter.likeMusic(ids);
                }
                break;
            case R.id.iv_download:
                ToastUtils.show("Sorry啊，歌都不是我的，不能下载的");
                break;
            case R.id.iv_pre:
                //上一首
                MusicManager.get().playPre();
                break;
            case R.id.iv_next:
                //下一首
                MusicManager.get().playNext();
                break;
            case R.id.iv_play:
                //暂停/播放
                if ( MusicManager.isPlaying())
                {
                    ivPlay.setImageResource(R.drawable.shape_play_white);
                    MusicManager.get().pauseMusic();
                }
                else {
                    ivPlay.setImageResource(R.drawable.shape_stop_white);
                    MusicManager.get().resumeMusic();
                }
                break;

        }
    }

    //根据isShowLyrics来判断是否展示歌词
    private void showLyrics(boolean isShowLyrics) {
        ivRecord.setVisibility(isShowLyrics ? View.GONE : View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

        if (rotateAnimator != null) {
            if (rotateAnimator.isRunning()) {
                rotateAnimator.cancel();
            }
            rotateAnimator = null;
        }
        if (alphaAnimator != null) {
            if (alphaAnimator.isRunning()) {
                alphaAnimator.cancel();
            }
            alphaAnimator = null;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        ivPlay=findViewById(R.id.iv_play);
        if ( MusicManager.isPlaying())
        {
            ivPlay.setImageResource(R.drawable.shape_play_white);
        }
        else {
            ivPlay.setImageResource(R.drawable.shape_stop_white);
        }

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

        }
    };

//    public void calculateColors(String url) {
//        new Thread(() -> {
//            try {
//                //渐变色的两个颜色
//                URL fileUrl;
//                Bitmap bitmap;
//                fileUrl = new URL(url);
//                HttpURLConnection conn = (HttpURLConnection) fileUrl.openConnection();
//                conn.setDoInput(true);
//                conn.connect();
//                InputStream is = conn.getInputStream();
//                BitmapFactory.Options opt = new BitmapFactory.Options();
//                opt.inSampleSize = 27;
//                bitmap = BitmapFactory.decodeStream(is, new Rect(), opt);
//                LogUtil.d(TAG, "bitmap : width : " + bitmap.getWidth() + " height : " + bitmap.getHeight());
//
//                Message msg = Message.obtain();
//                msg.what = COMPLETED;
//                msg.obj = new BitmapDrawable(bitmap);
//                handler.sendMessage(msg);
//
//                is.close();
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();
//    }

    @Override
    public void onGetSongDetailSuccess(SongDetailBean bean) {
        LogUtil.d(TAG, "onGetSongDetailSuccess : " + bean);

    }

    @Override
    public void onGetSongDetailFail(String e) {
        LogUtil.d(TAG, "onGetSongDetailFail : " + e);
        ToastUtils.show(e);
    }

    @Override
    public void onLikeMusicSuccess(LikeMusicBean bean) {

    }

    @Override
    public void onLikeMusicFail(String e) {
        LogUtil.e(TAG, "onLikeMusicFail : " + e);
        ToastUtils.show(e);
    }



    @Override
    public void onGetLikeListFail(String e) {
        LogUtil.d(TAG, "onGetLikeListFail");
        ToastUtils.show(e);
    }

    @Override
    public void onGetMusicCommentSuccess(MusicCommentBean bean) {

    }

    @Override
    public void onGetMusicCommentFail(String e) {

    }

    @Override
    public void onLikeCommentSuccess(CommentLikeBean bean) {

    }

    @Override
    public void onLikeCommentFail(String e) {

    }

    @Override
    public void onGetLyricSuccess(LyricBean bean) {

    }

    private void initLrcListener() {

    }

    @Override
    public void onGetLyricFail(String e) {
        LogUtil.e(TAG, "onGetLyricFail: " + e);
        ToastUtils.show(e);
    }

    @Override
    public void onGetPlaylistCommentSuccess(PlayListCommentBean bean) {

    }

    @Override
    public void onGetLikeListSuccess(LikeListBean bean) {

    }

    @Override
    public void onGetPlaylistCommentFail(String e) {

    }

}
