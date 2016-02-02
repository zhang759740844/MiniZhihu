package com.example.zachary.minizhihu;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by zachary on 16/1/29.
 */
public class ExampleApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);
    }

    //在自己的Application中添加如下代码
    public static RefWatcher getRefWatcher(Context context) {
        ExampleApplication application = (ExampleApplication) (context
                .getApplicationContext());
        return application.refWatcher;
    }

    //在自己的Application中添加如下代码
    private RefWatcher refWatcher;

}