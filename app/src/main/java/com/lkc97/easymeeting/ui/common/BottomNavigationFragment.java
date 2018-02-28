package com.lkc97.easymeeting.ui.common;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.lkc97.easymeeting.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BottomNavigationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BottomNavigationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public BottomNavigationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BottomNavigationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BottomNavigationFragment newInstance(String param1, String param2) {
        BottomNavigationFragment fragment = new BottomNavigationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bottom_navigation,container,false);
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar)view.findViewById(R.id.bottom_navigation_bar);//底部导航栏，fragment 不提供findViewById

        bottomNavigationBar.setBarBackgroundColor(R.color.colorPrimaryDark);//应为背景颜色，实为选中颜色
        bottomNavigationBar.setInActiveColor(R.color.gray);//未选中时的颜色
        bottomNavigationBar.setActiveColor(R.color.white);//应为选中时的颜色，实为背景颜色

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.conf, "发现"))
                .addItem(new BottomNavigationItem(R.drawable.topic, "话题"))
                .addItem(new BottomNavigationItem(R.drawable.newcon, "会议"))
                .addItem(new BottomNavigationItem(R.drawable.friend, "好友"))
                .addItem(new BottomNavigationItem(R.drawable.mine, "我的"))
                .initialise();
        // Inflate the layout for this fragment

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
            }
            @Override
            public void onTabUnselected(int position) {
            }
            @Override
            public void onTabReselected(int position) {
            }
        });

        return view;
    }

}
