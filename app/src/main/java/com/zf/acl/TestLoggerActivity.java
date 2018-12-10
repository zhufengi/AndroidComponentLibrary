package com.zf.acl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.zf.land.logger.CustomLogCatStrategy;


public class TestLoggerActivity extends AppCompatActivity {
    private static String TAG = "TestLoggerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_logger);
        initLogger();
        testlog();
    }

    private void initLogger(){
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(3)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                .logStrategy(new CustomLogCatStrategy()) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag("yonbor605")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }

    private void testlog(){
        Logger.d(TAG,"1");
        Logger.d(TAG,"2");
        Logger.d(TAG,"3");
        Logger.d(TAG,"4");
        Logger.i(TAG,"ashdkjhkh");
        Logger.w(TAG,"adksl;k;lk");
        Logger.e(TAG,"asdqwe3223");
    }
}
