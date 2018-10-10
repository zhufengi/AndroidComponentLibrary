package com.zf.land.eventbus;

import android.content.Context;
import android.util.Log;
import com.zf.land.BuildConfig;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/9/28
 * @description: eventbus的封装
 */
public class EventBusUtils {

    private final String TAG = "EventBusUtils";
    private static EventBusUtils instance;
    private EventBus eventBus ;
    private EventbusMessageCallback callback;
    private Object object = new Object();

    public  static EventBusUtils getInstance(){
        if (instance == null){
            instance = new EventBusUtils();
        }
        return instance;
    }

    /**
     * 使用自定义eventbus时使用（建议在Application中使用）
     */
    public void init(){
        eventBus = EventBus.builder()
                .logNoSubscriberMessages(false)
                .sendNoSubscriberEvent(false)
                .throwSubscriberException(BuildConfig.DEBUG)
                .build();
    }

    /**
     * eventbus 订阅
     * @param context
     */
    public void register(Context context){
        EventBus.getDefault().register(context);
    }

    /**
     * eventbus 订阅
     * @param context
     */
    public void customRegister(Context context){
        eventBus.register(context);
    }

    /**
     * eventbus 取消订阅
     * @param context
     */
    public void unRegister(Context context){
        EventBus.getDefault().unregister(context);
    }

    /**
     * eventbus 取消订阅
     * @param context
     */
    public void unCustomRegister(Context context){
        eventBus.unregister(context);
    }

    /**
     * eventbus 是否被订阅过
     * @param context
     * @return
     */
    public boolean isRegister(Context context){
        return EventBus.getDefault().isRegistered(context);
    }

    /**
     * eventbus 是否被订阅过
     * @param context
     * @return
     */
    public boolean isCustomRegister(Context context){
        return eventBus.isRegistered(context);
    }

    /**
     * eventbus 事件发送
     * @param event
     */
    public void post(Object event){
        EventBus.getDefault().post(event);
    }

    /**
     * eventbus 事件发送
     * @param event
     */
    public void customPost(Object event){
        eventBus.post(event);
    }

    /**
     * eventbus postSticky 发送
     * @param event
     */
    public void postSticky(Object event){
        EventBus.getDefault().postSticky(event);
    }

    /**
     * eventbus postSticky 发送
     * @param event
     */
    public void customPostSticky(Object event){
        eventBus.postSticky(event);
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.POSTING)
    public Object onMessageEvent(MessageEvent event){
        Log.i(TAG,"onMessageEvent :"+event.getMessage());
        return event.getMessage();
    }

}
