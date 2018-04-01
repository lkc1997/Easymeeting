package com.lkc97.easymeeting.ui.register;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.lkc97.easymeeting.R;
import com.lkc97.easymeeting.ui.MainActivity;

import java.io.IOException;

import static com.lkc97.easymeeting.ui.common.ConfCreateActivity.CHOOSE_PHOTO;

public class AvatarActivity extends AppCompatActivity {
    private String imageAbsolutePath=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
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
                String selection= MediaStore.Images.Media._ID+"="+id;
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

    public void selectAvatar(View v){
        findImage();
    }
    public void sendAvatar(View v) {
        AVFile confImage = null;
        AVUser currentUser=AVUser.getCurrentUser();
        if (imageAbsolutePath == null) {
            Toast.makeText(getApplicationContext(), "请选择文件",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            try {
                confImage = AVFile.withAbsoluteLocalPath(AVUser.getCurrentUser().getUsername() + "_avatar" + ".jpg", imageAbsolutePath);
            } catch (IOException e) {
                //Log.e("Easymeeting",e.getMessage());
                e.printStackTrace();
            }
        }
        currentUser.put("avatar",confImage);
        currentUser.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                Intent AvatarIntent=new Intent(AvatarActivity.this, MainActivity.class);
                //销毁当前活动，让打开的活动无法返回当前活动
                AvatarIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(AvatarIntent);
            }
        });
    }
}
