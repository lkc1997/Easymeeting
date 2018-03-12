package com.lkc97.easymeeting.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.lkc97.easymeeting.R;
import com.lkc97.easymeeting.data.manager.LoginManager;
import com.lkc97.easymeeting.ui.common.BuddyListFragment;
import com.lkc97.easymeeting.ui.common.ConfFragment;
import com.lkc97.easymeeting.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {
//test
    private ConfFragment confFrag;
    private BuddyListFragment buddyListFragment;
    /*设置默认Fragment*/
    private void setDefaultFrag() {

        if (confFrag == null) {
            confFrag = new ConfFragment();
        }

        addFrag(confFrag);
        /*默认显示confFrag*/
        getSupportFragmentManager().beginTransaction().show(confFrag).commit();
    }

    /*添加Frag*/
    private void addFrag(Fragment frag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (frag != null && !frag.isAdded()) {
            ft.add(R.id.main_fragment, frag);
        }
        ft.commit();
    }

    /*隐藏所有fragment*/
    private void hideAllFrag() {
        hideFrag(confFrag);
        //hideFrag(topicFrag);
        //hideFrag(newconFrag);
        //hideFrag(friendFrag);
        //hideFrag(mineFrag);
    }

    /*隐藏frag*/
    private void hideFrag(Fragment frag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (frag != null && frag.isAdded()) {
            ft.hide(frag);
        }
        ft.commit();
    }

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

        /*BottomNavigationBar bottomNavigationBar = (BottomNavigationBar)findViewById(R.id.bottom_navigation_bar);//底部导航栏，fragment 不提供findViewById
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);//MODE_SHIFTING
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC//STYLE_RIPPLE
                );

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.conf, "发现").setActiveColorResource(R.color.material_orange_A200))
                .addItem(new BottomNavigationItem(R.drawable.topic, "话题").setActiveColorResource(R.color.material_green_500))
                .addItem(new BottomNavigationItem(R.drawable.newcon, "会议").setActiveColorResource(R.color.material_blue_A200))
                .addItem(new BottomNavigationItem(R.drawable.friend, "好友").setActiveColorResource(R.color.material_brown_500))
                .addItem(new BottomNavigationItem(R.drawable.mine, "我的信息").setActiveColorResource(R.color.material_blue_grey_500))
                .setFirstSelectedPosition(0)
                .initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                hideAllFrag();//先隐藏所有frag
                switch (position) {
                    case 0:
                        if (confFrag == null) {
                            confFrag = new ConfFragment();
                        }
                        addFrag(confFrag);
                        getSupportFragmentManager().beginTransaction().show(confFrag).commit();
                        getSupportActionBar().setTitle("会议推荐");
                        break;
                    case 3:
                        if (buddyListFragment == null) {
                            buddyListFragment = new BuddyListFragment();
                        }
                        addFrag(buddyListFragment);
                        getSupportFragmentManager().beginTransaction().show(buddyListFragment).commit();
                        getSupportActionBar().setTitle("好友");
                        break;
                }
             }
             @Override
             public void onTabUnselected(int position) {
             }
             @Override
             public void onTabReselected(int position) {
             }
        });*/

    }
}
