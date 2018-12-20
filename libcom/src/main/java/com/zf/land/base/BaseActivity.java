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
import com.zf.land.mvp.XActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/9/28
 * @description: Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity implements XActivity {

    private final String TAG = "BaseActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutResID = initView(savedInstanceState);
        //如果initView返回0,框架则不会调用setContentView(),当然也不会 Bind ButterKnife
        if (layoutResID != 0) {
            setContentView(layoutResID);
            //绑定到butterknife
        }
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




    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
