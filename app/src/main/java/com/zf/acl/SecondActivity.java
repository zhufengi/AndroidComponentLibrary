package com.zf.acl;

import android.os.Bundle;
import android.util.Log;

import com.zf.land.base.BaseActivity;
import com.zf.land.eventbus.MessageEvent;


public class SecondActivity extends BaseActivity {

    private final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        EventBusUtils.getInstance().post(new MessageEvent("MainActivity123556"));
    }

    @Override
    public Object onMessageEvent(MessageEvent messageEvent) {
        Log.i(TAG,""+messageEvent.getMessage());
        return super.onMessageEvent(messageEvent);
    }
}
