package com.zf.land.app;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;
import java.util.List;


/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/12/17
 * @description: AppInfo
 */
public class AppInfo {

    private AppInfo(){
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获得系统进程信息 方式一
     */
    public static ProcessInfo getRunningAppProcessInfo(Context context) {
        ProcessInfo processInfo = null;
        // ProcessInfo Model类   用来保存所有进程信息
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        // 通过调用ActivityManager的getRunningAppProcesses()方法获得系统里所有正在运行的进程
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : runningAppProcesses) {
            // 进程ID号
            int pid = appProcessInfo.pid;
            // 用户ID 类似于Linux的权限不同，ID也就不同 比如 root等
            int uid = appProcessInfo.uid;
            // 进程名，默认是包名或者由属性android：process=""指定
            String processName = appProcessInfo.processName;
            // 获得该进程占用的内存
            int[] myMempid = new int[] { pid };
            // 此MemoryInfo位于android.os.Debug.MemoryInfo包中，用来统计进程的内存信息
            Debug.MemoryInfo[] memoryInfo = activityManager
                    .getProcessMemoryInfo(myMempid);
            // 获取进程占内存用信息 kb单位
            int memSize = memoryInfo[0].dalvikPrivateDirty;
            processInfo = new ProcessInfo();
            processInfo.pid = pid;
            processInfo.uid = uid;
            processInfo.processName = processName;
            processInfo.memSize = memSize;
        }
        return processInfo;
    }

    /**
     * 获取pid 方式二
     * @return
     */
    public static int getPid(){
        return android.os.Process.myPid();
    }

    /**
     * 获取uid 方式二
     * @return
     */
    public static int getUid(){
        return android.os.Process.myUid();
    }

    /**
     * 获取tid 方式二
     * @return
     */
    public static int getTid(){
        return android.os.Process.myTid();
    }

    public static class ProcessInfo{
        public int pid;
        public int uid;
        public String processName;
        public int memSize;

        private ProcessInfo(){
            throw new UnsupportedOperationException("cannot be instantiated");
        }

        @Override
        public String toString() {
            return "ProcessInfo{" +
                    "pid=" + pid +
                    ", uid=" + uid +
                    ", processName='" + processName + '\'' +
                    ", memSize=" + memSize +
                    '}';
        }
    }
}
