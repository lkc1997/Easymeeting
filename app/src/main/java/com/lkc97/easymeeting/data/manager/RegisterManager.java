package com.lkc97.easymeeting.data.manager;

import com.lkc97.easymeeting.data.network.Register;

/**
 * Created by 77416 on 2018/2/11.
 */

public class RegisterManager {

    public boolean registerByPassword(String userName,String password,String emailAddress){
        Register mRegister=new Register();
        return mRegister.registerByPassword(userName,password,emailAddress);
    }
}
