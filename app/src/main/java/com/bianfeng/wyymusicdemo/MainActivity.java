package com.bianfeng.wyymusicdemo;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;


import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bianfeng.wyymusicdemo.base.BaseFragment;
import com.bianfeng.wyymusicdemo.main.adapter.MultiFragmentPagerAdapter;
import com.bianfeng.wyymusicdemo.main.mvp.view.fragments.CloudVillageFragment;
import com.bianfeng.wyymusicdemo.main.mvp.view.fragments.MineFragment;
import com.bianfeng.wyymusicdemo.main.mvp.view.fragments.MyMvSubFragment;
import com.bianfeng.wyymusicdemo.main.mvp.view.fragments.WowFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.tab_title)
    TabLayout tab;
    @BindView(R.id.ic_nav)
    ImageView nav_image;
    @BindView(R.id.main_viewpager)
    ViewPager viewPager;


    private List<BaseFragment> fragments = new ArrayList<>();
    private MultiFragmentPagerAdapter mPagerAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
