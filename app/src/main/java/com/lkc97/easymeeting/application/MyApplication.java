package com.lkc97.easymeeting.application;

import android.app.Application;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.PushService;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.lkc97.easymeeting.ui.MainActivity;
import com.lkc97.easymeeting.ui.adapter.CustomUserProvider;
import com.lkc97.easymeeting.ui.common.BuddyActivity;
import com.lkc97.easymeeting.ui.common.ContactFragment;

import cn.leancloud.chatkit.LCChatKit;

/**
 * Created by 77416 on 2018/1/27.
 */

public class MyApplication extends Application {
    private final String APP_ID="GlS5n1W7dxjFBEnxRu2GLIvl-gzGzoHsz";
    private final String APP_KEY="49BNFGxJt0VqnB4l9KKXIHOP";
    @Override
    public void onCreate(){
        super.onCreate();
        // 初始化LeacCloud的App ID 以及 App Key
        AVOSCloud.initialize(this,APP_ID,APP_KEY);
        // 开启调试日志(网络请求、错误消息等信息输出到 IDE 的日志窗口)
        AVOSCloud.setDebugLogEnabled(true);
        //初始化chatkit
        LCChatKit.getInstance().setProfileProvider(CustomUserProvider.getInstance());
        LCChatKit.getInstance().init(getApplicationContext(), APP_ID, APP_KEY);
        AVIMClient.setAutoOpen(true);
        PushService.setDefaultPushCallback(this, BuddyActivity.class);
        PushService.setAutoWakeUp(true);
        PushService.setDefaultChannelId(this, "default");
        //保证设备注册到 LeanCloud 云端（推送功能）
        AVInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {
            public void done(AVException e) {
                if (e == null) {
                    // 保存成功
                    String installationId = AVInstallation.getCurrentInstallation().getInstallationId();
                    System.out.println("---  " + installationId);
                } else {
                    // 保存失败，输出错误信息
                    System.out.println("failed to save installation.");
                }
            }
        });
    }
}
