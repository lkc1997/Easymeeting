package com.lkc97.easymeeting.ui.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lkc97.easymeeting.R;
import com.lkc97.easymeeting.data.network.Login;
import com.lkc97.easymeeting.data.network.Register;
import com.lkc97.easymeeting.ui.MainActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText usernamerEdittxt=null;
    EditText passwordEdittext=null;
    EditText emailAddress=null;
    Button registerBtn=null;
    Register mRegister=new Register();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernamerEdittxt=(EditText)findViewById(R.id.username_edittxt);
        passwordEdittext=(EditText)findViewById(R.id.password_edittxt);
        emailAddress=(EditText)findViewById(R.id.email_address_exittext);
        registerBtn=(Button)findViewById(R.id.register_btn);
    }

    public void registerOnclick(View v){
        String sUsename=usernamerEdittxt.getText().toString().trim();
        String sPassword=passwordEdittext.getText().toString().trim();
        String sEmailAddress=passwordEdittext.getText().toString();
        if("".equals(sUsename)||"".equals(sPassword)||"".equals(sEmailAddress)) {
            Toast.makeText(getApplicationContext(), "请正确填写注册信息",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if(mRegister.registerByPassword(sUsename,sPassword,sEmailAddress)){
            Login mLogin=new Login(sUsename,sPassword);
            mLogin.loginByPassword();
            Intent loginIntent=new Intent(this, MainActivity.class);
            //销毁当前活动，让打开的活动无法返回当前活动
            loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(loginIntent);
        }
        else{
            Toast.makeText(getApplicationContext(), "注册失败,用户名已存在或请求出错",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
