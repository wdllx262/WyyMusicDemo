package com.bianfeng.wyymusicdemo.song.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bianfeng.wyymusicdemo.R;
import com.bianfeng.wyymusicdemo.base.BaseAdapter;
import com.bianfeng.wyymusicdemo.util.LogUtil;
import com.lzx.musiclibrary.aidl.model.SongInfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 播放列表的适配器
 */
public class SongListAdapter extends BaseAdapter<RecyclerView.ViewHolder, SongInfo> {
    private static final String TAG = "SongListAdapter";

    private List<SongInfo> list = new ArrayList<>();
    private OnSongClickListener listener;



    public SongListAdapter(Context context) {
        super(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void notifyDataSetChanged(List<SongInfo> dataList) {
        list = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(getInflater().inflate(R.layout.item_song_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder != null) {
            ViewHolder vh = (ViewHolder) viewHolder;
            vh.setBean(list.get(i));
            vh.setListener(listener, i);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSongName, tvSingerName, tvLink;
        ImageView ivDel, ivHorn;
        RelativeLayout rlSongPlay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivDel = itemView.findViewById(R.id.iv_del);
            tvSongName = itemView.findViewById(R.id.tv_music_name);
            tvLink = itemView.findViewById(R.id.tv_link);
            tvSingerName = itemView.findViewById(R.id.tv_artist_name);
            ivHorn = itemView.findViewById(R.id.iv_horn);
            rlSongPlay = itemView.findViewById(R.id.rl_song_play);
        }

        public void setBean(SongInfo songInfo) {
            tvSongName.setText(songInfo.getSongName());
            tvSingerName.setText(songInfo.getArtist());

        }

        public void setListener(OnSongClickListener listener, int i) {
            rlSongPlay.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onMusicClick(i);
                }
            });

            ivDel.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onDelClick(i);
                }
            });
        }
    }

    public interface OnSongClickListener {
        void onMusicClick(int position);

        void onDelClick(int position);
    }

    public void setListener(OnSongClickListener listener) {
        this.listener = listener;
    }
}
