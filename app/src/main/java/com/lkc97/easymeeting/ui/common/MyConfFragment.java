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
import android.widget.TextView;
import android.widget.Toast;

import com.lkc97.easymeeting.R;
import com.lkc97.easymeeting.ui.MainActivity;
import com.lkc97.easymeeting.ui.adapter.LoopViewPager;
import com.lkc97.easymeeting.ui.adapter.SamplePagerAdapter;

import me.relex.circleindicator.CircleIndicator;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MyConfFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyConfFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyConfFragment() {
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

    private Toolbar mToolbarMyConf;
    private LoopViewPager viewPager;
    private CircleIndicator indicator;
    private TextView confList;
    private TextView confCreate;
    private TextView confValue;
    private View view;
    private MainActivity mainActivity;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.myconf_toolbar, menu);
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
        view = inflater.inflate(R.layout.fragment_my_conf, container, false);
        viewPager = (LoopViewPager)view.findViewById(R.id.viewpager);
        indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        confList = (TextView) view.findViewById(R.id.conf_list);
        confCreate = (TextView) view.findViewById(R.id.conf_creat);
        confValue = (TextView) view.findViewById(R.id.conf_value);
        //add toolbar
        mToolbarMyConf = (Toolbar) view.findViewById(R.id.frag_myconf_toolbar);
        mToolbarMyConf.setTitle("会议");
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbarMyConf);

        mainActivity=(MainActivity)getActivity();
        viewPager.setAdapter(new SamplePagerAdapter());
        indicator.setViewPager(viewPager);
        confList.setOnClickListener(this);
        confCreate.setOnClickListener(this);
        confValue.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.conf_list:
                mainActivity.openClistActivity();
                break;
            case R.id.conf_creat:
                mainActivity.openCcreateActivity();
                break;
            case R.id.conf_value:
                mainActivity.openCvalueActivity();
            default:
                break;
        }
    }

    private void selectAdvocator(){
        //点击广告进入页面
        int value = viewPager.getCurrentItem();
        switch (value){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }

}
