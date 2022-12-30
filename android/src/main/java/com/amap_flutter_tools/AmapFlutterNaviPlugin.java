package com.amap_flutter_tools;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.amap_flutter_tools.activity.BaseActivity;
import com.amap_flutter_tools.activity.EmulatorActivity;

import java.util.Map;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * AmapFlutterNaviPlugin
 */
public class AmapFlutterNaviPlugin implements FlutterPlugin, MethodCallHandler {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private MethodChannel channel;
    static private Context mContext;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "amap_flutter_navi");
        channel.setMethodCallHandler(this);
        mContext = flutterPluginBinding.getApplicationContext();
    }

    // This static function is optional and equivalent to onAttachedToEngine. It supports the old
    // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
    // plugin registration via this function while apps migrate to use the new Android APIs
    // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
    //
    // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
    // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
    // depending on the user's project. onAttachedToEngine or registerWith must both be defined
    // in the same class.
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "amap_flutter_navi");
        mContext = registrar.activeContext();
        channel.setMethodCallHandler(new AmapFlutterNaviPlugin());
    }


    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        Map<String, Object> params = (Map<String, Object>) call.arguments;
        switch (call.method) {
            case "getPlatformVersion":
                result.success("Android " + android.os.Build.VERSION.RELEASE);
                break;
            case "startNavi":
                BaseActivity.setLatLng(params);

                Intent intent = new Intent(mContext, EmulatorActivity.class);
                intent.putExtra("useInnerVoice", true);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
                break;
            case "startNaviByEnd":
                new AmapComponentNavi(mContext).onMethodCall(call,result);
                break;
            case "init":
                result.success("init success");
                break;
            default:
                result.notImplemented();
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }
}
