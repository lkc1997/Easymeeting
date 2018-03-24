package com.lkc97.easymeeting.ui.adapter;

/**
 * Created by admin on 2018/3/24.
 */

public class ConfListBean {
    private String name;
    private int imageId;
    private String state;

    public ConfListBean(String name,int imageId,String state){
        this.name=name;
        this.imageId=imageId;
        this.state=state;
    }

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }

    public String getContent() {
        return state;
    }

    public void setName(String name) {
        this.name = name;
    }

}
