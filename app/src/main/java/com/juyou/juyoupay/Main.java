package com.juyou.juyoupay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import hprose.client.HproseHttpClient;
import hprose.common.HproseCallback1;

public class Main extends AppCompatActivity {

    private String urlRpcServer="http://www.ptjuyou.com/weisite/app/index.php?i=2&c=entry&do=rpc&m=juyou_sms";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.init();
    }
    private void init(){
        Button logBtn = (Button) findViewById(R.id.loginBtn);
        final TextView copyright= (TextView) findViewById(R.id.copyright);
        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("juyou","prc begin===========");
                HproseHttpClient client = new HproseHttpClient(urlRpcServer);
                /*client.invoke("hello", new Object[] {"hprose"}, new HproseCallback1<String>() {
                    @Override
                    public void handler(final String s) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                copyright.setText(s);
                                Log.i("juyou",s);
                            }
                        });
                    }
                });*/


            }
        });
    }
}
