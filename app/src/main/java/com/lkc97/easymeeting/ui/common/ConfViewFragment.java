package com.lkc97.easymeeting.ui.common;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.lkc97.easymeeting.R;
import com.lkc97.easymeeting.data.callback.ReloadConfCallBack;
import com.lkc97.easymeeting.ui.MainActivity;
import com.lkc97.easymeeting.ui.adapter.ConfBean;
import com.lkc97.easymeeting.ui.adapter.ConfViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/3/3.
 */

public class ConfViewFragment extends Fragment {
    private Toolbar mToolbarConfView;
    private View conf_frag;
    private RecyclerView conf_recv;
    private List<ConfBean> dataList = new ArrayList<>();
    private Context context;
    private ConfViewAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Boolean confLoadState=false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.confview_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    /*给toolbar右上角Item添加选择效果,或通过实现接口完成监听器*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.toolbar_search) {
            Toast.makeText(getActivity(), "补充搜索界面",Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.toolbar_chat) {
            Toast.makeText(getActivity(), "跳转到聊天界面",Toast.LENGTH_SHORT).show();
            MainActivity mainActivity=(MainActivity)getActivity();
            mainActivity.openBuddyActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        conf_frag = inflater.inflate(R.layout.fragment_conf_view, container, false);
        swipeRefreshLayout=(SwipeRefreshLayout)conf_frag.findViewById(R.id.conf_view_swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                //mData.add(0, "嘿，我是“下拉刷新”生出来的");
                    dataList.clear();
                    reloadConf(new ReloadConfCallBack() {
                        @Override
                        public void reloadConf() {
                            adapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    });
            }
        });
        innitView();
        showData();
        context=conf_frag.getContext();
        //add toolbar
        mToolbarConfView = (Toolbar) conf_frag.findViewById(R.id.frag_conf_view_toolbar);
        mToolbarConfView.setTitle("推荐");
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbarConfView);
        return conf_frag;
    }

    private void innitView() {
        conf_recv = (RecyclerView) conf_frag.findViewById(R.id.conf_view_fragment_conf_view);
        GridLayoutManager layoutManager = new GridLayoutManager(context,2);
        conf_recv.setLayoutManager(layoutManager);
    }

    private void showData() {
        if(!confLoadState) {
            loadConf();
            confLoadState=true;
        }
    }
    private void loadConf(){
        AVQuery<AVObject> query = new AVQuery<>("Conference");
        query.whereEqualTo("checkState", true);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                //Log.d("Easymeeting","confList size ="+list.size());
                AVObject conference;
                AVFile file;
                if(list.size()>0) {
                    for (int i = 0; i < list.size(); i++) {
                        conference = list.get(i);
                        file = conference.getAVFile("image");
                        dataList.add(new ConfBean(conference.getString("confName"), file.getUrl(), conference));
                    }
                }
                adapter=new ConfViewAdapter(conf_frag.getContext(),dataList);
                conf_recv.setAdapter(adapter);
            }
        });
    }
    public void reloadConf(final ReloadConfCallBack reloadConfCallBack){
        AVQuery<AVObject> query = new AVQuery<>("Conference");
        query.whereEqualTo("checkState", true);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                AVObject conference;
                AVFile file;
                for(int i=0;i<list.size();i++){
                    conference=list.get(i);
                    file=conference.getAVFile("image");
                    if(file==null)
                        dataList.add(new ConfBean(conference.getString("confName"),
                                "http://lc-gls5n1w7.cn-n1.lcfile.com/d9d5450110185ffc607e.jpg",conference));
                    else
                        dataList.add(new ConfBean(conference.getString("confName"),file.getUrl(),conference));
                }
                reloadConfCallBack.reloadConf();
            }
        });
    }
}
