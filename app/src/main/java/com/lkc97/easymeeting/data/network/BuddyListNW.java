package com.lkc97.easymeeting.data.network;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.lkc97.easymeeting.ui.adapter.Buddy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 77416 on 2018/3/11.
 */

public class BuddyListNW {
    private String userName;
    AVQuery<AVObject> query = new AVQuery<>("BuddyList");
    List<AVObject> buddyList=new ArrayList<>();
    public BuddyListNW(String userName){
        this.userName=userName;
    }
    public BuddyListNW(){
        AVUser currentUser = AVUser.getCurrentUser();
        this.userName=currentUser.getUsername();
    }
    public void getBuddyNameList(){
        AVQuery<AVObject> buddyListQuery=query.whereEqualTo("uaerName",userName);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                buddyList=list;
            }
        });
    }
}
