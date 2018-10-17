package com.zf.acl;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import com.zf.acl.rxjava.RxLearnConcatMap;
import com.zf.acl.rxjava.RxLearnCreate;
import com.zf.acl.rxjava.RxLearnDefer;
import com.zf.acl.rxjava.RxLearnEmpty;
import com.zf.acl.rxjava.RxLearnError;
import com.zf.acl.rxjava.RxLearnFlatMap;
import com.zf.acl.rxjava.RxLearnFromArray;
import com.zf.acl.rxjava.RxLearnFromCallable;
import com.zf.acl.rxjava.RxLearnFromFuture;
import com.zf.acl.rxjava.RxLearnFromIterable;
import com.zf.acl.rxjava.RxLearnInterval;
import com.zf.acl.rxjava.RxLearnIntervalRange;
import com.zf.acl.rxjava.RxLearnJust;
import com.zf.acl.rxjava.RxLearnMap;
import com.zf.acl.rxjava.RxLearnNever;
import com.zf.acl.rxjava.RxLearnRangeLong;
import com.zf.acl.rxjava.RxLearnTimer;
import com.zf.land.Logger;
import com.zf.land.base.BaseActivity;
import com.zf.land.eventbus.EventBusUtils;
import com.zf.land.eventbus.MessageEvent;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;

public class MainActivity extends BaseActivity {
    private final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this,SecondActivity.class));
        EventBusUtils.getInstance().postSticky(new MessageEvent("MainActivity123"));

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
        testObservable();
    }

    @Override
    public Object onMessageEvent(MessageEvent messageEvent) {
        Log.i(TAG,""+messageEvent.getMessage());
        return super.onMessageEvent(messageEvent);
    }

    private void testObservable(){
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                emitter.onNext("啦啦啦啦啦啦啦啦");
                Logger.log(TAG,"testObservable");
            }
        });
        Observer observer = SecondActivity.testObserver();
        observable.subscribe(observer);
    }
}
