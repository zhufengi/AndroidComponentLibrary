package com.zf.acl;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import com.zf.acl.rxjava.RxLearnCreate;
import com.zf.acl.rxjava.RxLearnFromArray;
import com.zf.acl.rxjava.RxLearnFromCallable;
import com.zf.acl.rxjava.RxLearnFromFuture;
import com.zf.acl.rxjava.RxLearnJust;
import com.zf.land.Logger;
import com.zf.land.base.BaseActivity;
import com.zf.land.eventbus.EventBusUtils;
import com.zf.land.eventbus.MessageEvent;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

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
