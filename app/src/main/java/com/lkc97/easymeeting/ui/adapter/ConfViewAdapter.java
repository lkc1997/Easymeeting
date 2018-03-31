package com.lkc97.easymeeting.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVObject;
import com.bumptech.glide.Glide;
import com.lkc97.easymeeting.R;
import com.lkc97.easymeeting.ui.common.ConfDetailActivity;

import java.util.List;

/**
 * Created by admin on 2018/3/3.
 */

public class ConfViewAdapter extends RecyclerView.Adapter<ConfViewAdapter.ConfViewHolder>{

    private Context context;
    private List<ConfBean> dataList;
    static class ConfViewHolder extends RecyclerView.ViewHolder {
        View confView;
        CardView cardView;
        TextView confName;
        ImageView confImage;
        TextView content;

        public ConfViewHolder(View itemView) {
            super(itemView);
            confView=itemView;
            cardView = (CardView) itemView;
            confName = (TextView) itemView.findViewById(R.id.conf_name);
            confImage = (ImageView) itemView.findViewById(R.id.conf_image);
            content = (TextView) itemView.findViewById(R.id.conf_content);

        }
    }

    public ConfViewAdapter(Context context, List<ConfBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public ConfViewAdapter.ConfViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.conf_item, parent, false);
        final ConfViewHolder holder=new ConfViewHolder(view);
        holder.confView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position=holder.getAdapterPosition();
                AVObject conf=dataList.get(position).getConference();
                Intent intent=new Intent(context, ConfDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("objectId",conf.getObjectId());
               // Log.d("Easymeeting",conf.getObjectId());
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });
        if(context == null){
            context = parent.getContext();
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(ConfViewAdapter.ConfViewHolder holder, int position) {
        ConfBean confBean = dataList.get(position);
        holder.confName.setText(confBean.getName());
        holder.content.setText(dataList.get(position).getContent());
        Glide.with(context).load(confBean.getImageUrl()).into(holder.confImage);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
