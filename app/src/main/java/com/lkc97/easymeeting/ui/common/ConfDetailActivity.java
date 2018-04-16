package com.lkc97.easymeeting.ui.common;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.DeleteCallback;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetCallback;
import com.bumptech.glide.Glide;
import com.lkc97.easymeeting.R;

import org.w3c.dom.Text;

import java.util.List;

public class ConfDetailActivity extends AppCompatActivity {
    private AVObject conference;
    private TextView confName;
    private TextView confPlace;
    private TextView confTime;
    private ImageView confImage;
    private Button confParticipateBtn;
    private TextView confBrief;
    static{

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf_detail);
        confName=(TextView)findViewById(R.id.conf_name_detail_activity);
        confPlace=(TextView)findViewById(R.id.conf_place_detail_activity);
        confTime=(TextView)findViewById(R.id.conf_time_detail_activity);
        confImage=(ImageView)findViewById(R.id.conf_image_detail_activity);
        confParticipateBtn=(Button)findViewById(R.id.conf_participate_detail_activity_btn);
        confBrief=(TextView)findViewById(R.id.conf_brief);
        /*取得来自B页面的数据，并显示到画面*/
        Bundle bundle = this.getIntent().getExtras();
        /*获取Bundle中的数据，注意类型和key*/
        String objectId = bundle.getString("objectId");
        AVQuery<AVObject> avQuery = new AVQuery<>("Conference");
        avQuery.getInBackground(objectId, new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                conference=avObject;
                loadConfInfo();//加载会议信息
                checkParticipateState();//检查是否参与
            }
        });
    }
    //已弃用
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
        confParticipateBtn.setText("已参加");
        confParticipateBtn.setEnabled(false);
    }
    public void loadConfInfo(){
        confName.setText(conference.getString("confName"));
        confPlace.setText("地点:"+conference.getString("confPlace"));
        confTime.setText("日期:"+conference.getString("date"));
        confBrief.setText(conference.getString("briefIntroduction"));
        if(conference.getAVFile("image")==null)
            Glide.with(this.getApplicationContext()).load("http://lc-gls5n1w7.cn-n1.lcfile.com/d9d5450110185ffc607e.jpg").into(confImage);
        else
            Glide.with(this.getApplicationContext()).load(conference.getAVFile("image").getUrl()).into(confImage);
    }
    public void checkParticipateState(){
        AVQuery<AVObject> query = new AVQuery<>("FollowedConference");
        query.whereEqualTo("conference", conference);
        query.include("follower");
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                String followerName;
                for(AVObject followedConference:list){
                    followerName=followedConference.getAVUser("follower").getUsername();
                    if(followerName.equals(AVUser.getCurrentUser().getUsername())) {
                        confParticipateBtn.setText("已参加");
                        confParticipateBtn.setEnabled(false);
                    }
                }
            }
        });
    }
}
