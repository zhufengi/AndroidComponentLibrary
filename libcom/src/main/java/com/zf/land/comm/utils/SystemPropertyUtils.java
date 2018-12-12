package com.zf.land.comm.utils;

import java.lang.reflect.Method;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/11/26
 * @description: SystemPropertyUtils
 */
public class SystemPropertyUtils {

    private static final String TAG = "SystemPropertyUtils";

    private SystemPropertyUtils(){
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        init();
        String value = null;
        try {
            value = (String) mGetMethod.invoke(mClassType, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     *
     * @param key
     * @param def
     * @return
     */
    public static int getInt(String key, int def) {
        init();
        int value = def;
        try {
            Integer v = (Integer) mGetIntMethod.invoke(mClassType, key, def);
            value = v.intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 获取sdk版本
     * @return
     */
    public static int getSdkVersion() {
        return getInt("ro.build.version.sdk", -1);
    }

    /**
     * ================================================================
     *
     */
    private static Class<?> mClassType = null;
    private static Method mGetMethod = null;
    private static Method mGetIntMethod = null;
    private static void init() {
        try {
            if (mClassType == null) {
                mClassType = Class.forName("android.os.SystemPropertyUtils");

                mGetMethod = mClassType.getDeclaredMethod("get", String.class);
                mGetIntMethod = mClassType.getDeclaredMethod("getInt", String.class, int.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
