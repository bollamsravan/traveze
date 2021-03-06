package com.example.se.travezeandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;


class MyPreference {
    private static MyPreference myPreference;
    private SharedPreferences sharedPreferences;

    static MyPreference getInstance(Context context) {
        if (myPreference == null) {
            myPreference = new MyPreference(context);
        }
        return myPreference;
    }

    private MyPreference(Context context) {
        sharedPreferences = context.getSharedPreferences("com.example.se.traveze",Context.MODE_PRIVATE);
    }

    void saveData(String key,String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor .putString(key, value);
        prefsEditor.apply();
    }

    String getData(String key) {
        if (sharedPreferences!= null) {
            return sharedPreferences.getString(key, "");
        }
        return "";
    }

    boolean isLoggedIn(){
        String authToken = getData("Authorization");
        return !Objects.equals(authToken, "");
    }

    void saveAuthToken(String authToken){
        saveData("Authorization",authToken);
    }

    String getAuthToken(){
        return getData("Authorization");
    }

    String getUserName(){
        return getData("username");
    }

    String getEmail(){
        return getData("email");
    }

    void saveUserInfo(JSONObject user){
        try {
            saveData("username",user.getString("name"));
            saveData("email",user.getString("email"));
            saveData("mobilenumber",user.getString("mobilenumber"));
            saveData("user_role", String.valueOf(user.getInt("role")));
            saveData("user_id",String.valueOf(user.getInt("id")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    void logout(){
        saveData("Authorization","");
    }



    public String getMobileNumber() {
        return getData("mobilenumber");
    }
}
