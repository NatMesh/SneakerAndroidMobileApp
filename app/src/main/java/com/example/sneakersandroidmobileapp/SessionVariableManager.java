package com.example.sneakersandroidmobileapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionVariableManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String sharedPreferenceName = "session";
    String sessionKey = "sessionUser";

    public SessionVariableManager(Context context){
        sharedPreferences = context.getSharedPreferences(sharedPreferenceName, context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(UserModel userModel){
        //save session of user that is logged in
        int userId = userModel.getId();

        //This saves the unique id of the user into a pseudo session with shared preference
        editor.putInt(sessionKey, userId).commit();
    }


    public int getSession(){
        //returns user id who's session is currently saved
        return sharedPreferences.getInt(sessionKey, -1);
    }

    public void removeSession(){
        //we save -1 into our shared preferences as the session variable so it acts similar to deleting a session variable in the browser
        editor.putInt(sessionKey, -1).commit();
    }
}
