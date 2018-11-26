package com.zf.land.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.zf.land.Logger;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/11/22
 * @description: AppConfigUtils
 */
public class AppConfigUtils {

    private static final String TAG = "AppConfigUtils";
    private static String nullException = "please input all parameter";

    private AppConfigUtils() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    /**
     * 获取package info
     *
     * @param context
     * @param pckName
     * @return
     */
    public static PackageInfo getPackageInfo(Context context, String pckName) {
        if (context == null || StringUtils.isEmpty(pckName)) {
            Logger.e(TAG, "getPackageInfo " + nullException);
            return null;
        }
        try {
            return context.getPackageManager()
                    .getPackageInfo(pckName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取version Code
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        if (context == null) {
            Logger.e(TAG, "getVersionCode " + nullException);
            return 0;
        }
        int versionCode = 0;
        try {
            versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(),
                    0).versionCode;
        } catch (PackageManager.NameNotFoundException ex) {
            versionCode = 0;
        }
        return versionCode;
    }

    /**
     * 获取version name
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        String name = "";
        if (context == null) {
            Logger.e(TAG, "getVersionCode " + nullException);
            return name;
        }
        try {
            name = context.getPackageManager().getPackageInfo(context.getPackageName(),
                    0).versionName;
        } catch (PackageManager.NameNotFoundException ex) {
            name = "";
        }
        return name;
    }

    /**
     * 获取指定包名应用的版本号
     *
     * @param context
     * @param packageName
     * @return
     */
    public static int getVersionCode(Context context, String packageName) {
        int versionCode = 0;
        if (context == null || StringUtils.isEmpty(packageName)) {
            Logger.e(TAG, "getVersionCode " + nullException);
            return versionCode;
        }
        try {
            versionCode = context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException ex) {
            versionCode = 0;
        }
        return versionCode;
    }

    /**
     * 当前的包是否存在
     *
     * @param context
     * @param pckName
     * @return
     */
    public static boolean isPackageExist(Context context, String pckName) {
        if (context == null || StringUtils.isEmpty(pckName)) {
            Logger.e(TAG, "getVersionCode " + nullException);
            return false;
        }
        try {
            PackageInfo pckInfo = context.getPackageManager().getPackageInfo(pckName, 0);
            if (pckInfo != null)
                return true;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(TAG, e.getMessage());
            return false;
        }
        return false;
    }

    /**
     * 获取应用项目名字
     *
     * @param context
     * @param packageName
     * @return
     */
    public static String getAppName(Context context, String packageName) {
        String appName = null;
        PackageManager pm = context.getPackageManager();
        if (context == null || StringUtils.isEmpty(packageName)) {
            Logger.e(TAG, "getAppName " + nullException);
            return appName;
        }
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(packageName, 0);
            appName = String.valueOf(pm.getApplicationLabel(applicationInfo));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appName;
    }

    /**
     * 获取应用图标
     *
     * @param context
     * @param packageName
     * @return
     */
    public static Drawable getAppIcon(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        Drawable appIcon = null;
        if (context == null || StringUtils.isEmpty(packageName)) {
            Logger.e(TAG, "getAppIcon " + nullException);
            return appIcon;
        }
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(packageName, 0);
            appIcon = applicationInfo.loadIcon(pm);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appIcon;
    }

    /**
     * 获取应用第一次安装时间
     *
     * @param context
     * @param packageName
     * @return
     */
    public static long getAppFirstInstallTime(Context context, String packageName) {
        long firstInstallTime = 0;
        if (context == null || StringUtils.isEmpty(packageName)) {
            Logger.e(TAG, "getAppFirstInstallTime " + nullException);
            return firstInstallTime;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            firstInstallTime = packageInfo.firstInstallTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return firstInstallTime;
    }

    /**
     * 获取app的所有权限
     *
     * @param context
     * @param packname
     * @return
     */
    public static String[] getAppPermissions(Context context, String packname) {
        String[] requestedPermissions = null;
        if (context == null || StringUtils.isEmpty(packname)) {
            Logger.e(TAG, "getAppPermissions " + nullException);
            return requestedPermissions;
        }
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(packname, PackageManager.GET_PERMISSIONS);
            requestedPermissions = info.requestedPermissions;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return requestedPermissions;
    }

    /**
     * 检查是否拥有权限
     *
     * @param context
     * @param permission
     * @return
     */
    public static boolean hasPermission(Context context, String permission) {
        if (context == null || StringUtils.isEmpty(permission)) {
            Logger.e(TAG, "hasPermission " + nullException);
            return false;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                if (PackageManager.PERMISSION_GRANTED == packageManager.checkPermission(permission, context
                        .getPackageName())) {
                    return true;
                }
                Log.d(TAG, "Have you declared permission " + permission + " in AndroidManifest.xml ?");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * app是否安装
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        boolean installed = false;
        if (StringUtils.isEmpty(packageName)) {
            Logger.e(TAG, "isAppInstalled " + nullException);
            return installed;
        }
        List<ApplicationInfo> installedApplications = context.getPackageManager().getInstalledApplications(0);
        for (ApplicationInfo in : installedApplications) {
            if (packageName.equals(in.packageName)) {
                installed = true;
                break;
            } else {
                installed = false;
            }
        }
        return installed;
    }

    /**
     * 是否系统App
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isSystemApp(Context context, String packageName) {
        boolean isSys = false;
        PackageManager pm = context.getPackageManager();
        if (context == null || StringUtils.isEmpty(packageName)){
            Logger.e(TAG, "isSystemApp " + nullException);
            return false;
        }
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo(packageName, 0);
            if (applicationInfo != null && (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0) {
                isSys = true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            isSys = false;
        }
        return isSys;
    }

    /**
     * 启动app
     * @param context
     * @param packagename
     */
    public static void runApp(Context context, String packagename) {
        if (context == null || StringUtils.isEmpty(packagename)){
            Logger.e(TAG,"runApp "+nullException);
            return;
        }else {
            context.startActivity(new Intent(context.getPackageManager().getLaunchIntentForPackage(packagename)));
        }

    }
}
