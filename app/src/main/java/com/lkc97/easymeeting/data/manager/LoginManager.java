package com.lkc97.easymeeting.data.manager;

import android.content.SharedPreferences;

import com.avos.avoscloud.AVUser;
import com.lkc97.easymeeting.data.network.Login;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by 77416 on 2018/1/28.
 */

public class LoginManager {
    private Login mLogin=new Login();
    public boolean checkLoginState(){
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            // 跳转到首页
            return true;
        } else {
            //缓存用户对象为空时，打开用户注册界面
            return false;
        }
    }
    //发送验证码
    public void sendVerificationCode(String phoneNumber){
        mLogin.loginByPhoneNumber(phoneNumber);
    }
    //使用验证码登陆
    public boolean checkVerificationCode(String VerificationCode){
        mLogin.checkVerificationCode(VerificationCode);
        return mLogin.getLoginState();
    }
}
