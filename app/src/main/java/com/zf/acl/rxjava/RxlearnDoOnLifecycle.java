package com.zf.acl.rxjava;

import android.util.Log;

import com.orhanobut.logger.Logger;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/25
 * @description: RxlearnDoOnLifecycle
 * 在回调 onSubscribe 之前回调该方法的第一个参数的回调方法，可以使用该回调方法决定是否取消订阅。
 */
public class RxlearnDoOnLifecycle {
    private static String TAG = "RxlearnDoOnLifecycle";

    public static void onRxlearnDoOnLifecycle(){
        Observable.just(1,2,3)
                .doOnLifecycle(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.d(TAG, "doOnLifecycle");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(TAG,"Action");
                    }
                }).doOnDispose(new Action() {
            @Override
            public void run() throws Exception {
                Log.d(TAG,"doOnDispose");
            }
        }).subscribe(new Observer<Integer>() {
            Disposable disposable;
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG,"onSubscribe");
                disposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG,"onNext:"+integer);
                disposable.dispose();
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
