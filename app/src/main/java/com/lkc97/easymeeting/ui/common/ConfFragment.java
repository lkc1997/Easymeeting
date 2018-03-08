package com.lkc97.easymeeting.ui.common;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lkc97.easymeeting.R;
import com.lkc97.easymeeting.ui.adapter.ConfAdapter;
import com.lkc97.easymeeting.ui.adapter.ConfBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by admin on 2018/3/3.
 */

public class ConfFragment extends Fragment {

    private View conf_frag;
    private RecyclerView conf_recv;
    private List<ConfBean> dataList = new ArrayList<>();
    private Context context;
    private ConfAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        conf_frag = inflater.inflate(R.layout.fragment_conf, container, false);
        innitView();
        showData();
        context=conf_frag.getContext();
        return conf_frag;
    }

    private void innitView() {
        conf_recv = (RecyclerView) conf_frag.findViewById(R.id.conf_recv);
        GridLayoutManager layoutManager = new GridLayoutManager(context,2);
        conf_recv.setLayoutManager(layoutManager);
    }

    private void showData() {
        ConfBean[] confBean ={
                new ConfBean("会议1",R.drawable.test,"会议简介"),
                new ConfBean("会议2",R.drawable.test,"会议简介"),
                new ConfBean("会议3",R.drawable.test,"会议简介"),
                new ConfBean("会议4",R.drawable.test,"会议简介"),
                new ConfBean("会议5",R.drawable.test,"会议简介"),
                new ConfBean("会议6",R.drawable.test,"会议简介")
        };

        for (int i=0;i<100;i++){
            Random random = new Random();
            int index = random.nextInt(confBean.length);
            dataList.add(confBean[index]);
        }
        adapter=new ConfAdapter(conf_frag.getContext(),dataList);
        conf_recv.setAdapter(adapter);
    }

}
