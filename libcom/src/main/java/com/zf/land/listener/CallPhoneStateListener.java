package com.zf.land.listener;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;

import com.orhanobut.logger.Logger;
import com.zf.land.utils.LogUtils;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/17
 * @description: PhoneStateListener电话状态监听
 * CALL_STATE_IDLE 空闲态(没有通话活动)
 * 	CALL_STATE_RINGING 包括响铃、第三方来电等待
 * 	CALL_STATE_OFFHOOK 包括dialing拨号中、active接通、hold挂起等
 * 由上可知，active接通状态没有单独给出，所以我们无法得知电话是否接通了，
 * 因此需要其它手段来获取更多的精确通话状态，遍查网络资料，一般有两种方法！
 */
public class CallPhoneStateListener extends PhoneStateListener {
    private static String TAG = "CallPhoneStateListener";
    private Context mContext;

    public CallPhoneStateListener(Context context) {
        mContext = context;
    }

    /**
     * 注册通话状态监听
     * @param context
     */
    public static void registerPhoneStateListener(Context context) {
        LogUtils.i(TAG,"registerPhoneStateListener ...");
        CallPhoneStateListener customPhoneStateListener = new CallPhoneStateListener(context);
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager != null) {
            telephonyManager.listen(customPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }

    @Override
    public void onServiceStateChanged(ServiceState serviceState) {
        super.onServiceStateChanged(serviceState);
        LogUtils.i(TAG, "CustomPhoneStateListener onServiceStateChanged: " + serviceState);
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        LogUtils.i(TAG, "CustomPhoneStateListener state: "+ state + " incomingNumber: " + incomingNumber);
        switch (state) {
            // (电话挂断)当前电话没有进行活动
            case TelephonyManager.CALL_STATE_IDLE:
                break;
            // 电话响铃
            case TelephonyManager.CALL_STATE_RINGING:
                break;
            // 来电接通 或者 去电  但是没法区分
            case TelephonyManager.CALL_STATE_OFFHOOK:
                break;
            default:
                break;
        }
    }



}
