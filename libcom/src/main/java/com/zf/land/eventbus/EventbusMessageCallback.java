package com.zf.land.eventbus;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/9/28
 * @description: EventbusMessageCallback
 */
public interface EventbusMessageCallback {
    void onMessageCallback(Object event);
}
