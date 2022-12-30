import 'dart:async';

import 'package:amap_flutter_base/amap_flutter_base.dart';
import 'package:flutter/services.dart';

class AmapFlutterNavi {
  static const MethodChannel _channel =
  MethodChannel('amap_flutter_navi');

  static Future<String?> get platformVersion async {
    final String? version = await _channel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  static Future startNavi(LatLng fromLatLng, LatLng toLatLng) async {
    await _channel.invokeMethod<dynamic>('startNavi',
        <String,dynamic>{'fromLatLng': fromLatLng.toJson(), 'toLatLng': toLatLng.toJson()});
  }

  /// 不传入终点可以进入后自己输入，也可以传
  static Future startNaviByEnd(
      [LatLng? toLatLng, String? name]) async {
    if (toLatLng != null && name != null) {
      await _channel.invokeMethod<dynamic>('startNaviByEnd', <String,dynamic>{
        'toLatLng': toLatLng.toJson(),
        'name': name
      });
    } else {
      await _channel.invokeMethod<String>('startNaviByEnd');
    }
  }

  static Future init(String iosKey) async {
    await _channel.invokeMethod<dynamic>('init', {'iosKey': iosKey});
  }
}
