package com.lkc97.easymeeting.ui.common;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FollowCallback;
import com.lkc97.easymeeting.R;
import com.lkc97.easymeeting.ui.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MineFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MineFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;
    private Button testBtn;
    private Button buddyListBtn;
    private Button getQRcodeBtn;
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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, container, false);
        buddyListBtn=(Button)view.findViewById(R.id.buddy_list_mine_fragment);
        testBtn=(Button)view.findViewById(R.id.test);
        getQRcodeBtn=(Button)view.findViewById(R.id.get_qrcode_btn);
        buddyListBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                MainActivity mainActivity=(MainActivity)getActivity();
                mainActivity.openBuddyActivity();
            }
        });
        testBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                /*AVUser.getCurrentUser().followInBackground("5ab525f6756571108cdc1c29", new FollowCallback() {
                    @Override
                    public void done(AVObject object, AVException e) {
                        if (e == null) {
                            Log.d("TEST", "follow succeeded.");
                        } else if (e.getCode() == AVException.DUPLICATE_VALUE) {
                            Log.d("TEST", "Already followed.");
                        }
                        else
                            Log.d("TEST", e.getMessage());
                    }
                });*/
            }
        });
        getQRcodeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                MainActivity mainActivity=(MainActivity)getActivity();
                mainActivity.openQRcodeActivity();
            }
        });
        return view;
    }


}
