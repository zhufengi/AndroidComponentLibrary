package com.zf.land.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.orhanobut.logger.Logger;
import com.zf.land.utils.LogUtils;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/12/7
 * @description: BootAndShutdownReceiver
 * 开机关机广播（8.0测试只对关机有效）
 */
public class BootAndShutdownReceiver extends BroadcastReceiver {

    private final String TAG = "BootAndShutdownBroadcastReceiver";
    private final String ACTION_BOOT = "android.intent.action.BOOT_COMPLETED";
    private final String ACTION_SHUTDOWN = "android.intent.action.ACTION_SHUTDOWN";
    private final String ACTION_REBOOT = "android.intent.action.REBOOT";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null){
            if (intent.getAction().equals(ACTION_BOOT)){
                LogUtils.d(TAG,"开机");
            }else if (intent.getAction().equals(ACTION_SHUTDOWN)){
                LogUtils.d(TAG,"关机");
            }else if (intent.getAction().equals(ACTION_REBOOT)){
                LogUtils.d(TAG,"重启");
            }
        }
    }
}
