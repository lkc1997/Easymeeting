package com.lkc97.easymeeting.data.network;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;

/**
 * Created by 77416 on 2018/2/11.
 */

public class Register {
    private boolean registerState=false;
    public boolean registerByPassword(String userName,String password,String emailAddress){
        AVUser user = new AVUser();// 新建 AVUser 对象实例
        user.setUsername(userName);// 设置用户名
        user.setPassword(password);// 设置密码
        user.setEmail(emailAddress);// 设置邮箱
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    // 注册成功
                    registerState=true;
                } else {
                    // 失败的原因可能有多种，常见的是用户名已经存在。
                }
            }
        });
        return registerState;
    }

}
