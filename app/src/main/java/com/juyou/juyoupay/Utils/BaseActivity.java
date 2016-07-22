package com.juyou.juyoupay.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * Created by Administrator on 2016/7/22 0022.
 */
public abstract class BaseActivity extends AppCompatActivity {
    // 可以把常量单独放到一个Class中
    public static final String ACTION_NETWORK_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";
    public static final String ACTION_PUSH_DATA = "fm.data.push.action";
    public static final String ACTION_NEW_VERSION = "apk.update.action";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView(savedInstanceState);
        init();
    }

    public BaseActivity() {

    }

    // 初始化UI，setContentView等
    protected abstract void initContentView(Bundle savedInstanceState);

    //初始化
    protected  abstract void init();


    // 可能全屏或者没有ActionBar等
    private void setBaseToNoActionBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 例
    }


    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // 处理各种情况
            String action = intent.getAction();
            if (ACTION_NETWORK_CHANGE.equals(action)) { // 网络发生变化
                // 处理网络问题
            } else if (ACTION_PUSH_DATA.equals(action)) { // 可能有新数据
                Bundle b = intent.getExtras();
                /*MData<Employee> mdata = (MData<Employee>) b.get("data");
                if (dataCallback != null) { // 数据通知
                    dataCallback.onNewData(mdata);
                }*/
            } else if (ACTION_NEW_VERSION.equals(action)) { // 可能发现新版本
                // VersionDialog 可能是版本提示是否需要下载的对话框
            }
        }
    };
}
