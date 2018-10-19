package com.zf.acl.rxjava;

import com.zf.land.Logger;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/19
 * @description: RxLearnConcatArray
 * 与 concat() 作用一样，不过 concatArray() 可以发送多于 4 个被观察者。
 */
public class RxLearnConcatArray {
    private static String TAG = "RxLearnConcatArray";

    public static void onRxLearnConcatArray(){
        Observable.concatArray(Observable.just(1,2),
                Observable.just(3,4),
                Observable.just(5,6),
                Observable.just(7,8),
                Observable.just(9,0))
                .subscribe(new Observer<Integer>() {
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
