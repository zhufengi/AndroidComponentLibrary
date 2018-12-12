package com.zf.acl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zf.land.comm.utils.LogUtils;

public class LogUtilsActivity extends AppCompatActivity {

    private static String TAG = "LogUtilsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_utils);
        test();
    }

    private void test(){
        LogUtils.d(TAG,"123");
        LogUtils.i(TAG,"ajdlkj");
        LogUtils.w(TAG,"jlkjkljlk");
        LogUtils.e(TAG,"adsjlijlas");
    }


}
