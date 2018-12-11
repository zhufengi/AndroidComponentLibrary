package com.zf.land.utils;

import android.os.SystemClock;
import android.util.Log;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/11/26
 * @description: TimeKeeper
 */

public class TimeKeeper {

    private static final String TAG = "TimeKeeper";
    private long keepTimeMillis;
    private long startMillis;

    public TimeKeeper(long keepTimeMillis) {
        this.keepTimeMillis = keepTimeMillis;
    }

    public long getKeepTimeMillis() {
        return keepTimeMillis;
    }

    public TimeKeeper setKeepTimeMillis(long keepTimeMillis) {
        this.keepTimeMillis = keepTimeMillis;
        return this;
    }

    public TimeKeeper printTimeCost(String event) {
        long costMillis = SystemClock.elapsedRealtime() - startMillis;
        Log.d(TAG, event + " cost time millis: " + costMillis);
        return this;
    }

    public TimeKeeper startNow() {
        startMillis = SystemClock.elapsedRealtime();
        return this;
    }

    public TimeKeeper waitForEnd(OnEndCallback endCallback) {
        long costMillis = SystemClock.elapsedRealtime() - startMillis;
        long leftMillis = keepTimeMillis - costMillis;
        if (leftMillis > 0) {
            SystemClock.sleep(leftMillis);
            endCallback.onEnd(costMillis, leftMillis);
        } else {
            endCallback.onEnd(costMillis, leftMillis);
        }
        return this;
    }

    public interface OnEndCallback {
        void onEnd(long costTime, long leftTime);
    }
}
