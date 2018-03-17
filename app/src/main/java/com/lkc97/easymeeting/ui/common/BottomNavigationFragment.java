package com.lkc97.easymeeting.ui.common;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.lkc97.easymeeting.R;
import com.lkc97.easymeeting.ui.MainActivity;

import cn.leancloud.chatkit.activity.LCIMConversationListFragment;

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


    private ConfFragment confFrag;
    private ContactFragment contactFrag;
    /*设置默认Fragment*/
    private void setDefaultFrag() {

        if (confFrag == null) {
            confFrag = new ConfFragment();
        }

        addFrag(confFrag);
        /*默认显示confFrag*/
        getFragmentManager().beginTransaction().show(confFrag).commit();
    }

    /*添加Frag*/
    private void addFrag(Fragment frag) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (frag != null && !frag.isAdded()) {
            ft.add(R.id.main_fragment, frag);
        }
        else
            ft.replace(R.id.main_fragment,frag);
        ft.commit();
    }

    /*隐藏所有fragment*/
    private void hideAllFrag() {
        hideFrag(confFrag);
        //hideFrag(topicFrag);
        //hideFrag(newconFrag);
        hideFrag(contactFrag);
        //hideFrag(mineFrag);
    }

    /*隐藏frag*/
    private void hideFrag(Fragment frag) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (frag != null && frag.isAdded()) {
            ft.hide(frag);
        }
        ft.commit();
    }

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
        View view=inflater.inflate(R.layout.fragment_bottom_navigation,container,false);// Inflate the layout for this fragment
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar)view.findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);//MODE_SHIFTING
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC//STYLE_RIPPLE
                );

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.conf, "发现").setActiveColorResource(R.color.material_orange_A200))
                .addItem(new BottomNavigationItem(R.drawable.topic, "话题").setActiveColorResource(R.color.material_green_500))
                .addItem(new BottomNavigationItem(R.drawable.newcon, "会议").setActiveColorResource(R.color.material_blue_A200))
                .addItem(new BottomNavigationItem(R.drawable.friend, "好友").setActiveColorResource(R.color.material_brown_500))
                .addItem(new BottomNavigationItem(R.drawable.mine, "我").setActiveColorResource(R.color.material_blue_grey_500))
                .setFirstSelectedPosition(0)
                .initialise();
        setDefaultFrag();
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                hideAllFrag();//先隐藏所有frag
                switch (position) {
                    case 0:
                        hideAllFrag();
                        if (confFrag == null) {
                            confFrag = new ConfFragment();
                        }
                        addFrag(confFrag);
                        getFragmentManager().beginTransaction().show(confFrag).commit();
                        break;
                    case 3:
                        /*hideAllFrag();
                        if (contactFrag== null) {
                            contactFrag = new ContactFragment();
                        }
                        addFrag(contactFrag);
                        getFragmentManager().beginTransaction().show(contactFrag).commit();*/
                        MainActivity mainActivity=(MainActivity)getActivity();
                        mainActivity.openBuddyActivity();
                        break;
                }
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
