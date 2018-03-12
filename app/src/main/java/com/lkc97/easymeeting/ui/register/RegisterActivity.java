package com.lkc97.easymeeting.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SignUpCallback;
import com.lkc97.easymeeting.R;
import com.lkc97.easymeeting.ui.MainActivity;

public class RegisterActivity extends AppCompatActivity {
    private  boolean registerState=false;
    private EditText usernamerEdittxt=null;
    private EditText passwordEdittext=null;
    private EditText emailAddress=null;
    private Button registerBtn=null;
    private Handler registerHandler=new Handler(){
      public void handleMessage(Message msg){
          switch(msg.what){
              case 1:
                  loginByPassword(usernamerEdittxt.getText().toString().trim(),passwordEdittext.getText().toString().trim());
                  break;
              case -1:
                  Toast.makeText(getApplicationContext(), "用户名已存在或请求出错",
                          Toast.LENGTH_SHORT).show();
                  break;
              default:
                  break;
          }
      }
    };
    private Handler loginHandler=new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    Intent loginIntent=new Intent(RegisterActivity.this, MainActivity.class);
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
        setContentView(R.layout.activity_register);
        usernamerEdittxt=(EditText)findViewById(R.id.username_edittxt);
        passwordEdittext=(EditText)findViewById(R.id.password_edittxt);
        emailAddress=(EditText)findViewById(R.id.email_address_exittext);
        registerBtn=(Button) findViewById(R.id.register_btn);
    }
    public void registerOnclick(View v){
        String sUsename=usernamerEdittxt.getText().toString().trim();
        String sPassword=passwordEdittext.getText().toString().trim();
        String sEmailAddress=emailAddress.getText().toString().trim();
        if("".equals(sUsename)||"".equals(sPassword)||"".equals(sEmailAddress)) {
            Toast.makeText(getApplicationContext(), "请正确填写注册信息",
                    Toast.LENGTH_SHORT).show();
        }
        else
            registerByPassword(sUsename,sPassword,sEmailAddress);
    }
    public void registerByPassword(String userName,String password,String emailAddress){
        AVUser user = new AVUser();// 新建 AVUser 对象实例
        user.setUsername(userName);// 设置用户名
        user.setPassword(password);// 设置密码
        user.setEmail(emailAddress);// 设置邮箱
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                Message message=new Message();
                if (e == null) {
                    // 注册成功
                    Log.d("EasymeetingLog","注册成功");
                    message.what=1;
                    registerHandler.sendMessage(message);
                } else {
                    // 失败的原因可能有多种，常见的是用户名已经存在。
                    Log.d("EasymeetingLog","注册失败");
                    message.what=-1;
                    registerHandler.sendMessage(message);
                }
            }
        });
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
                loginHandler.sendMessage(message);
            }
        });
    }
}
