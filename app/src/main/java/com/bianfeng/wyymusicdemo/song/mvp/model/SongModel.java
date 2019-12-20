package com.bianfeng.wyymusicdemo.song.mvp.model;

import com.bianfeng.wyymusicdemo.bean.LikeListBean;
import com.bianfeng.wyymusicdemo.song.bean.CommentLikeBean;
import com.bianfeng.wyymusicdemo.song.bean.LikeMusicBean;
import com.bianfeng.wyymusicdemo.song.bean.LyricBean;
import com.bianfeng.wyymusicdemo.song.bean.MusicCommentBean;
import com.bianfeng.wyymusicdemo.song.bean.PlayListCommentBean;
import com.bianfeng.wyymusicdemo.song.bean.SongDetailBean;
import com.bianfeng.wyymusicdemo.song.mvp.contract.SongContract;

import io.reactivex.Observable;

public class SongModel implements SongContract.Model {
    @Override
    public Observable<SongDetailBean> getSongDetail(long ids) {
        return null;
    }

    @Override
    public Observable<LikeMusicBean> likeMusic(long id) {
        return null;
    }

    @Override
    public Observable<LikeListBean> getLikeList(long uid) {
        return null;
    }

    @Override
    public Observable<MusicCommentBean> getMusicComment(long id) {
        return null;
    }

    @Override
    public Observable<CommentLikeBean> likeComment(long id, long cid, int t, int type) {
        return null;
    }

    @Override
    public Observable<LyricBean> getLyric(long id) {
        return null;
    }

    @Override
    public Observable<PlayListCommentBean> getPlaylistComment(long id) {
        return null;
    }
}
