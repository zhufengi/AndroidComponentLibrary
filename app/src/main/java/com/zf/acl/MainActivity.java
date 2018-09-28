package com.zf.acl;

import android.os.Bundle;

import com.zf.land.base.BaseActivity;
import com.zf.land.eventbus.EventBusUtils;
import com.zf.land.eventbus.MessageEvent;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBusUtils.getInstance().postSticky(new MessageEvent("123"));
    }
}
