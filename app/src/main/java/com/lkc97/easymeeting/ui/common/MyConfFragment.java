package com.lkc97.easymeeting.ui.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
public class MyConfFragment extends Fragment {
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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private LoopViewPager viewPager;
    private CircleIndicator indicator;
    private TextView confList;
    private TextView confCreate;
    private TextView confValue;
    private View view;
    private MainActivity mainActivity;
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
        mainActivity=(MainActivity)getActivity();
        viewPager.setAdapter(new SamplePagerAdapter());
        indicator.setViewPager(viewPager);
        confList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.conf_list:
                        mainActivity.openClistActivity();
                        break;
                    default:
                        break;
                }
            }
        });
        confCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.conf_creat:
                        mainActivity.openCcreateActivity();
                        break;
                    default:
                        break;
                }
            }
        });
        confValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.conf_value:
                        mainActivity.openCvalueActivity();
                        break;
                    default:
                        break;
                }
            }
        });
        return view;
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
