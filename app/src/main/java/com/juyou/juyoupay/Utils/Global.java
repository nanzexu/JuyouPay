package com.juyou.juyoupay.Utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.juyou.juyoupay.bean.Token;
import com.juyou.juyoupay.bean.UserInfo;

/**
 * Created by Administrator on 2016/7/22 0022.
 */
public class Global {
    public static String urlRpcServer="http://www.ptjuyou.com/weisite/app/index.php?i=2&c=entry&do=rpc&m=juyou_pay";
    private static Global instance = null;
    private static Application APP = null;
    public void onCreate(Application app) {
        Global.APP = app;
    }

    public static Global getInstance() {
        if (null == instance) {
            instance = new Global();
        }
        return instance;
    }

    public static boolean CheckNetwork(Context context) {

        boolean flag = false;
        ConnectivityManager cwjManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cwjManager.getActiveNetworkInfo() != null)
            flag = cwjManager.getActiveNetworkInfo().isAvailable();
        if (!flag) {
            Toast.makeText(context, "提示，当前网络连接不可用!", Toast.LENGTH_LONG).show();
        }
        return flag;
    }
    public static void setUserInfo(Context context,UserInfo userInfo){
        SharedPreferencesUtils.setParam(context,"userinfo",userInfo);
    }
    public static UserInfo getUserInfo(Context context){
        return (UserInfo)SharedPreferencesUtils.getParam(context,"userinfo","");
    }
    public static boolean isLogin(Context context){
        UserInfo userInfo=Global.getUserInfo(context);
        if(null==userInfo){
            return false;
        }
        else {
            return true;
        }
    }
    public static void showToast(Context context,String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
    public static String  getAccess_Token(Context context){
        Token token= (Token) SharedPreferencesUtils.getParam(context,"token","token");
        if(null==token){
            return "";
        }
        else {
            return token.access_token;
        }
    }

}
