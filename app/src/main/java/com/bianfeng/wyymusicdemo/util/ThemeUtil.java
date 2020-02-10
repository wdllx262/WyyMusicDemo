package com.bianfeng.wyymusicdemo.util;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

import com.bianfeng.wyymusicdemo.R;

public class ThemeUtil {
    public static void switchThemeType(Activity mContext)
    {
        boolean useTheme=  SharedPreferencesUtils.getInstance(mContext).read("themeType");
        SharedPreferencesUtils.getInstance(mContext).put("themeType",!useTheme);
        useTheme = SharedPreferencesUtils.getInstance(mContext).read("themeType");
        if (useTheme)
        {
            mContext.setTheme(R.style.AppTheme);
        }else {
            mContext.setTheme(R.style.NightTheme);
        }
        final View rootView = mContext.getWindow().getDecorView();
        ColorUiUtil.changeTheme(rootView, mContext.getTheme());
//        if(Build.VERSION.SDK_INT < 14) {
//            ColorUiUtil.changeTheme(rootView, mContext.getTheme());
//
//        } else {
//            rootView.setDrawingCacheEnabled(true);
//            rootView.buildDrawingCache(true);
//            final Bitmap localBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
//            rootView.setDrawingCacheEnabled(false);
//            if (null != localBitmap && rootView instanceof ViewGroup) {
//                final View localView2 = new View(mContext.getApplicationContext());
//                localView2.setBackgroundDrawable(new BitmapDrawable(mContext.getResources(), localBitmap));
//                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                ((ViewGroup) rootView).addView(localView2, params);
//                localView2.animate().alpha(0).setDuration(400).setListener(new Animator.AnimatorListener() {
//                    @Override
//                    public void onAnimationStart(Animator animation) {
//                        ColorUiUtil.changeTheme(rootView, mContext.getTheme());
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        ((ViewGroup) rootView).removeView(localView2);
//                        localBitmap.recycle();
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animator animation) {
//
//                    }
//                }).start();
//            }
//        }
    }
}
