package com.lkc97.easymeeting.ui.common;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.lkc97.easymeeting.R;
import com.lkc97.easymeeting.ui.adapter.ConfListAdapter;
import com.lkc97.easymeeting.ui.adapter.ConfListBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ConfListActivity extends AppCompatActivity {

    private RecyclerView conf_list_recv;
    private List<ConfListBean> dataList = new ArrayList<>();
    private Context context;
    private ConfListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf_list);
        innitView();
        loadFollowedConference();
        conf_list_recv.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST,R.drawable.divider));
    }

    private void innitView() {
        conf_list_recv = (RecyclerView)findViewById(R.id.conf_list_recv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        conf_list_recv.setLayoutManager(layoutManager);
    }

    private void loadFollowedConference(){
        final Date data=new Date();
        AVQuery<AVObject> query = new AVQuery<>("FollowedConference");
        query.whereEqualTo("follower", AVUser.getCurrentUser());
        query.include("conference");
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                int interval;
                AVObject conference=null;
                String state;
                //Log.d("Easymeeting",""+list.get(0).getAVObject("conference").getString("confName"));
                for(AVObject followedConference:list){
                    conference=followedConference.getAVObject("conference");
                    //Log.d("Easymeeting",""+conference.getDate("createdAt"));
                    interval=data.compareTo(conference.getDate("createdAt"));
                    if(interval>0)
                        state=interval+"天后";
                    else if(interval==0)
                        state="今日开始";
                    else
                        state="已过期";
                    dataList.add(new ConfListBean(conference.getString("confName"),state,conference));
                }
                adapter=new ConfListAdapter(dataList);
                conf_list_recv.setAdapter(adapter);
            }
        });
    }
}
