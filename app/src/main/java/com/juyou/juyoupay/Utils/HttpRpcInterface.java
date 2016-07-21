package com.juyou.juyoupay.Utils;

import com.juyou.juyoupay.bean.Token;

import hprose.common.HproseCallback;
import hprose.common.HproseCallback1;

/**
 * Created by Administrator on 2016/7/1 0001.
 */
public interface HttpRpcInterface {
    String hello(String name,String access_token,HproseCallback1 callback1);
    String hello(String name,String access_token);
    Token token(String userID, String Password, HproseCallback callback);
    Token token(String userID, String Password);


}
