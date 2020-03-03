package com.bianfeng.wyymusicdemo.main.mvp;

import android.animation.Animator;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bianfeng.wyymusicdemo.R;
import com.bianfeng.wyymusicdemo.base.BaseFragment;
import com.bianfeng.wyymusicdemo.main.adapter.MultiFragmentPagerAdapter;
import com.bianfeng.wyymusicdemo.main.mvp.view.fragments.CloudVillageFragment;
import com.bianfeng.wyymusicdemo.main.mvp.view.fragments.MineFragment;
import com.bianfeng.wyymusicdemo.main.mvp.view.fragments.MyMvSubFragment;
import com.bianfeng.wyymusicdemo.main.mvp.view.fragments.WowFragment;
import com.bianfeng.wyymusicdemo.util.ClickUtil;
import com.bianfeng.wyymusicdemo.util.ColorUiUtil;
import com.bianfeng.wyymusicdemo.util.SharedPreferencesUtils;
import com.bianfeng.wyymusicdemo.util.ThemeUtil;
import com.bianfeng.wyymusicdemo.widget.BottomSongPlayBar;
import com.hjq.toast.ToastUtils;
import com.lzx.musiclibrary.aidl.listener.OnPlayerEventListener;
import com.lzx.musiclibrary.aidl.model.SongInfo;
import com.lzx.musiclibrary.manager.MusicManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.tab_title)
    TabLayout tab;
    @BindView(R.id.ic_nav)
    ImageView nav_image;
    @BindView(R.id.main_viewpager)
    ViewPager viewPager;
    @BindView(R.id.tv_dayornight_mode)
    TextView dayornight_mode;


    private long firstTime;
    Context mContext;


    private List<BaseFragment> fragments = new ArrayList<>();
    private MultiFragmentPagerAdapter mPagerAdapter;

    Boolean  useTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
         useTheme = SharedPreferencesUtils.getInstance(this).read("themeType");
        if (useTheme)
        {
            setTheme(R.style.AppTheme);
        }else {
            setTheme(R.style.NightTheme);
        }

        setContentView(R.layout.activity_main);
             //黑色状态栏字体
             int originFlag = this.getWindow().getDecorView().getSystemUiVisibility();
             this.getWindow().getDecorView().setSystemUiVisibility(originFlag | View
                     .SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

//        ImageView imageView=findViewById(R.id.ic_nav);
        ButterKnife.bind(this);
        nav_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        tab.setTabTextColors(Color.parseColor("#e78c86"), Color.parseColor("#FFFDFD"));
        mPagerAdapter = new MultiFragmentPagerAdapter(getSupportFragmentManager());
        fragments.add(new MineFragment());
        fragments.add(new WowFragment());
        fragments.add(new CloudVillageFragment());
        fragments.add(new MyMvSubFragment());
        mPagerAdapter.init(fragments);
        initTabListener();
        viewPager.setAdapter(mPagerAdapter);
        tab.setupWithViewPager(viewPager);

    }

    @Override
    @OnClick({R.id.tv_dayornight_mode,R.id.iv_dayornight})
    public void onClick(View v) {
        if (ClickUtil.isFastClick(1000, v)) {
            return;
        }
        switch (v.getId()) {
            case R.id.tv_dayornight_mode:
               ThemeUtil.switchThemeType(this);
                //播放asset文件下文件示例
//                com.lzx.musiclibrary.aidl.model.SongInfo msongInfo = new com.lzx.musiclibrary.aidl.model.SongInfo();
//                msongInfo.setSongId("111");
//                msongInfo.setSongUrl("file:///android_asset/music.mp3");
//                MusicManager.get().playMusicByInfo(msongInfo);


                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    private void initTabListener()
    {
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setSelectTextBoldAndBig(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setCustomView(null);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setSelectTextBoldAndBig(TabLayout.Tab tab) {
        TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.design_layout_tab_text, null);
        textView.setText(tab.getText());
        textView.setScaleY(1.5f);
        textView.setScaleX(1.5f);
        textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        textView.setTextColor(Color.parseColor("#FFFDFD"));
        tab.setCustomView(textView);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {// 点击了返回按键
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
            exitApp(2000);// 退出应用
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onResume() {
        super.onResume();
        BottomSongPlayBar bottomSongPlayBar=findViewById(R.id.bottom_controller);
        bottomSongPlayBar.switchPlay();
    }

    /**
     * 退出应用
     *
     * @param timeInterval 设置第二次点击退出的时间间隔
     */
    private void exitApp(long timeInterval) {
        if (System.currentTimeMillis() - firstTime >= timeInterval) {
            ToastUtils.show(R.string.press_exit_again);
            firstTime = System.currentTimeMillis();
        } else {
            MusicManager.get().pauseMusic();
            SongInfo songInfo= MusicManager.get().getCurrPlayingMusic();
            long Progress=MusicManager.get().getProgress();
            SharedPreferencesUtils.getInstance(this).putSongInfo(songInfo,Progress,MusicManager.get().getCurrPlayingIndex());
            Log.e("11111111","退出了");
            finish();// 销毁当前activity
            System.exit(0);// 完全退出应用
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
