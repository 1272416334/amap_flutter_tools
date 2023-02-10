/*
 * @Description: 高德导航demo
 * @Author: dmlzj
 * @Github: https://github.com/dmlzj
 * @Email: 284832506@qq.com
 * @Date: 2021-01-28 17:28:23
 * @LastEditors: dmlzj
 * @LastEditTime: 2021-02-02 10:05:48
 * @如果有bug，那肯定不是我的锅，嘤嘤嘤
 */
import 'package:amap_flutter_base/amap_flutter_base.dart';
import 'package:amap_flutter_location/amap_flutter_location.dart';
import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:permission_handler/permission_handler.dart';
import 'package:amap_flutter_tools/amap_flutter_navi.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';

  @override
  void initState() {
    super.initState();
    initPlatformState();
    AmapFlutterNavi.init('8274bbca80f3448d2d9fb54fda198ba0');

    /// 设置是否已经包含高德隐私政策并弹窗展示显示用户查看，如果未包含或者没有弹窗展示，高德定位SDK将不会工作
    /// [hasShow] 隐私权政策是否弹窗展示告知用户
    AMapFlutterLocation.updatePrivacyShow(true, true);

    /// 设置是否已经取得用户同意，如果未取得用户同意，高德定位SDK将不会工作
    /// [hasAgree] 隐私权政策是否已经取得用户同意
    AMapFlutterLocation.updatePrivacyAgree(true);

    /// 动态申请定位权限
    requestPermission();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String? platformVersion;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      platformVersion = await AmapFlutterNavi.platformVersion;
    } on PlatformException {
      platformVersion = 'Failed to get platform version.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _platformVersion = platformVersion!;
    });
  }

  Widget item(String title, VoidCallback callback) {
    return InkWell(
      onTap: callback,
      child: Container(
        margin: EdgeInsets.only(top: 20),
        child: Text(title),
      ),
    );
  }

  Future<bool> requestPermission() async {
    final status = await Permission.location.request();

    if (status == PermissionStatus.granted) {
      return true;
    } else {
      // CustomDialog.showToast('需要定位权限');
      Text('需要定位权限');
      return false;
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Flutter高德地图插件example'),
        ),
        body: ListView(
          padding: EdgeInsets.only(left: 16),
          children: <Widget>[
            item('从我的位置-[终点自己搜索]', () async {
              if (await this.requestPermission()) {
                AmapFlutterNavi.startNaviByEnd();
              }
            }),
            item('从我的位置-测试地点', () async {
              if (await this.requestPermission()) {
                LatLng toLatLng = LatLng(39.963175, 116.42669);
                AmapFlutterNavi.startNaviByEnd(toLatLng, '测试地点');
              }
            }),
          ],
        ),
      ),
    );
  }
}

