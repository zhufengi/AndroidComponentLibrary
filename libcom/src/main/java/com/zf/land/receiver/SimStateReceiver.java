package com.zf.land.receiver;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.TelephonyManager;

import com.zf.land.comm.utils.LogUtils;


/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/12/7
 * @description: BootAndShutdownReceiver
 * @describe: 监听sim状态改变的广播，返回sim卡的状态， 有效或者无效。
 * 双卡中只要有一张卡的状态有效即返回状态为有效，两张卡都无效则返回无效。
 */
public class SimStateReceiver extends BroadcastReceiver {
    private static final String TAG = "SimStateReceive";
    public final static String ACTION_SIM_STATE_CHANGED = "android.intent.action.SIM_STATE_CHANGED";
    public final static int SIM_VALID = 0;
    public final static int SIM_INVALID = 1;
    private int simState = SIM_VALID;

    public int getSimState() {
        return simState;
    }

    public SimStateReceiver() { }

    public SimStateReceiver(SimCardStateChangeListener simCardStateChangeListener) {
        mSimCardStateChangeListener = simCardStateChangeListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_SIM_STATE_CHANGED)) {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
            LogUtils.i(TAG,"sim state ："+tm.getSimState());
            switch (tm.getSimState()) {
                case TelephonyManager.SIM_STATE_READY:
                    simState = SIM_VALID;
                    LogUtils.i(TAG, "sim state ：SIM_VALID");
                    break;
                case TelephonyManager.SIM_STATE_UNKNOWN:
                case TelephonyManager.SIM_STATE_ABSENT:
                case TelephonyManager.SIM_STATE_PIN_REQUIRED:
                case TelephonyManager.SIM_STATE_PUK_REQUIRED:
                case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                default:
                    LogUtils.i(TAG, "sim state ：SIM_INVALID");
                    simState = SIM_INVALID;
                    break;
            }
            if (mSimCardStateChangeListener!=null) {
                mSimCardStateChangeListener.simCardStateChange();
            }
        }
    }

    public SimCardStateChangeListener mSimCardStateChangeListener;

    public interface SimCardStateChangeListener {
        void simCardStateChange();
    }


}
