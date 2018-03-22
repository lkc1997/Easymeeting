package com.lkc97.easymeeting.ui.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVObject;
import com.bigkoo.pickerview.TimePickerView;
import com.lkc97.easymeeting.R;

import java.util.Date;

public class ConfCreateActivity extends AppCompatActivity {
    private EditText confName;
    private EditText confPlace;
    private EditText confTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf_create);
        confName=(EditText)findViewById(R.id.conf_name_edtxt);
        confPlace=(EditText)findViewById(R.id.conf_place_edtxt);
        confTime=(EditText)findViewById(R.id.conf_time_edtxt);
    }
    //发送会议信息
    public void sendConfInfo(View v){
        String sConfName=confName.getText().toString().trim();
        String sConfPlace=confPlace.getText().toString().trim();
        String sConfTime=confTime.getText().toString().trim();
        if("".equals(sConfName)||"".equals(sConfPlace)||"".equals(sConfTime)) {
            Toast.makeText(getApplicationContext(), "请正确填写信息",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            AVObject conference = new AVObject("Conference");// 构建对象
            conference.put("confName", sConfName);
            conference.put("confPlace", sConfPlace);
            conference.put("confTime", sConfTime);
            conference.saveInBackground();// 保存到服务端
            Toast.makeText(getApplicationContext(), "成功申请会议",
                    Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
