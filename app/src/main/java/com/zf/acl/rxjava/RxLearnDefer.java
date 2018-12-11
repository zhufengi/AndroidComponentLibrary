package com.zf.acl.rxjava;


import android.util.Log;

import com.orhanobut.logger.Logger;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/12
 * @description: RxLearnDefer
 */
public class RxLearnDefer {
    private static String TAG = "RxLearnDefer";

    /**integer 定义为成员变量*/
    static Integer integer = 100;

    public static void onRxLearnDefer(){
        final Integer finalInteger = integer;
        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> call() throws Exception {
                return Observable.just(finalInteger);
            }
        });
        integer = 200;
        Observer observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG,"onSubscribe");
            }

            @Override
            public void onNext(Integer o) {
                Log.d(TAG,"onNext:"+o);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"onComplete");
            }
        };
        observable.subscribe(observer);
        integer = 300;
        observable.subscribe(observer);
    }
}
