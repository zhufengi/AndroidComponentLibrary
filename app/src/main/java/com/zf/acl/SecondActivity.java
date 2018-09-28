package com.zf.acl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.zf.land.base.BaseActivity;
import com.zf.land.eventbus.EventBusUtils;
import com.zf.land.eventbus.MessageEvent;

public class SecondActivity extends BaseActivity {

    private final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

    }


    @Override
    public Object onEventbusMessage(MessageEvent event) {
        Object ob=super.onEventbusMessage(event);
        Log.i(TAG,ob.toString());
        return super.onEventbusMessage(event);
    }
}
