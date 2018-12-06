package com.zf.acl;

import android.os.Bundle;
import android.util.Log;

import com.zf.acl.rxjava.RxLearnBuffer;
import com.zf.acl.rxjava.RxLearnCollect;
import com.zf.acl.rxjava.RxLearnCombineLatest;
import com.zf.acl.rxjava.RxLearnConcat;
import com.zf.acl.rxjava.RxLearnConcatArray;
import com.zf.acl.rxjava.RxLearnConcatArrayDelayError;
import com.zf.acl.rxjava.RxLearnConcatMap;
import com.zf.acl.rxjava.RxLearnCount;
import com.zf.acl.rxjava.RxLearnCreate;
import com.zf.acl.rxjava.RxLearnDefer;
import com.zf.acl.rxjava.RxLearnDelay;
import com.zf.acl.rxjava.RxLearnDoAfterNext;
import com.zf.acl.rxjava.RxLearnDoAfterTerminate;
import com.zf.acl.rxjava.RxLearnDoOnComplete;
import com.zf.acl.rxjava.RxLearnDoOnDispose;
import com.zf.acl.rxjava.RxLearnDoOnEach;
import com.zf.acl.rxjava.RxLearnDoOnError;
import com.zf.acl.rxjava.RxLearnDoOnNext;
import com.zf.acl.rxjava.RxLearnDoOnSubscribe;
import com.zf.acl.rxjava.RxLearnDoOnTerminate;
import com.zf.acl.rxjava.RxLearnEmpty;
import com.zf.acl.rxjava.RxLearnError;
import com.zf.acl.rxjava.RxLearnFlatMap;
import com.zf.acl.rxjava.RxLearnFromArray;
import com.zf.acl.rxjava.RxLearnFromCallable;
import com.zf.acl.rxjava.RxLearnFromFuture;
import com.zf.acl.rxjava.RxLearnFromIterable;
import com.zf.acl.rxjava.RxLearnGroupBy;
import com.zf.acl.rxjava.RxLearnInterval;
import com.zf.acl.rxjava.RxLearnIntervalRange;
import com.zf.acl.rxjava.RxLearnJust;
import com.zf.acl.rxjava.RxLearnMap;
import com.zf.acl.rxjava.RxLearnMerge;
import com.zf.acl.rxjava.RxLearnMergeArrayDelayError;
import com.zf.acl.rxjava.RxLearnNever;
import com.zf.acl.rxjava.RxLearnRangeLong;
import com.zf.acl.rxjava.RxLearnReduce;
import com.zf.acl.rxjava.RxLearnScan;
import com.zf.acl.rxjava.RxLearnStartWith;
import com.zf.acl.rxjava.RxLearnTimer;
import com.zf.acl.rxjava.RxLearnWindow;
import com.zf.acl.rxjava.RxLearnZip;
import com.zf.acl.rxjava.RxlearnDoOnLifecycle;
import com.zf.land.Logger;
import com.zf.land.base.BaseActivity;
import com.zf.land.eventbus.MessageEvent;
import com.zf.land.utils.AppConfigUtils;

import java.util.concurrent.TimeUnit;

public class MainActivity extends BaseActivity {
    private final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startActivity(new Intent(this,SecondActivity.class));
//        EventBusUtils.getInstance().postSticky(new MessageEvent("MainActivity123"));

//        onLearn();
        String[] permissions = AppConfigUtils.getAppPermissions(this, "com.zf.acl");
//        AppConfigUtils.killProcesses(this,"com.zf.acl");
        Logger.log("111",AppConfigUtils.getAppPermissions(this,"com.zf.acl")+"");

    }

    @Override
    public Object onMessageEvent(MessageEvent messageEvent) {
        Log.i(TAG,""+messageEvent.getMessage());
        return super.onMessageEvent(messageEvent);
    }

    private void onLearn(){
        RxLearnCreate.onRxLearnCreate();
        RxLearnCreate.onRxLearnCreate2();
        RxLearnJust.onRxLearnJust();
        RxLearnFromArray.onRxLearnFromArray();
        RxLearnFromCallable.onRxLearnFromCallable();
        RxLearnFromFuture.onRxLearnFromFuture();
        RxLearnFromIterable.onRxLearnFromIterable();
        RxLearnDefer.onRxLearnDefer();
        RxLearnTimer.onRxLearnTimer();
        RxLearnInterval.onRxLearnInterval(3,2,TimeUnit.SECONDS);
        RxLearnIntervalRange.onRxLearnIntervalRange(3,7,4,1,TimeUnit.SECONDS);
        RxLearnRangeLong.onRxLearnRangeLong(4,2);
        RxLearnEmpty.onRxLearnEmpty();
        RxLearnNever.onRxLearnNever();
        RxLearnError.onRxLearnError();
        RxLearnMap.onRxLearnMap();
        RxLearnMap.onRxLearnMap2(5);
        RxLearnFlatMap.onRxLearnFlatMap(5);
        RxLearnConcatMap.onRxLearnConcatMap(5);
        RxLearnBuffer.onRxLearnBuffer();
        RxLearnGroupBy.onRxLearnGroupBy();
        RxLearnScan.onRxLearnScan();
        RxLearnConcat.onRxLearnConcat();
        RxLearnConcatArray.onRxLearnConcatArray();
        RxLearnWindow.onRxLearnWindow(3);
        RxLearnMerge.onRxLearnMerge();
        RxLearnConcatArrayDelayError.onRxLearnConcatArrayDelayError();
        RxLearnMergeArrayDelayError.onRxLearnMergeArrayDelayError();
        RxLearnZip.onRxLearnZip();
        RxLearnCombineLatest.onRxLearnCombineLatest();
        RxLearnReduce.onRxLearnReduce();
        RxLearnCollect.onRxLearnCollect();
        RxLearnStartWith.onRxLearnStartWith();
        RxLearnCount.onRxLearnCount();
        RxLearnDelay.onRxLearnDelay();
        RxLearnDoOnEach.onRxLearnDoOnEach();
        RxLearnDoOnNext.onRxLearnDoOnNext();
        RxLearnDoAfterNext.onRxLearnDoAfterNext();
        RxLearnDoOnComplete.onRxLearnDoOnComplete();
        RxLearnDoOnError.onRxLearnDoOnError();
        RxLearnDoOnSubscribe.onRxLearnDoOnSubscribe();
        RxLearnDoOnDispose.onRxLearnDoOnDispose();
        RxlearnDoOnLifecycle.onRxlearnDoOnLifecycle();
        RxLearnDoOnTerminate.onRxLearnDoOnTerminate();
        RxLearnDoAfterTerminate.onRxLearnDoAfterTerminate();
    }
}
