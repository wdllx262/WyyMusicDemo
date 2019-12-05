package com.bianfeng.wyymusicdemo;

import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;



public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //黑色状态栏字体
        int originFlag = this.getWindow().getDecorView().getSystemUiVisibility();
        this.getWindow().getDecorView().setSystemUiVisibility(originFlag | View
                .SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }


}
