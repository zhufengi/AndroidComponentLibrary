package com.zf.land.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zf.land.Logger;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/12/17
 * @description: SdcardListenReceiver
 */
public class SdcardListenReceiver extends BroadcastReceiver {
    private final String TAG = "SdcardListenReceiver";
//    SdcardListenReceiverCallback callback;
//
//    public SdcardListenReceiver(){}
//
//    public SdcardListenReceiver(SdcardListenReceiverCallback callback){
//        this.callback = callback;
//    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null){
            String action = intent.getAction();
            Logger.e(TAG,"action :"+action);
//            callback.onSdcardListenCurrentAction(action);
            switch (action){
                case Intent.ACTION_MEDIA_MOUNTED:
                    // 卡已经成功挂载
                    // 你的SD卡路径
                    break;
                case Intent.ACTION_MEDIA_REMOVED:
                    break;
                case Intent.ACTION_MEDIA_UNMOUNTED:
                    break;
                case Intent.ACTION_MEDIA_BAD_REMOVAL:
                    break;
                case Intent.ACTION_MEDIA_SCANNER_STARTED:
                    break;
                case Intent.ACTION_MEDIA_SCANNER_FINISHED:
                    break;
                case Intent.ACTION_MEDIA_EJECT:
                    break;
                    default:
                        break;
            }
        }

    }

}
