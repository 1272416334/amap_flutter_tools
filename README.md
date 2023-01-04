# amap_flutter_tools

# 基于高德地图sdk,集成导航、路线规划、定位、地图

flutte amap tools plugin

## Getting Started

This project is a starting point for a Flutter
[plug-in package](https://flutter.dev/developing-packages/), a specialized package that includes
platform-specific implementation code for Android and/or iOS.

本插件集成高德地图sdk中的3D地图、定位、路线规划、模拟导航和真实导航功能 更多需求和内容请查阅[高德地图官方api]：https://lbs.amap.com/
由于高德地图官方flutter plugin 没有路线规划和导航，所以作此集成，使用安卓原生java代码实现导航功能。

## 开始使用

### github版引入项目

### 1、在flutter项目目录下的pubspec.yaml中添加以下代码:  <br>
     amap_flutter_tools:  <br>
     git:  
     url: https://github.com/1272416334/amap_flutter_tools  <br>
     ref: main  <br>
### 2、在项目目录下的android/app/build.gradle文件中的dependencies添加以下代码：  <br>
dependencies  <br>
{  <br>
     ...  <br>
     implementation 'com.amap.api:navi-3dmap:latest.integration'   <br>
     ...  <br>
}  <br>
### 3、在android/app/src/main/AndroidManifest.xml，application标签内添加以下代码：  <br>
### 不注册activity无法使用，还未深入接触安卓原生，不明原因，后期可能会修改  
<!--     注册activity-->  <br>
     <activity android:name="com.amap_flutter_tools.activity.EmulatorActivity"/
     <activity android:name="com.amap_flutter_tools.activity.BaseActivity"/>
     <activity android:name="com.amap_flutter_tools.activity.CustomAmapRouteActivity"/>


For help getting started with Flutter development, view the
[online documentation](https://flutter.dev/docs), which offers tutorials, samples, guidance on
mobile development, and a full API reference.

# flutterPackage

# flutterPackage

# flutterPackage
