package com.juyou.juyoupay.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.juyou.juyoupay.bean.Token;
import com.juyou.juyoupay.bean.UserInfo;

import hprose.client.HproseHttpClient;
import hprose.common.HproseCallback;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class HttpRpc {

    private String urlRpcServer;
    private HproseHttpClient client ;
    private final  HttpRpcInterface hri;
    private final ProgressDialog progressDialog;
    private final Context context;
    private Token token;
    public HttpRpc(String url,Context con){
        this.urlRpcServer=url;
        this.context=con;

        this.token=(Token)SharedPreferencesUtils.getParam(this.context,"token","");
        this.progressDialog = new ProgressDialog(this.context);
        this.progressDialog.setTitle("提示信息");
        this.progressDialog.setMessage("正在处理中，请稍后......");
        //    设置setCancelable(false); 表示我们不能取消这个弹出框，等下载完成之后再让弹出框消失
        this.progressDialog.setCancelable(false);
        //    设置ProgressDialog样式为圆圈的形式
        this.progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        this.client=new HproseHttpClient(this.urlRpcServer);
        this.hri=(HttpRpcInterface)client.useService(HttpRpcInterface.class);

    }
    private static class getUserInfoParms{
        String userId;
        String passWord;
        String token;
        public getUserInfoParms(String userId,String passWord,String token){
            this.userId=userId;
            this.passWord=passWord;
            this.token=token;
        }
    }
    public void getUserinfo(String userId,String passWord,String token){
        Log.i("juyou_pay",token);
        getUserInfoParms guip=new getUserInfoParms(userId,passWord,token);
        new GetUserInfoAsyncTask().execute(guip);

    }
    public class GetUserInfoAsyncTask extends AsyncTask<getUserInfoParms,Integer,UserInfo>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(UserInfo userInfo) {
            super.onPostExecute(userInfo);
        }

        @Override
        protected UserInfo doInBackground(getUserInfoParms... getUserInfoParmses) {
            UserInfo userInfo=hri.getUserInfo(getUserInfoParmses[0].userId,getUserInfoParmses[0].passWord,getUserInfoParmses[0].token);
            SharedPreferencesUtils.setParam(context,"userinfo",userInfo);
            return userInfo;
        }
    }
    public void hello(String name){
        new HelloAsyncTask().execute(name);
    }
    public class HelloAsyncTask extends AsyncTask<String,Integer,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
        }

        @Override
        protected String doInBackground(String... strings) {
            Token token=hri.token("zy","123456");
            Log.i("juyou","=========================");
            Log.i("juyou",token.access_token);
            Log.i("juyou", String.valueOf(token.expires));
            Log.i("juyou", String.valueOf(token.lastlogintime));
            Log.i("juyou", String.valueOf(token.code));
            SharedPreferencesUtils.setParam(context,"token",token);
           /* String h=hri.hello(strings[0]);
            Log.i("juyou", h);*/
            return "ffff";
        }
    }
}
