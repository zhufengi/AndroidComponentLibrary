package com.zf.land.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;

import com.zf.land.eventbus.EventBusUtils;
import com.zf.land.eventbus.EventbusMessageCallback;
import com.zf.land.eventbus.MessageEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/9/28
 * @description: Activity基类
 */
public class BaseActivity extends AppCompatActivity {

    private final String TAG = "BaseActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBusUtils.getInstance().register(this);

    }

    @Subscribe
    public Object onMessageEvent(MessageEvent messageEvent){
        Log.i(TAG,""+messageEvent.getMessage());
       return EventBusUtils.getInstance().onMessageEvent(messageEvent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBusUtils.getInstance().isRegister(this)){
            EventBusUtils.getInstance().unRegister(this);
        }
    }
}
