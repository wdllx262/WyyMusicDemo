package com.bianfeng.wyymusicdemo.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bianfeng.wyymusicdemo.R;
import com.bianfeng.wyymusicdemo.helper.DataHelper;
import com.bianfeng.wyymusicdemo.network.RetrofitHelper;
import com.bianfeng.wyymusicdemo.song.mvp.view.SongActivity;
import com.bianfeng.wyymusicdemo.util.SharedPreferencesUtils;
import com.lzx.musiclibrary.aidl.model.SongInfo;
import com.lzx.musiclibrary.db.SongHistoryManager;
import com.lzx.musiclibrary.manager.MusicManager;
import com.lzx.musiclibrary.utils.LogUtil;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BottomSongPlayBar extends RelativeLayout {
    private static final String TAG="BottomSongPlayBar";

    private Context mContext;
    private RelativeLayout rlSongController;
    private CircleImageView ivCover;
    private ImageView ivPlay,ivController;
    private TextView tvSongName,tvSongSinger;
    private LinearLayout llSongInfo;
    private SongInfo currentSongInfo;
    private List<SongInfo> mlst;

    public int size = 10;
    private boolean isMore;


    public BottomSongPlayBar(Context context) {this(context,null);}

    public BottomSongPlayBar(Context context, AttributeSet attrs) {this(context,attrs,0);}

    public BottomSongPlayBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
        initListener();
        initSongInfo();
    }

    private void initView()
    {
        rlSongController=(RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_bottom_songplay_control,this,true);
        ivCover = rlSongController.findViewById(R.id.iv_cover);
        ivPlay = rlSongController.findViewById(R.id.iv_bottom_play);
        ivController = rlSongController.findViewById(R.id.iv_bottom_controller);
        tvSongName = rlSongController.findViewById(R.id.tv_songname);
        tvSongSinger = rlSongController.findViewById(R.id.tv_singer);
        llSongInfo = rlSongController.findViewById(R.id.ll_songinfo);
    }

    private void initListener()
    {
        ivCover.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, SongActivity.class);
            intent.putExtra(SongActivity.SONG_INFO, currentSongInfo);
            mContext.startActivity(intent);
        });
        llSongInfo.setOnClickListener(v -> {

        });

        ivPlay.setOnClickListener(v -> {
            if ( MusicManager.isPlaying())
            {
                ivPlay.setImageResource(R.drawable.shape_play);
                MusicManager.get().pauseMusic();
                SongInfo songInfo= MusicManager.get().getCurrPlayingMusic();
                long Progress=MusicManager.get().getProgress();
                int index=MusicManager.get().getCurrPlayingIndex();
                SharedPreferencesUtils.getInstance(mContext).putSongInfo(songInfo,Progress,index);
            }
            else {
                ivPlay.setImageResource(R.drawable.shape_stop);
                //读取上一次的歌曲信息
                SongInfo songInfo=SharedPreferencesUtils.getInstance(mContext).readSongInfo();
                if (songInfo.getSongName().equals("0"))
                {
                    MusicManager.get().playMusic(mlst,0,false);
                    SharedPreferencesUtils.getInstance(mContext).clear();
                    SharedPreferencesUtils.getInstance(mContext).putSongInfo(MusicManager.get().getCurrPlayingMusic(),0,0);
                }
                else {
                    playOneSong(SharedPreferencesUtils.getInstance(mContext).readIndex());
//                    List<SongInfo> songInfos = new ArrayList<>();
//                    songInfos.add(songInfo);
//                    MusicManager.get().playMusic(songInfos, 0, false);
                    MusicManager.get().seekTo((int)SharedPreferencesUtils.getInstance(mContext).readProcess());
                }
                //MusicManager.get().resumeMusic();
            }
        });

        ivController.setOnClickListener(v -> {

        });

    }

    private void initSongInfo()
    {
        //初始化的时候，要从本地拿最近一次听的歌曲
        getSongList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    isMore = list.size() >= size;
                    getList(list);
                }, throwable -> {
                    LogUtil.i("error = " + throwable.getMessage());
                    Toast.makeText(mContext, "获取数据失败", Toast.LENGTH_SHORT).show();
                });

    }
    public void playOneSong(int index)
    {

        //播放一首歌曲

//        SongInfo songInfo = new SongInfo();
//        songInfo.setSongId("0");
//        songInfo.setSongName("音乐1");
//        songInfo.setSongUrl("http://audio04.dmhmusic.com/71_53_T10052891596_128_4_1_0_sdk-cpm/cn/0208/M00/FD/6E/ChR47F3eKR-AQI61AD0fhCqFKlc657.mp3?xcode=b7a345f27ee059655f4e7c2769b0cc038fcadcc");
//        songInfo.setArtist("11");
//        songInfo.setDuration(10000);
//        songInfos.add(songInfo);
        Log.e("11111111","播放一首歌曲");
        MusicManager.get().playMusic(mlst,index,false);
    }

    private Observable<List<SongInfo>> getSongList() {
        return RetrofitHelper.getMusicApi().requestMusicList(2, 10, 0)
                .map(responseBody -> {
                    List<SongInfo> list = DataHelper.fetchJSONFromUrl(responseBody);
                    List<SongInfo> newList = new ArrayList<>();
                    for (SongInfo info : list) {
                        RetrofitHelper.getMusicApi().playMusic(info.getSongId())
                                .map(responseUrlBody -> {
                                    String json = responseUrlBody.string();
                                    json = json.substring(1, json.length() - 2);
                                    JSONObject jsonObject = new JSONObject(json);
                                    JSONObject bitrate = jsonObject.getJSONObject("bitrate");
                                    return bitrate.getString("file_link");
                                })
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(url -> {
                                    info.setSongUrl(url);
                                    newList.add(info);
                                }, throwable -> {
                                    LogUtil.i("1error = " + throwable.getMessage());
                                });
                    }
                    return newList;
                });
    }

    private void getList(List<SongInfo> list)
    {
        mlst=list;
        Log.e("1111111","获取歌曲成功");
    }



}
