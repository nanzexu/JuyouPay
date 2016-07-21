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
    public int expires;
    public int lastlogintime;
    public int code;
    public String message;


}
