package com.zf.land;

import android.content.Context;

import java.io.DataOutputStream;
import java.io.File;

/**
 * @author: Administrator
 * @github: https://github.com/zhufengi
 * @time: 2018/11/25 0025
 * @description: DevicesUtils
 */
public class DevicesUtils {
    private static String TAG = "DevicesUtils";

    /**
     * 是否root
     * @return
     */
    public static boolean isRooted() {
        String suSearchPaths[] = {"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        File file = null;
        boolean flag1 = false;
        for (String suSearchPath : suSearchPaths) {
            file = new File(suSearchPath + "su");
            if (file.isFile() && file.exists()) {
                flag1 = true;
                break;
            }
        }
        return flag1;
    }
    /**
     * 获取root权限
     * @param context
     * @return
     */
    public static boolean getRootPermission(Context context) {
        String packageCodePath = context.getPackageCodePath();
        Process process = null;
        DataOutputStream os = null;
        try {
            String cmd = "chmod 777 " + packageCodePath;
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(cmd + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
