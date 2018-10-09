package com.zf.acl;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import com.zf.land.base.BaseActivity;
import com.zf.land.eventbus.EventBusUtils;
import com.zf.land.eventbus.MessageEvent;

public class MainActivity extends BaseActivity {
    private final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this,SecondActivity.class));
        EventBusUtils.getInstance().postSticky(new MessageEvent("MainActivity123"));
    }

    @Override
    public void onMessageEvent(MessageEvent messageEvent) {
        super.onMessageEvent(messageEvent);
        Log.i(TAG,"MainActivity123");
    }
}
