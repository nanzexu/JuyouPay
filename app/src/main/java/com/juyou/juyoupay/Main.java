package com.juyou.juyoupay;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.juyou.juyoupay.Utils.HttpRpc;
import com.juyou.juyoupay.Utils.HttpRpcInterface;
import com.juyou.juyoupay.Utils.SharedPreferencesUtils;
import com.juyou.juyoupay.bean.Token;
import com.juyou.juyoupay.bean.UserInfo;

import org.json.JSONException;

import hprose.client.HproseHttpClient;
import hprose.common.HproseCallback;

public class Main extends AppCompatActivity {

    private final String urlRpcServer="http://www.ptjuyou.com/weisite/app/index.php?i=2&c=entry&do=rpc&m=juyou_pay";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.init();
    }
    private void init(){
        Button btn= (Button) findViewById(R.id.getpwdBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HttpRpc httpRpc=new HttpRpc(urlRpcServer,Main.this);
                httpRpc.hello("nanze");

            }
        });
        Button logBtn = (Button) findViewById(R.id.loginBtn);
        //final TextView copyright= (TextView) findViewById(R.id.copyright);

        logBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Token token=(Token) SharedPreferencesUtils.getParam(view.getContext(),"token","");
                Log.i("juyou","=========================");
                Log.i("juyou",token.access_token);
                Log.i("juyou",token.openid);
                Log.i("juyou", String.valueOf(token.expires));
                Log.i("juyou", String.valueOf(token.nickname));
            }
        });

    }
}
