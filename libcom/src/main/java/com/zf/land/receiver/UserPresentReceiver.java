package com.zf.land.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/12/7
 * @description: UserPresentReceiver
 * 手机屏幕解锁广播
 */
public class UserPresentReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action == null){
            return;
        }
        if(action.equals(Intent.ACTION_USER_PRESENT)){
        }
    }
}
