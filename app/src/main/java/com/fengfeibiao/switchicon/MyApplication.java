package com.fengfeibiao.switchicon;

import android.app.Application;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ForegroundCallbacks.init(this);
    }
}
