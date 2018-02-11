package com.lkc97.easymeeting.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lkc97.easymeeting.R;
import com.lkc97.easymeeting.data.manager.LoginManager;
import com.lkc97.easymeeting.ui.MainActivity;
import com.lkc97.easymeeting.ui.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    EditText usernamerEdittxt=null;
    EditText passwordEdittext=null;
    Button loginBtn=null;
    Button registerBtn=null;
    LoginManager mLoginManager=new LoginManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernamerEdittxt=(EditText)findViewById(R.id.username_edittxt);
        passwordEdittext=(EditText)findViewById(R.id.password_edittxt);
        loginBtn=(Button)findViewById(R.id.login_btn);
        registerBtn=(Button)findViewById(R.id.register_btn);
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.login_btn:
                loginBtn();
                break;
            case R.id.register_btn:
                registerBtn();
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
        if(mLoginManager.loginByPassword(usernamerEdittxt.getText().toString(),passwordEdittext.getText().toString())){
            Intent loginIntent=new Intent(LoginActivity.this, MainActivity.class);
            //销毁当前活动，让打开的活动无法返回当前活动
            loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(loginIntent);
        }
        else
            Toast.makeText(getApplicationContext(), "用户名或密码错误",
                    Toast.LENGTH_SHORT).show();
    }
    public void registerBtn(){
        Intent registerIntent=new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(registerIntent);
    }
}
