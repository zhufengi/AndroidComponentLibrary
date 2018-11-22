package com.zf.acl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class WakeLockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_wake_lock);
        try {
            Thread.sleep(16000);
            WakeLock wakeLock = new WakeLock(this,"1");
            wakeLock.turnScreenOn();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
