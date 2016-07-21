package com.juyou.juyoupay.bean;


import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/30 0030.
 */
public class Token implements Serializable {
    public String  access_token ;
    public String  userid ;
    public int expires;
    public String realname;
    public String openid;
    public String nickname;
    public int createtime;
    public int code;


}
