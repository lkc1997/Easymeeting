package com.lkc97.easymeeting.application;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.lkc97.easymeeting.ui.adapter.CustomUserProvider;

import cn.leancloud.chatkit.LCChatKit;

/**
 * Created by 77416 on 2018/1/27.
 */

public class MyApplication extends Application {
    private final String AppID="GlS5n1W7dxjFBEnxRu2GLIvl-gzGzoHsz";
    private final String AppKey="49BNFGxJt0VqnB4l9KKXIHOP";
    @Override
    public void onCreate(){
        super.onCreate();
        // 初始化LeacCloud的App ID 以及 App Key
        AVOSCloud.initialize(this,AppID,AppKey);
        // 开启调试日志(网络请求、错误消息等信息输出到 IDE 的日志窗口)
        AVOSCloud.setDebugLogEnabled(true);
    }
}
