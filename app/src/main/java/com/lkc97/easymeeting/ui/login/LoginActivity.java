package com.lkc97.easymeeting.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.lkc97.easymeeting.R;
import com.lkc97.easymeeting.data.manager.LoginManager;
import com.lkc97.easymeeting.ui.MainActivity;
import com.lkc97.easymeeting.ui.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView bingPicImg;
    private EditText usernamerEdittxt=null;
    private EditText passwordEdittext=null;
    private Button loginBtn=null;
    private TextView registerTouch=null;
    private LoginManager mLoginManager=new LoginManager();
    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    Intent loginIntent=new Intent(LoginActivity.this, MainActivity.class);
                    //销毁当前活动，让打开的活动无法返回当前活动
                    loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(loginIntent);
                    break;
                case -1:
                    Toast.makeText(getApplicationContext(), "用户名或密码错误",
                            Toast.LENGTH_SHORT).show();
                    break;
                default:break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernamerEdittxt=(EditText)findViewById(R.id.username_edittxt);
        passwordEdittext=(EditText)findViewById(R.id.password_edittxt);
        loginBtn=(Button)findViewById(R.id.login_btn);
        registerTouch=(TextView)findViewById(R.id.register_touch);
        loginBtn.setOnClickListener(this);
        registerTouch.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.login_btn:
                loginBtn();
                break;
            case R.id.register_touch:
                registerTouch();
                break;
        }
    }
    public void loginBtn(){
        //用户名或密码为空时
        if("".equals(usernamerEdittxt.getText().toString().trim())||"".equals(passwordEdittext.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "用户名或密码为空",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        loginByPassword(usernamerEdittxt.getText().toString().trim(),passwordEdittext.getText().toString().trim());
    }
    public void registerTouch(){
        Intent registerIntent=new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(registerIntent);
    }
    public void loginByPassword(String userName,String password){
        AVUser.logInInBackground(userName, password, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                Message message=new Message();
                if(e==null){
                    //登陆成功
                    message.what=1;
                    Log.d("EasymeetingLog","登陆成功,loginState=");
                }else{
                    //登陆失败
                    message.what=-1;
                    Log.d("EasymeetingLog","登陆失败,loginState=");
                }
                handler.sendMessage(message);
            }
        });
    }
}
