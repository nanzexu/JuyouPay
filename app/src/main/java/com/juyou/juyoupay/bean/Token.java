package com.juyou.juyoupay.bean;


import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/6/30 0030.
 */
public class Token {
    public String  access_token ;
    public int expires_in;
    public UserInfo token_info(String access_token) throws JSONException {
        String jsonStr=new String(Base64.decode(access_token,Base64.DEFAULT));
        JSONObject jobj = new JSONObject(jsonStr);
        UserInfo userInfo=new UserInfo();
        userInfo.userID=jobj.getString("userid");
        userInfo.PassWord=jobj.getString("password");
        return userInfo;
    }

}
