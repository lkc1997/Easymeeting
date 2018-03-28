package com.lkc97.easymeeting.ui.adapter;

import com.avos.avoscloud.AVObject;

/**
 * Created by admin on 2018/3/24.
 */

public class ConfListBean {
    private String name;
    private String state;
    private AVObject conference;
    public ConfListBean(String name,String state,AVObject conference){
        this.name=name;
        this.state=state;
        this.conference=conference;
    }

    public String getName(){
        return name;
    }

    public String getContent() {
        return state;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AVObject getConference(){
        return conference;
    }
}
