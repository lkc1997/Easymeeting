package com.lkc97.easymeeting.ui.common;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;
import com.bigkoo.pickerview.TimePickerView;
import com.lkc97.easymeeting.R;
import com.lkc97.easymeeting.ui.MainActivity;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;

public class ConfCreateActivity extends AppCompatActivity {
    public static final int CHOOSE_PHOTO=2;
    File imageSelected=null;
    AVFile confImage=null;
    String imageAbsolutePath=null;
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
        else if(imageAbsolutePath==null){
            Toast.makeText(getApplicationContext(), "请选择文件",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            AVObject conference = new AVObject("Conference");// 构建对象
            try {
                confImage= AVFile.withAbsoluteLocalPath(sConfName+".jpg", imageAbsolutePath);
            }catch(IOException e){
                //Log.e("Easymeeting",e.getMessage());
                e.printStackTrace();
            }
            conference.put("confName", sConfName);
            conference.put("confPlace", sConfPlace);
            conference.put("confTime", sConfTime);
            conference.put("image", confImage);
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
        intent.setType("image/*");//设置类型，任意后缀的可以这样写。
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent,CHOOSE_PHOTO);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case CHOOSE_PHOTO:
                if(resultCode==RESULT_OK){
                    if(Build.VERSION.SDK_INT>=19){
                        handleImageOnKitKat(data);
                    }
                    else{
                        handleImageBeforeKitKat(data);
                    }
                }
                Toast.makeText(getApplicationContext(), "选择文件"+imageAbsolutePath,
                        Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data){
        String imagePath=null;
        Uri uri=data.getData();
        if(DocumentsContract.isDocumentUri(this,uri)){
            String docId=DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                //如果是document类型的Uri
                String id=docId.split(":")[1];
                String selection=MediaStore.Images.Media._ID+"="+id;
                imagePath=getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }
            else if("com.android.providers.download.documents".equals(uri.getAuthority())){
                Uri contentUri= ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath=getImagePath(contentUri,null);
            }
        }
        else if("content".equalsIgnoreCase(uri.getScheme())){
            //如果是content类型的Uri
            imagePath=getImagePath(uri,null);
        }
        else if("file".equalsIgnoreCase(uri.getScheme())){
            //如果是file类型的Uri
            imagePath=uri.getPath();
        }
        imageAbsolutePath=imagePath;
    }
    private void handleImageBeforeKitKat(Intent data){
        Uri uri=data.getData();
        String imagePath=getImagePath(uri,null);
        imageAbsolutePath=imagePath;
    }
    private String getImagePath(Uri uri,String selection){
        String path=null;
        //通过Uri和selection来获取真实的图片路径
        Cursor cursor=getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
}
