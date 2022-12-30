package com.amap_flutter_tools.activity;


import android.os.Bundle;

import com.amap.api.navi.AmapRouteActivity;

public class CustomAmapRouteActivity extends AmapRouteActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}