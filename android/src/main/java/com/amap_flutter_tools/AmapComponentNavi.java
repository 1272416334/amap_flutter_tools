package com.amap_flutter_tools;

import static com.amap_flutter_tools.activity.BaseActivity.toFloat;

import android.content.Context;
import android.view.View;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap_flutter_tools.activity.CustomAmapRouteActivity;

import java.util.List;
import java.util.Map;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

/**
 * Created by fengshun
 * Create Date 2019-06-27 15:25
 * amap.com.example.flutter_amap_plugin.Nav.Component
 */
public class AmapComponentNavi implements INaviInfoCallback, MethodChannel.MethodCallHandler {
    private final Context mContext;

    private String name;

    private LatLng mEndLatlng;

    public AmapComponentNavi(Context context) {
        this.mContext = context;
    }

    void initNav() {
//        LatLng p4 = new LatLng(39.773801, 116.368984);//新三余公园(南5环)
//        Poi end = new Poi("新三余公园(南5环)", p4, "");
        if (mEndLatlng != null && name != null) {

            AmapNaviParams param = new AmapNaviParams(null, null, new Poi(name, mEndLatlng, ""), AmapNaviType.DRIVER);
            param.setUseInnerVoice(true);
            AmapNaviPage.getInstance().showRouteActivity(mContext, param, this, CustomAmapRouteActivity.class);
        } else {
            AmapNaviParams param = new AmapNaviParams(null, null, null, AmapNaviType.DRIVER);
            param.setUseInnerVoice(true);
            AmapNaviPage.getInstance().showRouteActivity(mContext, param, this, CustomAmapRouteActivity.class);
        }
    }

    @Override
    public void onInitNaviFailure() {
    }

    @Override
    public void onGetNavigationText(String s) {
    }

    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {
    }

    @Override
    public void onArriveDestination(boolean b) {
    }

    @Override
    public void onStartNavi(int i) {
    }

    @Override
    public void onCalculateRouteSuccess(int[] ints) {
    }

    @Override
    public void onCalculateRouteFailure(int i) {
    }

    @Override
    public void onStopSpeaking() {

    }

    @Override
    public void onReCalculateRoute(int i) {
    }

    @Override
    public void onExitPage(int i) {

    }

    @Override
    public void onStrategyChanged(int i) {

    }

    @Override
    public View getCustomNaviBottomView() {
        return null;
    }

    @Override
    public View getCustomNaviView() {
        return null;
    }

    @Override
    public void onArrivedWayPoint(int i) {

    }

    @Override
    public void onMapTypeChanged(int i) {

    }

    @Override
    public void onNaviDirectionChanged(int i) {

    }

    @Override
    public void onDayAndNightModeChanged(int i) {

    }

    @Override
    public void onBroadcastModeChanged(int i) {

    }

    @Override
    public void onScaleAutoChanged(boolean b) {

    }

    @Override
    public View getCustomMiddleView() {
        return null;
    }

    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        Map<String, Object> params = (Map<String, Object>) call.arguments;
        if (params != null) {
            final List<?> to = (List<?>) params.get("toLatLng");
            mEndLatlng = new LatLng(toFloat(to.get(0)), toFloat(to.get(1)));

            name = (String) params.get("name");
        }
        initNav();
    }
}