package com.lkc97.easymeeting.ui.common;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.avos.avoscloud.AVUser;
import com.lkc97.easymeeting.R;
import com.lkc97.easymeeting.util.QRCodeUtil;

public class QRcodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        ImageView mImageView = (ImageView) findViewById(R.id.qrcode);
        Bitmap mBitmap = QRCodeUtil.createQRCodeBitmap(AVUser.getCurrentUser().getUsername(), 480, 480); mImageView.setImageBitmap(mBitmap);


    }
}
