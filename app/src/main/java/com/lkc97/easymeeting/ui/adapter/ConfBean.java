package com.lkc97.easymeeting.ui.adapter;

/**
 * Created by admin on 2018/3/3.
 */

public class ConfBean {

    private String name;
    private int imageId;
    private String content;

    public ConfBean(String name,int imageId,String content){
        this.name=name;
        this.imageId=imageId;
        this.content=content;
    }

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }

    public String getContent() {
        return content;
    }

    public void setName(String name) {
        this.name = name;
    }

}
