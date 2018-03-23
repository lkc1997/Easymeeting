package com.lkc97.easymeeting.ui.common;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVObject;
import com.bigkoo.pickerview.TimePickerView;
import com.lkc97.easymeeting.R;
import com.lkc97.easymeeting.ui.MainActivity;

import java.io.File;
import java.net.URI;
import java.util.Date;

public class ConfCreateActivity extends AppCompatActivity {
    public static final int CHOOSE_PHOTO=2;
    File imageSelected;
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
    public void selectConfImage(View v){
        findImage();
    }
    public void findImage(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent,CHOOSE_PHOTO);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CHOOSE_PHOTO) {
                Uri uri = data.getData();
                imageSelected = new File(uri.toString());
                Toast.makeText(this, "选择图片：" + imageSelected.getName(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
