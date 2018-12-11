package com.zf.acl.rxjava;

import android.util.Log;

import com.orhanobut.logger.Logger;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/25
 * @description: RxLearnDoOnSubscribe
 * Observable 每发送 onSubscribe() 之前都会回调这个方法。
 */
public class RxLearnDoOnSubscribe {
    private static String TAG = "RxLearnDoOnSubscribe";

    public static void onRxLearnDoOnSubscribe(){
        Observable.just(1)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.d(TAG,"doOnSubscribe");
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
