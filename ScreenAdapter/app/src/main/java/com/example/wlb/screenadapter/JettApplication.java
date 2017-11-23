
package com.example.wlb.screenadapter;

import android.app.Application;

/**
 * Created by wlb on 2017/11/20.
 * Description ：为了方便框架内部使用application和得到的context上下文用
 */

public class JettApplication extends Application {
    private static JettApplication instance;

    public static JettApplication getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
