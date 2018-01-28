package com.lkc97.easymeeting.data.network;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.RequestMobileCodeCallback;

/**
 * Created by 77416 on 2018/1/28.
 */

public class Login{
    private boolean loginState=false;
    public void loginByPassword(String userName,String password){
        AVUser.logInInBackground(userName, password, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if(e==null){
                    //登陆成功
                    loginState=true;
                }else{
                    //登陆失败
                }
            }
        });
    }
    public boolean getLoginState(){
        return loginState;
    }
}
