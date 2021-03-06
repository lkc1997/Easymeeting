package com.lkc97.easymeeting.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.lkc97.easymeeting.R;
import com.lkc97.easymeeting.data.manager.LoginManager;
import com.lkc97.easymeeting.data.network.BuddyListNW;
import com.lkc97.easymeeting.ui.adapter.CustomUserProvider;
import com.lkc97.easymeeting.ui.common.BuddyActivity;
import com.lkc97.easymeeting.ui.common.ConfCreateActivity;
import com.lkc97.easymeeting.ui.common.ConfListActivity;
import com.lkc97.easymeeting.ui.common.ConfValueActivity;
import com.lkc97.easymeeting.ui.common.QRcodeActivity;
import com.lkc97.easymeeting.ui.login.LoginActivity;

import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.utils.LCIMConstants;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //动态申请权限
        verifyStoragePermissions(this);
        //判断是否有账户缓存
        LoginManager mLoginmanager=new LoginManager();
        if(!mLoginmanager.checkLoginState()){
            //无缓存，打开注册页面
            Intent loginIntent=new Intent(MainActivity.this, LoginActivity.class);
            //销毁当前活动，让打开的活动无法返回当前活动
            loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(loginIntent);
        }
        else{
            //加载好友列表
            if(!BuddyListNW.buddyListState) {
                CustomUserProvider.fetchBuddyList();
                BuddyListNW.buddyListState=true;
            }
        }
    }

    public void openBuddyActivity(){
        Intent loginIntent=new Intent(MainActivity.this, BuddyActivity.class);
        startActivity(loginIntent);
    }

    public void openClistActivity(){
        Intent loginIntent=new Intent(MainActivity.this, ConfListActivity.class);
        startActivity(loginIntent);
    }

    public void openCcreateActivity(){
        Intent loginIntent=new Intent(MainActivity.this, ConfCreateActivity.class);
        startActivity(loginIntent);
    }

    public void openCvalueActivity(){
        Intent loginIntent=new Intent(MainActivity.this, ConfValueActivity.class);
        startActivity(loginIntent);
    }
    public void openQRcodeActivity(){
        Intent QrcodeIntent=new Intent(MainActivity.this, QRcodeActivity.class);
        startActivity(QrcodeIntent);
    }
    public void openLoginActivity(){
        AVUser.logOut();
        Intent loginIntent=new Intent(MainActivity.this, LoginActivity.class);
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(loginIntent);
    }
    public void openCharRoomActivity(AVIMConversation avimConversation){
        Intent intent = new Intent(MainActivity.this, LCIMConversationActivity.class);
        intent.putExtra(LCIMConstants.CONVERSATION_ID, avimConversation.getConversationId());
        startActivity(intent);
    }
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };


    public static void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
