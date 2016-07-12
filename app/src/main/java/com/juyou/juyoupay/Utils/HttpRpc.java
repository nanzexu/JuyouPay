package com.juyou.juyoupay.Utils;

import android.content.Context;

import com.juyou.juyoupay.bean.Token;

import hprose.client.HproseHttpClient;
import hprose.common.HproseCallback;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class HttpRpc {
    private RpcInter rpcInter;
    public void getToken(String userId,String passWord,RpcInter inter){
        this.rpcInter=inter;

    }


}
