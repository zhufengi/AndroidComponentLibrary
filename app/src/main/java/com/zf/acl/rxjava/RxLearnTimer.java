package com.zf.acl.rxjava;

import android.util.Log;

import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/12
 * @description: RxLearnTimer
 */
public class RxLearnTimer {
    private static String TAG = "RxLearnTimer";

    public static void onRxLearnTimer(){

        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG,"onSubscribe");
                    }

                    @Override
                    public void onNext(Long l) {
                        Log.d(TAG,"onNext:"+l);
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
