package com.zf.land.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zf.land.Logger;
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
                Logger.log(TAG,"开机");
            }else if (intent.getAction().equals(ACTION_SHUTDOWN)){
                Logger.log(TAG,"关机");
            }else if (intent.getAction().equals(ACTION_REBOOT)){
                Logger.log(TAG,"重启");
            }
        }
    }
}
