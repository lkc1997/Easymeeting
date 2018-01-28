package com.lkc97.easymeeting.data.network;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.RequestMobileCodeCallback;

/**
 * Created by 77416 on 2018/1/28.
 */

public class Login{
    private static String phoneNumber=null;
    private static boolean loginState=false;
    public void loginByPhoneNumber(String userPhoneNumber){
        AVUser.requestLoginSmsCodeInBackground(userPhoneNumber, new RequestMobileCodeCallback() {
            @Override
            public void done(AVException e) {
                if (null == e) {
                // 请求成功
                } else {
                // 请求失败
                }
            }
        });
        phoneNumber=userPhoneNumber;
    }
    public void checkVerificationCode(String verificationCode) {
        AVUser.signUpOrLoginByMobilePhoneInBackground(phoneNumber, verificationCode, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (e == null) {
                    // 登陆成功
                    loginState=true;
                } else {
                    // 登陆失败
                }
            }
        });
    }
    public boolean getLoginState(){
        return loginState;
    }
}
