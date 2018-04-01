package com.lkc97.easymeeting.data.network;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.lkc97.easymeeting.data.callback.BuddyListCallBack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 77416 on 2018/3/11.
 */

public class BuddyListNW {
    public static boolean buddyListState=false;
    private String userName;
    private AVQuery<AVObject> query = new AVQuery<>("_Follower");
    private AVQuery<AVUser> followerQuery;
    public void getBuddyList(final BuddyListCallBack buddyListCallBack){
            try {
                followerQuery = AVUser.getCurrentUser().followerQuery(AVUser.class);
                followerQuery.include("follower");
            } catch (AVException e) {
                Log.e("Easymeeting", e.getMessage());
            }
            followerQuery.findInBackground(new FindCallback<AVUser>() {
                @Override
                public void done(List<AVUser> avObjects, AVException avException) {
                    // avObjects 包含了 userA 的粉丝列表
                    if(avObjects.size()==0)
                        Log.d("Easymeeting", "未找到好友");
                    buddyListCallBack.receiveBuddyList(avObjects);

                }
            });
        }
}
