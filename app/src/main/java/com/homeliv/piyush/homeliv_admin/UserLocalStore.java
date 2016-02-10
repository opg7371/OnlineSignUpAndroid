package com.homeliv.piyush.homeliv_admin;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by piyush on 24/1/16.
 */
public class UserLocalStore {
    public static final String SP_NAME = "userdetails";
    static SharedPreferences userLocalDatabase;

    public  UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME,0);
    }

    public void storeUserData(Contact user) {
        SharedPreferences.Editor spEditor  = userLocalDatabase.edit();
        spEditor.putString("fname",user.fname);
        spEditor.putString("lname",user.lname);
        spEditor.putString("email",user.email);
        spEditor.putString("uname",user.uname);
        spEditor.putString("password",user.pass);
        spEditor.putString("mobile",user.mobile);
        spEditor.apply();
    }

    public static Contact getLoggedInUser() {
        String fname = userLocalDatabase.getString("fname", "a");
        String lname = userLocalDatabase.getString("lname","");
        String email = userLocalDatabase.getString("email","");
        String uname = userLocalDatabase.getString("uname","");
        String password = userLocalDatabase.getString("password","");
        String mobile = userLocalDatabase.getString("mobile","");


        return new Contact(fname,lname,email,uname,password,mobile);

    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("LoggedIn",loggedIn);
        spEditor.apply();
    }

    public boolean getUserLoggedIn() {
        if(userLocalDatabase.getBoolean("LoggedIn",false)==true) {
            return true;
        }
        else {
            return false;
        }
    }

    public void clearUserData( ) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.apply();
    }
}
