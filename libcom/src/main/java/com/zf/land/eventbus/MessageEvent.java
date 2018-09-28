package com.zf.land.eventbus;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/9/28
 * @description: eventbus的消息事件
 */
public class MessageEvent {

    private Object object;

    public MessageEvent(Object object){
        this.object = object;
    }

    public Object getMessage() {
        return object;
    }

}
