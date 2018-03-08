package com.lkc97.easymeeting.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lkc97.easymeeting.R;

import java.util.List;

/**
 * Created by admin on 2018/3/3.
 */

public class ConfAdapter extends RecyclerView.Adapter<ConfAdapter.ConfViewHolder>{

    private Context context;
    private List<ConfBean> dataList;

    static class ConfViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView confName;
        ImageView confImage;
        TextView content;

        public ConfViewHolder(View itemView) {

            super(itemView);
            cardView = (CardView) itemView;
            confName = (TextView) itemView.findViewById(R.id.conf_name);
            confImage = (ImageView) itemView.findViewById(R.id.conf_image);
            content = (TextView) itemView.findViewById(R.id.conf_content);

        }
    }

    public ConfAdapter(Context context, List<ConfBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public ConfAdapter.ConfViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(context == null){
            context = parent.getContext();
        }
        return new ConfAdapter.ConfViewHolder(LayoutInflater.from(context).inflate(R.layout.conf_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ConfAdapter.ConfViewHolder holder, int position) {
        ConfBean confBean = dataList.get(position);
        holder.confName.setText(confBean.getName());
        holder.content.setText(dataList.get(position).getContent());
        Glide.with(context).load(confBean.getImageId()).into(holder.confImage);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}