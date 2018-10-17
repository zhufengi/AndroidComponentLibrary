package com.zf.land.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/17
 * @description: CallPhoneStateReceiver拨打电话状态广播监听
 */
public class CallPhoneStateReceiver extends BroadcastReceiver {

    private static String TAG = "CallPhoneStateReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            // 去电
        } else {
            //来电

        }
        if(intent.getAction().equals(TelephonyManager.ACTION_PHONE_STATE_CHANGED)){
            TelephonyManager teleManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            teleManager.getCallState();
            switch (teleManager.getCallState()){
                case TelephonyManager.CALL_STATE_IDLE:
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    break;
                    default:
                        break;
            }
            //需要权限android.permission.READ_PRECISE_PHONE_STATE;
//            teleManager.listen(new PhoneStateListener(){
//                @Override
//                public void onPreciseCallStateChanged(PreciseCallState callState) {
//                    // default implementation empty
//                }
//            },PhoneStateListener.LISTEN_CALL_STATE);
        }
    }
}
