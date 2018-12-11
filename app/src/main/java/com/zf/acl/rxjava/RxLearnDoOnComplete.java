package com.zf.acl.rxjava;


import android.util.Log;

import com.orhanobut.logger.Logger;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/25
 * @description: RxLearnDoOnComplete
 * Observable 每发送 onComplete() 之前都会回调这个方法。
 */
public class RxLearnDoOnComplete {
    private static String TAG = "RxLearnDoOnComplete";
    public static void onRxLearnDoOnComplete(){
        Observable.just(1)
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(TAG,"doOnComplete");
                    }
                }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG,"onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG,"onNext:"+integer);
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
