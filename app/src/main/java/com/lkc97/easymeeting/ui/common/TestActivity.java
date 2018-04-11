package com.lkc97.easymeeting.ui.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.im.v2.AVIMChatRoom;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;
import com.lkc97.easymeeting.R;
import com.lkc97.easymeeting.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.utils.LCIMConstants;

public class TestActivity extends AppCompatActivity {
    private Button testBtn;
    private List<String> idList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        testBtn=(Button)findViewById(R.id.join_chat_btn);
        //获得上一活动传入的数据
        Bundle bundle = this.getIntent().getExtras();
        final String objectId = bundle.getString("objectId");
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idList.add("lkc");
                idList.add("thx");
                Log.d("Easymeeting", "群聊加载完毕");
                LCChatKit.getInstance().getClient().createChatRoom(
                        idList, getString(R.string.square), null, true, new AVIMConversationCreatedCallback() {
                            @Override
                            public void done(AVIMConversation avimConversation, AVIMException e) {
                                if (avimConversation instanceof AVIMChatRoom) {
                                    Intent intent = new Intent(TestActivity.this, LCIMConversationActivity.class);
                                    intent.putExtra(LCIMConstants.CONVERSATION_ID, avimConversation.getConversationId());
                                    startActivity(intent);
                                } else {
                                    Log.d("Easymeeting", e.getMessage());
                                }
                            }
                        });
                /*AVQuery<AVObject> query = new AVQuery<>("Conference");
                query.whereEqualTo("objectId", objectId);
                query.findInBackground(new FindCallback<AVObject>() {
                    @Override
                    public void done(List<AVObject> list, AVException e) {
                        AVQuery<AVObject> query = new AVQuery<>("FollowedConference");
                        query.whereEqualTo("conference", list.get(0));
                        query.include("follower");
                        query.findInBackground(new FindCallback<AVObject>() {
                            @Override
                            public void done(List<AVObject> list, AVException e) {
                                for(AVObject user:list){
                                    idList.add(user.getString("username"));
                                }
                                LCChatKit.getInstance().getClient().createChatRoom(
                                        idList, getString(R.string.square), null, true, new AVIMConversationCreatedCallback() {
                                            @Override
                                            public void done(AVIMConversation avimConversation, AVIMException e) {
                                                if (avimConversation instanceof AVIMChatRoom) {
                                                    Intent intent = new Intent(TestActivity.this, LCIMConversationActivity.class);
                                                    intent.putExtra(LCIMConstants.CONVERSATION_ID, avimConversation.getConversationId());
                                                    startActivity(intent);
                                                } else {
                                                    Log.e("Easymeeting", e.getMessage());
                                                }
                                            }
                                        });

                            }
                        });
                    }
                });

                */
            }
        });
    }

}
