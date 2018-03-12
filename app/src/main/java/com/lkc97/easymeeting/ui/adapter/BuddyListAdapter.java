package com.lkc97.easymeeting.ui.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.lkc97.easymeeting.R;

import java.util.List;

/**
 * Created by 77416 on 2018/3/11.
 */

public class BuddyListAdapter extends ArrayAdapter <Buddy>{
    private int resourceId;
    public BuddyListAdapter(Context context,int textViewResourceId,List<Buddy> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        Buddy mBuddy=getItem(position);//获取当前项的Buddy实例
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView buddyAvatar=(ImageView)view.findViewById(R.id.buddy_avatar);
        buddyAvatar.setImageResource(mBuddy.getAvatarId());
        return view;
    }
}
