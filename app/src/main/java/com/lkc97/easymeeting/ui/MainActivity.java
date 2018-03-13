package com.lkc97.easymeeting.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lkc97.easymeeting.R;
import com.lkc97.easymeeting.data.manager.LoginManager;
import com.lkc97.easymeeting.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //判断是否有账户缓存
        LoginManager mLoginmanager=new LoginManager();
        if(!mLoginmanager.checkLoginState()){
            //无缓存，打开注册页面
            Intent loginIntent=new Intent(MainActivity.this, LoginActivity.class);
            //销毁当前活动，让打开的活动无法返回当前活动
            loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(loginIntent);
        }
    }
}
