package com.juyou.juyoupay;

import android.content.Intent;
import android.os.Bundle;

import com.juyou.juyoupay.Activity.Login;
import com.juyou.juyoupay.Utils.BaseActivity;
import com.juyou.juyoupay.Utils.Global;


public class Main extends BaseActivity {
    @Override
    protected void initContentView(Bundle savedInstanceState) {

    }

    @Override
    protected void init() {
        //判断是否登陆
        if(Global.isLogin(this)){
            Global.showToast(this,"islogin");
        }
        else {
            Intent i= new Intent();
            i.setClass(this,Login.class);
            this.startActivity(i);
        }
    }



   /* private void init(){
        Button btn= (Button) findViewById(R.id.getpwdBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HttpRpc httpRpc=new HttpRpc(Global.urlRpcServer,Main.this);
                httpRpc.hello("nanze");

            }
        });
        Button logBtn = (Button) findViewById(R.id.loginBtn);
        //final TextView copyright= (TextView) findViewById(R.id.copyright);

        logBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(Global.isLogin(view.getContext())){
                    Global.showToast(view.getContext(),"islogin");
                }
                else {
                    Global.showToast(view.getContext(),"nologin");
                }
            }
        });

    }*/
}
