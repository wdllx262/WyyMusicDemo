package com.bianfeng.wyymusicdemo.main.mvp.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bianfeng.wyymusicdemo.App;
import com.bianfeng.wyymusicdemo.R;
import com.bianfeng.wyymusicdemo.base.BaseFragment;
import com.bianfeng.wyymusicdemo.bean.AlbumSublistBean;
import com.bianfeng.wyymusicdemo.bean.ArtistSublistBean;
import com.bianfeng.wyymusicdemo.bean.MainEventBean;
import com.bianfeng.wyymusicdemo.bean.MvSublistBean;
import com.bianfeng.wyymusicdemo.bean.MyFmBean;
import com.bianfeng.wyymusicdemo.bean.PlayModeIntelligenceBean;
import com.bianfeng.wyymusicdemo.main.mvp.contract.EventContract;
import com.bianfeng.wyymusicdemo.main.mvp.contract.MineContract;
import com.bianfeng.wyymusicdemo.main.mvp.presenter.EventPresenter;
import com.bianfeng.wyymusicdemo.main.mvp.presenter.MinePresenter;
import com.bianfeng.wyymusicdemo.persional.bean.UserEventBean;
import com.bianfeng.wyymusicdemo.persional.bean.UserPlaylistBean;
import com.bianfeng.wyymusicdemo.util.LogUtil;
import com.hjq.toast.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CloudVillageFragment extends BaseFragment<EventPresenter> implements EventContract.View {
    private static final String TAG = "CloudVillageFragment";
    private static final int tabNum=2;
    private ViewPager viewPager;
    private int page = 0;

    private List<UserEventBean.EventsBean> eventList;

    public CloudVillageFragment() {
        setFragmentTitle(App.getContext().getString(R.string.feed));
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feed, container, false);
        ButterKnife.bind(this, rootView);
        viewPager = (ViewPager) rootView.findViewById(R.id.cloudVillage_viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
            //viewPager.setOffscreenPageLimit(2);
        }
        final TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.cloudVillage_tabs);
        tabLayout.setupWithViewPager(viewPager);
        for (int i=0;i<tabNum;i++) {
            TextView title = (TextView) (((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(i)).getChildAt(1));
            title.setTextSize(18);
            title.setTextAppearance(getActivity(), R.style.TabLayoutTextStyle);
        }
        return rootView;
    }

    @Override
    protected void initData() {
        //rvEvent.setLayoutManager(new LinearLayoutManager(getContext()));


    //    viewPager.setCurrentItem(page);
        //showDialog();
        //mPresenter.getMainEvent();
    }


    @Override
    public EventPresenter onCreatePresenter() {
        return new EventPresenter(this);
    }


    @Override
    protected void initVariables(Bundle bundle) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onGetMainEventSuccess(MainEventBean bean) {
        hideDialog();
        LogUtil.d(TAG, "onGetMainEventSuccess :" + bean);
        eventList = bean.getEvent();
    }

    @Override
    public void onGetMainEventFail(String e) {
        hideDialog();
        LogUtil.e(TAG, "onGetMainEventFail :" + e);
        ToastUtils.show("e");
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new MyMvSubFragment(), "广场");
        adapter.addFragment(new MyMvSubFragment(), "关注");

        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }

    }
}
