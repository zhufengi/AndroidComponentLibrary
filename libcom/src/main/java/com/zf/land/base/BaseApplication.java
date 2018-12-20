package com.zf.land.base;

import android.app.Application;
import android.content.Context;

import com.zf.land.app.App;

/**
 * @author: Administrator
 * @github: https://github.com/zhufengi
 * @time: 2018/10/1
 * @description: BaseApplication
 */
public class BaseApplication extends Application implements App {

    /**
     * 这里会在onCreate之前被调用,可以做一些较早的初始化
     * 常用于 MultiDex 以及插件化框架的初始化
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 模拟环境下程序终止时回调
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onAppDestroy() {

    }
}
