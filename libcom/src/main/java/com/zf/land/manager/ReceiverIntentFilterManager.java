package com.zf.land.manager;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.orhanobut.logger.Logger;
import com.zf.land.receiver.BootAndShutdownReceiver;
import com.zf.land.receiver.SdcardListenReceiver;
import com.zf.land.receiver.UserPresentReceiver;
import com.zf.land.utils.LogUtils;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/12/7
 * @description: ReceiverIntentFilterManager
 */
public class ReceiverIntentFilterManager {
    private static final String TAG = "ReceiverIntentFilterManager";
    /**手机屏幕解锁监听*/
    private UserPresentReceiver mUserPresentReceiver = null;
    /**开关机监听*/
    private BootAndShutdownReceiver mBootAndShutdownReceiver = null;
    /**sdcard监听*/
    private SdcardListenReceiver mSdcardListenReceiver = null;
    private Context mContext;
    private IntentFilter mIntentFilter = null;


    public void init(Context mContext){
        this.mContext = mContext;
        mUserPresentReceiver = new UserPresentReceiver();
        mBootAndShutdownReceiver = new BootAndShutdownReceiver();
        mSdcardListenReceiver = new SdcardListenReceiver();
    }

    /**
     * add UserPresentReceiver
     */
    public void addUserPresentReceiver(){
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(Intent.ACTION_USER_PRESENT);
        mIntentFilter.setPriority(Integer.MAX_VALUE);
        mContext.registerReceiver(mUserPresentReceiver, mIntentFilter);
        LogUtils.i(TAG,"addUserPresentReceiver ...");
    }

    /**
     * add BootAndShutdownReceiver
     */
    public void addBootAndShutdownReceiver(){
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(Intent.ACTION_BOOT_COMPLETED);
        mIntentFilter.addAction(Intent.ACTION_SHUTDOWN);
        mIntentFilter.addAction(Intent.ACTION_REBOOT);
        mContext.registerReceiver(mBootAndShutdownReceiver,mIntentFilter);
        LogUtils.d(TAG,"addBootAndShutdownReceiver ...");
    }

    /**
     * add SdcardListenReceiver
     */
    public void addSdcardListenReceiver(){
        mIntentFilter = new IntentFilter();
        // 设置最高优先级 
        mIntentFilter.setPriority(100);
        // sd卡挂载
        mIntentFilter.addAction(Intent.ACTION_MEDIA_MOUNTED);
        // sd卡存在，但还没有挂载
        mIntentFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
        // sd卡被移除    
        mIntentFilter.addAction(Intent.ACTION_MEDIA_REMOVED);
        // sd卡作为 USB大容量存储被共享，挂载被解除   
        mIntentFilter.addAction(Intent.ACTION_MEDIA_SHARED);
        // sd卡已经从sd卡插槽拔出，但是挂载点还没解除    
        mIntentFilter.addAction(Intent.ACTION_MEDIA_BAD_REMOVAL);
        // 开始扫描    
        mIntentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_STARTED);
        // 扫描完成 
        mIntentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);
        mIntentFilter.addAction(Intent.ACTION_MEDIA_EJECT);
        mIntentFilter.addAction(Intent.ACTION_MEDIA_BUTTON);
        mIntentFilter.addAction(Intent.ACTION_MEDIA_CHECKING);
        mIntentFilter.addAction(Intent.ACTION_MEDIA_NOFS);
        mIntentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mIntentFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTABLE);
        // 必须添加，否则无法接收到广播
        mIntentFilter.addDataScheme("file");
        mContext.registerReceiver(mSdcardListenReceiver, mIntentFilter);
    }
}
