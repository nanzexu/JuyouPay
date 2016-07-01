package com.juyou.juyoupay.bean;

import hprose.common.HproseCallback;
import hprose.common.HproseCallback1;

/**
 * Created by Administrator on 2016/7/1 0001.
 */
public interface HttpRpcInterface {
    String hello(String name,HproseCallback1 callback1);
    Token token(String userID,String Password,HproseCallback callback);

}
