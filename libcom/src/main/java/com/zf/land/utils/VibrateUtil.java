package com.zf.land.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Vibrator;

/**
 * ================================================
 * <p>All methods requires the caller to hold the permission
 * {@link android.Manifest.permission#VIBRATE}.
 * ================================================
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/11/26
 * @description: UriUtils
 */

public class VibrateUtil {

    private VibrateUtil(){
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * Vibrate constantly for the specified period of time.
     * @param milliseconds The number of milliseconds to vibrate.
     */
    @SuppressLint("MissingPermission")
    public static void vibrate(Context context, long milliseconds) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(milliseconds);
    }

    /**
     * Vibrate with a given pattern.
     * @param pattern an array of longs of times for which to turn the vibrator on or off.
     * @param repeat  the index into pattern at which to repeat, or -1 if you don't want to repeat.
     */
    @SuppressLint("MissingPermission")
    public static void vibrate(Context context, long[] pattern, int repeat) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(pattern, repeat);
    }

}
