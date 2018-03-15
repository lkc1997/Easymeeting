package com.lkc97.easymeeting.ui.adapter;

/**
 * Created by 77416 on 2018/3/13.
 */


import android.util.Log;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.lkc97.easymeeting.data.callback.BuddyListCallBack;
import com.lkc97.easymeeting.data.manager.BuddyListManager;
import com.lkc97.easymeeting.data.network.BuddyListNW;

import java.util.ArrayList;
import java.util.List;

import cn.leancloud.chatkit.LCChatKitUser;
import cn.leancloud.chatkit.LCChatProfileProvider;
import cn.leancloud.chatkit.LCChatProfilesCallBack;

/**
 * Created by wli on 15/12/4.
 * 实现自定义用户体系
 */
public class CustomUserProvider implements LCChatProfileProvider {

    private static CustomUserProvider customUserProvider;

    public synchronized static CustomUserProvider getInstance() {
        if (null == customUserProvider) {
            customUserProvider = new CustomUserProvider();
        }
        return customUserProvider;
    }

    private CustomUserProvider() {
    }

    private static List<LCChatKitUser> partUsers = new ArrayList<LCChatKitUser>();

    static {
        /*BuddyListManager buddyListManager=new BuddyListManager();
        buddyListManager.receiveuddyList();
        List<AVObject> buddylist=buddyListManager.getBuddyList();
        AVObject buddy;
        for(int i = 0 ; i < buddylist.size() ; i++) {
            buddy=buddylist.get(i);
            partUsers.add(new LCChatKitUser(buddy.getString("userName"), buddy.getString("userName"), "http://www.avatarsdb.com/avatars/tom_and_jerry2.jpg"));
        }
        partUsers.add(new LCChatKitUser("Tom", "Tom", "http://www.avatarsdb.com/avatars/tom_and_jerry2.jpg"));
        partUsers.add(new LCChatKitUser("Jerry", "Jerry", "http://www.avatarsdb.com/avatars/jerry.jpg"));
        partUsers.add(new LCChatKitUser("Harry", "Harry", "http://www.avatarsdb.com/avatars/young_harry.jpg"));
        partUsers.add(new LCChatKitUser("William", "William", "http://www.avatarsdb.com/avatars/william_shakespeare.jpg"));
        partUsers.add(new LCChatKitUser("Bob", "Bob", "http://www.avatarsdb.com/avatars/bath_bob.jpg"));*/

        BuddyListNW buddyListNW=new BuddyListNW();
        buddyListNW.getBuddyList(new BuddyListCallBack() {
            @Override
            public void receiveBuddyList(List<AVUser> avObjects) {
                AVUser currentUser = AVUser.getCurrentUser();
                Log.d("Easymeeting","currentuser="+currentUser.getUsername());
                Log.d("Easymeeting","avObjects size="+avObjects.size());
                AVUser follower;
                for(int i = 0 ; i < avObjects.size() ; i++) {
                    follower=avObjects.get(i);
                    partUsers.add(new LCChatKitUser(follower.getUsername(), follower.getUsername(), "http://www.avatarsdb.com/avatars/tom_and_jerry2.jpg"));
                    Log.d("Easymeeting","list add "+avObjects.get(0).get("username"));
                }
            }
        });
    }
    @Override
    public void fetchProfiles(List<String> list, LCChatProfilesCallBack callBack) {
        List<LCChatKitUser> userList = new ArrayList<LCChatKitUser>();
        for (String userId : list) {
            for (LCChatKitUser user : partUsers) {
                if (user.getUserId().equals(userId)) {
                    userList.add(user);
                    break;
                }
            }
        }
        callBack.done(userList, null);
    }

    public List<LCChatKitUser> getAllUsers() {
        return partUsers;
    }
}
