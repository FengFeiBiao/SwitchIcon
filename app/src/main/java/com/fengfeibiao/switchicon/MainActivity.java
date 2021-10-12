package com.fengfeibiao.switchicon;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ForegroundCallbacks.Listener {

    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //进行app前后台监听
        ForegroundCallbacks.get(this).addListener(this);

        findViewById(R.id.tv_default).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = 0;
            }
        });

        findViewById(R.id.tv_alias1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = 1;
            }
        });

    }

    @Override
    protected void onDestroy() {
        // 移除app前后台监听
        ForegroundCallbacks.get(this).removeListener(this);
        super.onDestroy();
    }

    @Override
    public void onForeground() {

    }

    @Override
    public void onBackground() {
        //根据具体业务需求设置切换条件,我公司采用接口控制icon切换
        if (position == 0) {
            setDefaultAlias();
        } else {
            setAlias1();
        }
    }


    /**
     * 设置默认的别名为启动入口
     */
    public void setDefaultAlias() {
        PackageManager packageManager = getPackageManager();
        packageManager.setComponentEnabledSetting(new ComponentName(this, "com.fengfeibiao.switchicon.DefaultAliasActivity"),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

        packageManager.setComponentEnabledSetting(new ComponentName(this, "com.fengfeibiao.switchicon.Alias1Activity"),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }

    /**
     * 设置别名1为启动入口
     */
    public void setAlias1() {
        PackageManager packageManager = getPackageManager();
        packageManager.setComponentEnabledSetting(new ComponentName(this, "com.fengfeibiao.switchicon.DefaultAliasActivity"),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

        packageManager.setComponentEnabledSetting(new ComponentName(this, "com.fengfeibiao.switchicon.Alias1Activity"),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }
}