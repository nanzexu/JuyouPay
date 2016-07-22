package com.juyou.juyoupay.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.juyou.juyoupay.R;
import com.juyou.juyoupay.Utils.BaseActivity;
import com.juyou.juyoupay.Utils.Global;
import com.juyou.juyoupay.Utils.HttpRpc;

public class Login extends BaseActivity {

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        this.setContentView(R.layout.login);
    }

    @Override
    protected void init() {
        Button logBtn = (Button) findViewById(R.id.loginBtn);
        final TextView userIdTextView= (TextView) findViewById(R.id.idEditer);
        final TextView passWordTextView= (TextView) findViewById(R.id.pwdEditer);
        logBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                HttpRpc httpRpc=new HttpRpc(Global.urlRpcServer,view.getContext());
                httpRpc.getUserinfo(userIdTextView.getText().toString(),passWordTextView.getText().toString(),Global.getAccess_Token(view.getContext()));
            }
        });

    }
}
