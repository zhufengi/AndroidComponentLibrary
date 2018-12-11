package com.zf.acl.rxjava;

import android.util.Log;

import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/22
 * @description:
 * 会将多个被观察者合并，根据各个被观察者发送事件的顺序一个个结合起来，最终发送的事件数量会与源 Observable 中最少事件的数量一样。
 */
public class RxLearnZip {
    private static String TAG = "RxLearnZip";

    /**
     * 可以发现最终接收到的事件数量是4，那么为什么第1个 Observable 没有发送第5个事件呢？
     * 因为在这之前第2个 Observable 已经发送了 onComplete 事件，所以第1个 Observable 不会再发送事件。
     */
    public static void onRxLearnZip(){
        Observable.zip(Observable.intervalRange(0, 5, 2, 1, TimeUnit.SECONDS),
                Observable.intervalRange(0, 4, 2, 1,TimeUnit.SECONDS),
                new BiFunction<Long, Long, String>() {
                    @Override
                    public String apply(Long aLong, Long aLong2) throws Exception {
                        return aLong+""+aLong2;
                    }
                }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG,"onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG,"onNext :"+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"onComplete");
            }
        });
    }
}
