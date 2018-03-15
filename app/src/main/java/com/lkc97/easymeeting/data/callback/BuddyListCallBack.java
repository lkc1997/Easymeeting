package com.lkc97.easymeeting.data.callback;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;

import java.util.List;

/**
 * Created by 77416 on 2018/3/12.
 */

public interface BuddyListCallBack {
    void receiveBuddyList(List<AVUser> avObjects);
}
