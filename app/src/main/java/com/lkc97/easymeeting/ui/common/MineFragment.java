package com.lkc97.easymeeting.ui.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVUser;
import com.bumptech.glide.Glide;
import com.lkc97.easymeeting.R;
import com.lkc97.easymeeting.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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
    private TextView getQRcodeBtn;
    private Button lotOutBtn;
    private TextView username;
    private TextView emailaddress;
    private CircleImageView circleImageView;
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
            //Toast.makeText(getActivity(), "补充搜索界面",Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.toolbar_chat) {
            Toast.makeText(getActivity(), "正在加载聊天界面",Toast.LENGTH_SHORT).show();
            MainActivity mainActivity=(MainActivity)getActivity();
            mainActivity.openBuddyActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, container, false);
        circleImageView=(CircleImageView)view.findViewById(R.id.user_img);
        getQRcodeBtn=(TextView) view.findViewById(R.id.get_qrcode_btn);
        lotOutBtn=(Button)view.findViewById(R.id.logout_btn);
        username=(TextView)view.findViewById(R.id.username);
        emailaddress=(TextView)view.findViewById(R.id.email_box);
        username.setText(AVUser.getCurrentUser().getUsername());
        emailaddress.setText(AVUser.getCurrentUser().getEmail());
        //add toolbar
        Glide.with(view.getContext()).load(AVUser.getCurrentUser().getAVFile("avatar").getUrl()).into(circleImageView);
        mToolbarMine = (Toolbar) view.findViewById(R.id.frag_mine_toolbar);
        mToolbarMine.setTitle("我的");
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbarMine);


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
