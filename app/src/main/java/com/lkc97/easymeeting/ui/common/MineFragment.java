package com.lkc97.easymeeting.ui.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.im.v2.AVIMChatRoom;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;
import com.lkc97.easymeeting.R;
import com.lkc97.easymeeting.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

import cn.leancloud.chatkit.LCChatKit;

public class MineFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Toolbar mToolbarMine;
    private View view;
    private Button testBtn;
    private Button buddyListBtn;
    private Button getQRcodeBtn;
    private Button lotOutBtn;
    private List<String> idList=new ArrayList<>();
    public MineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyConfFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyConfFragment newInstance(String param1, String param2) {
        MyConfFragment fragment = new MyConfFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.mine_toolbar, menu);
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
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, container, false);
        buddyListBtn=(Button)view.findViewById(R.id.buddy_list_mine_fragment);
        testBtn=(Button)view.findViewById(R.id.test);
        getQRcodeBtn=(Button)view.findViewById(R.id.get_qrcode_btn);
        lotOutBtn=(Button)view.findViewById(R.id.logout_btn);
        //add toolbar
        mToolbarMine = (Toolbar) view.findViewById(R.id.frag_mine_toolbar);
        mToolbarMine.setTitle("我的");
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbarMine);

        buddyListBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                MainActivity mainActivity=(MainActivity)getActivity();
                mainActivity.openBuddyActivity();
            }
        });
        idList.add("lkc");
        idList.add("thx");
        testBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AVQuery<AVObject> query = new AVQuery<>("Conference");
                query.whereEqualTo("objectId", "5abf56964773f7005d43b451");
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
                                                    MainActivity mainActivity=(MainActivity)getActivity();
                                                    mainActivity.openCharRoomActivity(avimConversation);

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
        });
        getQRcodeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                MainActivity mainActivity=(MainActivity)getActivity();
                mainActivity.openQRcodeActivity();
            }
        });
        lotOutBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                MainActivity mainActivity=(MainActivity)getActivity();
                mainActivity.openLoginActivity();
            }
        });
        return view;
    }


}
