package com.lkc97.easymeeting.data.manager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.lkc97.easymeeting.data.network.Login;
import com.lkc97.easymeeting.ui.MainActivity;

import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.utils.LCIMConstants;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by 77416 on 2018/1/28.
 */

public class LoginManager {
    public int loginByPassword(String userName,String password){
        Login mLogin=new Login(userName,password);
        //登陆成功返回true,否则返回false
        return mLogin.loginByPassword();
    }
    public boolean checkLoginState(){
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            //登陆chatkit
            LCChatKit.getInstance().open(currentUser.getUsername(), new AVIMClientCallback() {
                @Override
                public void done(AVIMClient avimClient, AVIMException e) {
                    if (null == e) {

                    } else {

                    }
                }
            });
            // 跳转到首页
            return true;
        } else {
            //缓存用户对象为空时，可打开用户注册界面
            return false;
        }
    }
}
