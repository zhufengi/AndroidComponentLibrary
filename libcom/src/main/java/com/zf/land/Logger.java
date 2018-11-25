package com.zf.land;

import android.util.Log;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/11
 * @description: Logger
 */
public class Logger {

    public static void log(String tag,String msg){
        Log.i(tag,"当前线程>"+Thread.currentThread().getName()+"<"+msg);
    }

    public static void e(String tag,String msg){
        Log.e(tag,"当前线程>"+Thread.currentThread().getName()+"<"+msg);
    }
}
