package com.lkc97.easymeeting.data.manager;

import android.content.SharedPreferences;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.lkc97.easymeeting.data.network.Login;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by 77416 on 2018/1/28.
 */

public class LoginManager {
    public boolean loginByPassword(String userName,String password){
        Login mLogin=new Login();
        mLogin.loginByPassword(userName,password);
        //登陆成功返回true,否则返回false
        return mLogin.getLoginState();
    }
    public boolean checkLoginState(){
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            // 跳转到首页
            return true;
        } else {
            //缓存用户对象为空时，可打开用户注册界面
            return false;
        }
    }
}
