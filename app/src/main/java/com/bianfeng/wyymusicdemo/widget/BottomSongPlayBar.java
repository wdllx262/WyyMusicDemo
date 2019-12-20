package com.bianfeng.wyymusicdemo.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bianfeng.wyymusicdemo.R;
import com.bianfeng.wyymusicdemo.song.mvp.view.SongActivity;
import com.lzx.starrysky.model.SongInfo;


import de.hdodenhof.circleimageview.CircleImageView;

public class BottomSongPlayBar extends RelativeLayout {
    private static final String TAG="BottomSongPlayBar";

    private Context mContext;

    private RelativeLayout rlSongController;
    private CircleImageView ivCover;
    private ImageView ivPlay,ivController;
    private TextView tvSongName,tvSongSinger;
    private LinearLayout llSongInfo;
    private SongInfo currentSongInfo;

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

        });

        ivController.setOnClickListener(v -> {

        });

    }

    private void initSongInfo()
    {
        //初始化的时候，要从本地拿最近一次听的歌曲


    }


}
