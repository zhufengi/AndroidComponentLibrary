package com.zf.acl.rxjava;


import com.zf.land.Logger;

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
                Logger.log(TAG,"onSubscribe");
            }

            @Override
            public void onNext(Integer o) {
                Logger.log(TAG,"onNext:"+o);
            }

            @Override
            public void onError(Throwable e) {
                Logger.log(TAG,"onError");
            }

            @Override
            public void onComplete() {
                Logger.log(TAG,"onComplete");
            }
        };
        observable.subscribe(observer);
        integer = 300;
        observable.subscribe(observer);
    }
}
