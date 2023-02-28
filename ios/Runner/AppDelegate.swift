import UIKit
import Flutter
import AMapNaviKit

@UIApplicationMain
@objc class AppDelegate: FlutterAppDelegate {
  override func application(
    _ application: UIApplication,
    didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
  ) -> Bool {
      //更新App是否显示隐私弹窗的状态，隐私弹窗是否包含高德SDK隐私协议内容的状态. since 8.1.0
      AMapNaviManagerConfig.shared().updatePrivacyShow(AMapPrivacyShowStatus.didShow, privacyInfo: AMapPrivacyInfoStatus.didContain)
      /*
       * 调用隐私合规处理方法
       */
      AMapNaviManagerConfig.shared().updatePrivacyAgree(AMapPrivacyAgreeStatus.didAgree)
      AMapServices.shared().apiKey = "762779b8bbc80e79b6162619fbffd16b"
    GeneratedPluginRegistrant.register(with: self)
    return super.application(application, didFinishLaunchingWithOptions: launchOptions)
  }

    
}
