package com.zf.land.receiver;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zf.land.R;
import com.zf.land.manager.XNotificationManager;
import com.zf.land.comm.utils.LogUtils;
import com.zf.land.comm.utils.NetworkUtils;
import com.zf.land.comm.utils.ToastUtils;


/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/17
 * @description: NetWorkChangReceiver
 */
public class NetWorkChangReceiver extends BroadcastReceiver {
    private String TAG = "NetWorkChangReceiver";
    /**更新网络通知栏ID*/
    private int notifyID = 0x1234;

    @Override
    public void onReceive(Context context, Intent intent) {
        XNotificationManager notificationManager = new XNotificationManager(context);
        LogUtils.i(TAG, "NetWorkChangReceiver ...networkType :"+NetworkUtils.getNetworkType(context)+"... connectStatus :"+NetworkUtils.isConnected(context));

        if (NetworkUtils.isConnected(context)) {
            notificationManager.cancel(notifyID);
        } else {
//            ToastUtils.showToast(context,R.string.string_network_disconnect_hint);
            intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 3, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            notificationManager.sendCustomNotification(context.getString(R.string.string_network_disconnect),
                    context.getString(R.string.string_network_disconnect_tip),notifyID,
                    context.getString(R.string.string_network_set), android.R.drawable.sym_def_app_icon, pendingIntent);
        }
    }
}
