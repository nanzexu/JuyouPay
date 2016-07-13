package com.juyou.juyoupay;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.juyou.juyoupay.Utils.HttpRpc;
import com.juyou.juyoupay.Utils.HttpRpcInterface;
import com.juyou.juyoupay.bean.Token;
import com.juyou.juyoupay.bean.UserInfo;

import org.json.JSONException;

import hprose.client.HproseHttpClient;
import hprose.common.HproseCallback;

public class Main extends AppCompatActivity {

    private final String urlRpcServer="http://www.ptjuyou.com/weisite/app/index.php?i=2&c=entry&do=rpc&m=juyou_sms";
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

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog m_pDialog;
                m_pDialog = new ProgressDialog(view.getContext());
                m_pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

                // 设置ProgressDialog 提示信息
                m_pDialog.setMessage("请稍等。。。");

                // 设置ProgressDialog 的进度条是否不明确
                m_pDialog.setIndeterminate(false);

                // 设置ProgressDialog 是否可以按退回按键取消
                m_pDialog.setCancelable(false);
                m_pDialog.show();
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
                HttpRpcInterface hri=(HttpRpcInterface)client.useService(HttpRpcInterface.class);
                /*hri.hello("nanze",new HproseCallback1(){

                    @Override
                    public void handler(Object o) {
                        copyright.setText(o.toString());
                        Log.i("juyou",o.toString());
                    }
                });*/
                hri.token("nanze","123456", new HproseCallback(){

                    @Override
                    public void handler(final Object o, Object[] objects) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                m_pDialog.hide();
                                Token token= (Token) o;
                                Log.i("juyou",((Token) o).access_token);
                                Log.i("juyou", String.valueOf(((Token) o).expires_in));

                                try {
                                    UserInfo userinfo= token.token_info(((Token) o).access_token);
                                    Log.i("juyou",userinfo.userID);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });


                    }
                });


            }
        });
    }
}
