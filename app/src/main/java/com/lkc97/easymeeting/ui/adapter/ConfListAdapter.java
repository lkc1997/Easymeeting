package com.lkc97.easymeeting.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.DeleteCallback;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.im.v2.AVIMChatRoom;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;
import com.lkc97.easymeeting.R;
import com.lkc97.easymeeting.ui.common.ConfDetailActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.utils.LCIMConstants;
import info.hoang8f.widget.FButton;

/**
 * Created by admin on 2018/3/24.
 */

public class ConfListAdapter extends RecyclerView.Adapter<ConfListAdapter.ConfListViewHolder>{
    private Context context;
    private List<ConfListBean> dataList;
    private List<String> idList=new ArrayList<>();
    static class ConfListViewHolder extends RecyclerView.ViewHolder {
        View confListView;
        FontTextView confName;
        TextView confState;
        FButton  quitBtn;
        public ConfListViewHolder(View itemView) {
            super(itemView);
            confListView=itemView;
            confName = (FontTextView) itemView.findViewById(R.id.conf_list_name);
            confState = (TextView) itemView.findViewById(R.id.conf_list_state);
            quitBtn=(FButton) itemView.findViewById(R.id.conf_list_quit_btn);
        }
    }

    public ConfListAdapter(List<ConfListBean> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ConfListAdapter.ConfListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(context == null){
            context = parent.getContext();
        }
        View view=LayoutInflater.from(context).inflate(R.layout.conf_list_item, parent, false);
        final ConfListViewHolder holder=new ConfListViewHolder(view);
        holder.quitBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final int position=holder.getAdapterPosition();
                final ConfListBean conference=dataList.get(position);
                new AlertDialog.Builder(context)
                        .setTitle("确认")
                        .setMessage("确定要退出吗？")
                        .setNegativeButton("否", null)
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AVQuery<AVObject> query = new AVQuery<>("FollowedConference");
                                query.whereEqualTo("conference", conference.getConference());
                                query.findInBackground(new FindCallback<AVObject>() {
                                    @Override
                                    public void done(List<AVObject> list, AVException e) {
                                        list.get(0).deleteInBackground(new DeleteCallback() {
                                            @Override
                                            public void done(AVException e) {
                                                if(e==null)
                                                    //Log.d("Easymeeting","成功删除");
                                                    removeData(position);
                                                else
                                                    ;//Log.e("Easymeeting",e.getMessage());
                                            }
                                        });
                                    }
                                });
                            }
                        })
                        .show();
            }
        });
        holder.confListView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position=holder.getAdapterPosition();
                AVObject conf=dataList.get(position).getConference();
                Intent intent=new Intent(context, ConfDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("objectId",conf.getObjectId());
                Log.d("Easymeeting",conf.getObjectId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.confState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position=holder.getAdapterPosition();
                final ConfListBean conference=dataList.get(position);
                Calendar calendar = Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH)+1;//未知错误
                int day=calendar.get(Calendar.DATE);
                String startTime=conference.getConference().getString("date");
                String[] data=startTime.split("-");
                if(Integer.parseInt(data[0])==year&&Integer.parseInt(data[1])==month&&Integer.parseInt(data[2])==day){
                    AVQuery<AVObject> query = new AVQuery<>("Conference");
                    query.whereEqualTo("objectId",conference.getConference().getObjectId());
                    query.findInBackground(new FindCallback<AVObject>() {
                        @Override
                        public void done(List<AVObject> list, AVException e) {
                            AVQuery<AVObject> query = new AVQuery<>("FollowedConference");
                            query.whereEqualTo("conference", list.get(0));
                            query.include("follower");
                            query.findInBackground(new FindCallback<AVObject>() {
                                @Override
                                public void done(List<AVObject> list, AVException e) {
                                    for(AVObject followedConference:list){
                                        idList.add(followedConference.getAVUser("follower").getUsername());
                                    }
                                    LCChatKit.getInstance().getClient().createChatRoom(
                                            idList, conference.getConference().getString("confName"), null, true, new AVIMConversationCreatedCallback() {
                                                @Override
                                                public void done(AVIMConversation avimConversation, AVIMException e) {
                                                    if (avimConversation instanceof AVIMChatRoom) {
                                                        Intent intent = new Intent(context, LCIMConversationActivity.class);
                                                        intent.putExtra(LCIMConstants.CONVERSATION_ID, avimConversation.getConversationId());
                                                        context.startActivity(intent);
                                                    } else {
                                                        Log.e("Easymeeting", e.getMessage());
                                                    }
                                                }
                                            });

                                }
                            });
                        }
                    });
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ConfListAdapter.ConfListViewHolder holder, int position) {
        ConfListBean conflistBean = dataList.get(position);
        holder.confName.setText(conflistBean.getName());
        holder.confState.setText(dataList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    //删除当前项
    public void removeData(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
        notifyItemRemoved(position);
        if(position != getItemCount()) {
            notifyItemRangeChanged(position, getItemCount());
        }
    }
}
