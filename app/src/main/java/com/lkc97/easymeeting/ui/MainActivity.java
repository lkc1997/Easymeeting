package com.lkc97.easymeeting.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.avos.avoscloud.AVUser;
import com.lkc97.easymeeting.R;
import com.lkc97.easymeeting.data.manager.LoginManager;
import com.lkc97.easymeeting.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginManager mLoginmanager=new LoginManager();
        if(!mLoginmanager.checkLoginState()){
            Intent loginIntent=new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        }
        /*
        // 测试 LCSDK 是否正常工作的代码
        AVObject testObject = new AVObject("TestObject");
        testObject.put("words","Hello World!");
        testObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if(e == null){
                    Log.d("saved","success!");
                }
            }
        });
        */
    }
}
