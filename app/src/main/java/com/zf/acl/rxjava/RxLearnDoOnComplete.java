package com.zf.acl.rxjava;


import com.zf.land.Logger;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

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
                        Logger.log(TAG,"doOnComplete");
                    }
                }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Logger.log(TAG,"onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Logger.log(TAG,"onNext:"+integer);
            }

            @Override
            public void onError(Throwable e) {
                Logger.log(TAG,"onError");
            }

            @Override
            public void onComplete() {
                Logger.log(TAG,"onComplete");
            }
        });
    }
}
