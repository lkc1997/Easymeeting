package com.lkc97.easymeeting.data.network;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.lkc97.easymeeting.ui.MainActivity;
import com.lkc97.easymeeting.ui.login.LoginActivity;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by 77416 on 2018/1/28.
 */

public class Login{
    private int loginState=0;
    private String userName=null;
    private String password=null;
    public Login(String userName,String password){
        this.userName=userName;
        this.password=password;
    }
    public int loginByPassword(){
        AVUser.logInInBackground(userName, password, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if(e==null){
                    //登陆成功
                    loginState=1;
                    Log.d("EasymeetingLog","登陆成功,loginState="+loginState);
                }else{
                    //登陆失败
                    loginState=-1;
                    Log.d("EasymeetingLog","登陆失败,loginState="+loginState);
                }
            }
        });
        return loginState;
    }
    public int getLoginState(){
        return loginState;
    }
}
