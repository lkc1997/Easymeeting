package com.lkc97.easymeeting.ui.adapter;

/**
 * Created by admin on 2018/3/24.
 */

public class ConfListBean {
    private String name;
    private String state;

    public ConfListBean(String name,String state){
        this.name=name;
        this.state=state;
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

}
