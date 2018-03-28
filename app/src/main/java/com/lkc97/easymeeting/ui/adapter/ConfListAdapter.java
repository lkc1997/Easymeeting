package com.lkc97.easymeeting.ui.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lkc97.easymeeting.R;

import java.util.List;

/**
 * Created by admin on 2018/3/24.
 */

public class ConfListAdapter extends RecyclerView.Adapter<ConfListAdapter.ConfListViewHolder>{
    private Context context;
    private List<ConfListBean> dataList;

    static class ConfListViewHolder extends RecyclerView.ViewHolder {
        View confListView;
        TextView confName;
        ImageView confImage;
        TextView confState;
        Button  quitBtn;

        public ConfListViewHolder(View itemView) {
            super(itemView);
            confListView=itemView;
            confName = (TextView) itemView.findViewById(R.id.conf_list_name);
            confState = (TextView) itemView.findViewById(R.id.conf_list_state);
            quitBtn=(Button)itemView.findViewById(R.id.conf_list_quit_btn);
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
                Toast.makeText(v.getContext(),"退出会议",Toast.LENGTH_SHORT).show();
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
}
