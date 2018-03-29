package com.lkc97.easymeeting.ui.common;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.DeleteCallback;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.LogUtil;
import com.avos.avoscloud.SaveCallback;
import com.lkc97.easymeeting.R;

import java.util.List;

public class ConfDetailActivity extends AppCompatActivity {
    private AVObject conference;
    static{

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf_detail);
        /*取得来自B页面的数据，并显示到画面*/
        Bundle bundle = this.getIntent().getExtras();
            /*获取Bundle中的数据，注意类型和key*/
        String objectId = bundle.getString("objectId");
        AVQuery<AVObject> avQuery = new AVQuery<>("Conference");
        avQuery.getInBackground(objectId, new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                conference=avObject;
            }
        });
        //Log.d("Easymeeting",bundle.getString("objectId"));
    }
    public void confQuit(View v){
        new AlertDialog.Builder(this)
                .setTitle("确认")
                .setMessage("确定要退出吗？")
                .setNegativeButton("否", null)
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AVQuery<AVObject> query = new AVQuery<>("FollowedConference");
                        query.whereEqualTo("conference", conference);
                        query.findInBackground(new FindCallback<AVObject>() {
                            @Override
                            public void done(List<AVObject> list, AVException e) {
                                list.get(0).deleteInBackground(new DeleteCallback() {
                                    @Override
                                    public void done(AVException e) {
                                        if(e==null)
                                            Log.d("Easymeeting","成功删除");
                                        else
                                            Log.e("Easymeeting",e.getMessage());
                                    }
                                });
                            }
                        });
                    }
                })
                .show();
    }
    public void confParticipate(View v){
        AVObject followedCoference=new AVObject("FollowedConference");
        AVUser mine=AVUser.getCurrentUser();
        followedCoference.put("follower", mine);
        followedCoference.put("conference",conference);
        followedCoference.saveInBackground();
    }
}
