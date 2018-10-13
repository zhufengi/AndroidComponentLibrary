package com.zf.acl.rxjava;

import com.zf.land.Logger;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/13
 * @description: RxLearnInterval 每隔一段时间就会发送一个事件，这个事件是从0开始，不断增1的数字。
 */
public class RxLearnInterval {
    private static String TAG = "RxLearnInterval";

    /**
     * 每2s发送一次，递增(从0开始)
     * interval() 第三个方法的 initialDelay 参数，这个参数的意思就是 onSubscribe 回调之后，再次回调 onNext 的间隔时间。（延迟后再走周期）
     */
    public static void onRxLearnInterval(long initialDelay, long period, TimeUnit unit){
        Observable.interval(initialDelay,period,unit)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Logger.log(TAG,"onSubscribe");
                    }

                    @Override
                    public void onNext(Long l) {
                        Logger.log(TAG,"onNext:"+l);
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
