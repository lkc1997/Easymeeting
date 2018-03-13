package com.lkc97.easymeeting.ui.adapter;

/**
 * Created by 77416 on 2018/3/11.
 */

public class BuddyList {
    private int avatarId;
    private String accountName;
    public BuddyList(int avatarId, String accountName){
        this.avatarId=avatarId;
        this.accountName=accountName;
    }
    public String getAccountName(){
        return accountName;
    }
    public int getAvatarId(){
        return avatarId;
    }
}
