package com.zf.land.utils;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/12/9
 * @description: ActivityManagerUtils
 */
public class ActivityManagerUtils {

    private static final String TAG = "ActivityManagerUtils";
    /**管理所有存活的 Activity, 容器中的顺序仅仅是 Activity 的创建顺序, 并不能保证和 Activity 任务栈顺序一致*/
    private static Stack<Activity> mActivityStack;
    /**当前在前台的 Activity*/
    private static Activity mCurrentActivity;

    private ActivityManagerUtils(){
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 返回一个存储所有未销毁的 {@link Activity} 的集合
     *
     * @return
     */
    public static List<Activity> getActivityList() {
        if (mActivityStack == null) {
            mActivityStack = new Stack<>();
        }
        return mActivityStack;
    }


    /**
     * 添加 {@link Activity} 到集合
     */
    public static void addActivity(Activity activity) {
        synchronized (ActivityManagerUtils.class) {
            List<Activity> activities = getActivityList();
            if (!activities.contains(activity)) {
                activities.add(activity);
            }
            printfActivityStack();

        }
    }


    /**
     * 获取最近启动的一个 {@link Activity}, 此方法不保证获取到的 {@link Activity} 正处于前台可见状态
     * 即使 App 进入后台或在这个 {@link Activity} 中打开一个之前已经存在的 {@link Activity}, 这时调用此方法
     * 还是会返回这个最近启动的 {@link Activity}, 因此基本不会出现 {@code null} 的情况
     * 比较适合大部分的使用场景, 如 startActivity
     * <p>
     * Tips: mActivityStack 容器中的顺序仅仅是 Activity 的创建顺序, 并不能保证和 Activity 任务栈顺序一致
     *
     * @return
     */
    public static Activity getTopActivity() {
        if (mActivityStack == null) {
            LogUtils.w(TAG,"mActivityStack == null when getTopActivity()");
            return null;
        }
        return mActivityStack.size() > 0 ? mActivityStack.get(mActivityStack.size() - 1) : null;
    }
    /**
     * 将在前台的 {@link Activity} 赋值给 {@code currentActivity}, 注意此方法是在 {@link Activity#onResume} 方法执行时将栈顶的 {@link Activity} 赋值给 {@code currentActivity}
     * 所以在栈顶的 {@link Activity} 执行 {@link Activity#onCreate} 方法时使用 {@link #getCurrentActivity()} 获取的就不是当前栈顶的 {@link Activity}, 可能是上一个 {@link Activity}
     * 如果在 App 启动第一个 {@link Activity} 执行 {@link Activity#onCreate} 方法时使用 {@link #getCurrentActivity()} 则会出现返回为 {@code null} 的情况
     * 想避免这种情况请使用 {@link #getTopActivity()}
     *
     * @param currentActivity
     */
    public void setCurrentActivity(Activity currentActivity) {
        this.mCurrentActivity = currentActivity;
    }
    /**
     * 获取在前台的 {@link Activity} (保证获取到的 {@link Activity} 正处于可见状态, 即未调用 {@link Activity#onStop()}), 获取的 {@link Activity} 存续时间
     * 是在 {@link Activity#onStop()} 之前, 所以如果当此 {@link Activity} 调用 {@link Activity#onStop()} 方法之后, 没有其他的 {@link Activity} 回到前台(用户返回桌面或者打开了其他 App 会出现此状况)
     * 这时调用 {@link #getCurrentActivity()} 有可能返回 {@code null}, 所以请注意使用场景和 {@link #getTopActivity()} 不一样
     * <p>
     * Example usage:
     * 使用场景比较适合, 只需要在可见状态的 {@link Activity} 上执行的操作
     * 如当后台 {@link Service} 执行某个任务时, 需要让前台 {@link Activity} ,做出某种响应操作或其他操作,如弹出Dialog, 这时在 {@link Service} 中就可以使用 {@link #getCurrentActivity()}
     * 如果返回为 {@code null}, 说明没有前台 {@link Activity} (用户返回桌面或者打开了其他 App 会出现此状况), 则不做任何操作, 不为 {@code null}, 则弹出 Dialog
     *
     * @return
     */
    public static Activity getCurrentActivity() {
        return mCurrentActivity != null ? mCurrentActivity : null;
    }

    /**
     * 删除集合里的指定的 {@link Activity} 实例
     *
     * @param {@link Activity}
     */
    public static void removeActivity(Activity activity) {
        if (mActivityStack == null) {
            LogUtils.w(TAG,"mActivityStack == null when removeActivity(Activity)");
            return;
        }
        synchronized (ActivityManagerUtils.class) {
            if (mActivityStack.contains(activity)) {
                mActivityStack.remove(activity);
            }
        }
    }

    /**
     * 关闭指定的 {@link Activity} class 的所有的实例
     *
     * @param activityClass
     */
    public static void killActivity(Class<?> activityClass) {
        if (mActivityStack == null) {
            LogUtils.w(TAG,"mActivityStack == null when killActivity(Class)");
            return;
        }
        synchronized (ActivityManagerUtils.class) {
            Iterator<Activity> iterator = getActivityList().iterator();
            while (iterator.hasNext()) {
                Activity next = iterator.next();

                if (next.getClass().equals(activityClass)) {
                    iterator.remove();
                    next.finish();
                }
            }
        }
    }

    /**
     * 指定的 {@link Activity} 实例是否存活
     *
     * @param {@link Activity}
     * @return
     */
    public static boolean activityInstanceIsLive(Activity activity) {
        if (mActivityStack == null) {
            LogUtils.w(TAG,"mActivityStack == null when activityInstanceIsLive(Activity)");
            return false;
        }
        return mActivityStack.contains(activity);
    }

    /**
     * 指定的 {@link Activity} class 是否存活(同一个 {@link Activity} class 可能有多个实例)
     *
     * @param activityClass
     * @return
     */
    public static boolean activityClassIsLive(Class<?> activityClass) {
        if (mActivityStack == null) {
            LogUtils.w(TAG,"mActivityStack == null when activityClassIsLive(Class)");
            return false;
        }
        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(activityClass)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取指定 {@link Activity} class 的实例,没有则返回 null(同一个 {@link Activity} class 有多个实例,则返回最早创建的实例)
     *
     * @param activityClass
     * @return
     */
    public static Activity findActivity(Class<?> activityClass) {
        if (mActivityStack == null) {
            LogUtils.w(TAG,"mActivityStack == null when findActivity(Class)");
            return null;
        }
        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(activityClass)) {
                return activity;
            }
        }
        return null;
    }

    /**
     * 关闭所有 {@link Activity}
     */
    public static void killAllActivities() {
//        while (getActivityList().size() != 0) { //此方法只能兼容LinkedList
//            getActivityList().remove(0).finish();
//        }
        synchronized (ActivityManagerUtils.class) {
            Iterator<Activity> iterator = getActivityList().iterator();
            while (iterator.hasNext()) {
                Activity next = iterator.next();
                iterator.remove();
                next.finish();
            }
        }
    }

    /**
     * 关闭所有 {@link Activity},排除指定的 {@link Activity}
     *
     * @param excludeActivityClasses activity class
     */
    public static void killOtherActivities(Class<?>... excludeActivityClasses) {
        List<Class<?>> excludeList = Arrays.asList(excludeActivityClasses);
        synchronized (ActivityManagerUtils.class) {
            Iterator<Activity> iterator = getActivityList().iterator();
            while (iterator.hasNext()) {
                Activity next = iterator.next();
                if (excludeList.contains(next.getClass()))
                    continue;
                iterator.remove();
                next.finish();
            }
        }
    }
    /**
     * 关闭所有 {@link Activity},排除指定的 {@link Activity}
     *
     * @param excludeActivityName {@link Activity} 的完整全路径
     */
    public static void killOtherActivityNames(String... excludeActivityName) {
        List<String> excludeList = Arrays.asList(excludeActivityName);
        synchronized (ActivityManagerUtils.class) {
            Iterator<Activity> iterator = getActivityList().iterator();
            while (iterator.hasNext()) {
                Activity next = iterator.next();

                if (excludeList.contains(next.getClass().getName()))
                    continue;

                iterator.remove();
                next.finish();
            }
        }
    }

    /**
     * 退出应用程序
     */
    public static void killPidExitApp() {
        try {
            killAllActivities();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 不杀进程退出app
     */
    public static void exitApp() {
        ActivityManagerUtils.killAllActivities();
    }

    /**
     * 重启app
     * @param context
     * @param millisecond seconds=millisecond/1000
     */
    public static void relaunchApp(final Context context, long millisecond) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent LaunchIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
                LaunchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(LaunchIntent);
            }
        }, millisecond);
    }

    /**
     * 重启app，杀掉进程
     * @param context
     * @param millisecond seconds=millisecond/1000
     */
    public static void relaunchAppForKillPid(Context context, long millisecond) {
        Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(context.getPackageName());
        PendingIntent restartIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + millisecond, restartIntent);
        System.exit(0);
    }

    /**
     * printf activity stack.
     */
    public static void printfActivityStack(){
        for (int i = 0, size = mActivityStack.size(); i < size; i++){
            if (null != mActivityStack.get(i)){
                LogUtils.w(TAG, " index(" + i + "/" + (size-1) + "):" + mActivityStack.get(i));
            }
        }
    }
}
