package com.lkc97.easymeeting.ui.register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lkc97.easymeeting.R;
import com.lkc97.easymeeting.data.network.Register;

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

    public void registerOnclick(){
        if(mRegister.registerByPassword(usernamerEdittxt.getText().toString(),passwordEdittext.getText().toString(),emailAddress.getText().toString())){
            ;
        }
        else{
            Toast.makeText(getApplicationContext(), "注册失败,用户名已存在",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
