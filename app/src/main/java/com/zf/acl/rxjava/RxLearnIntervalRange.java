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
 * @description: RxLearnIntervalRange可以指定发送事件的开始值和数量，其他与 interval() 的功能一样。
 */
public class RxLearnIntervalRange {
    private static String TAG = "RxLearnIntervalRange";

    /**
     * 测试
     * @param start 开始数
     * @param count 总共数量
     * @param initialDelay 延迟(即延迟后再开始周期)
     * @param period 周期
     * @param unit 时间
     */
    public static void onRxLearnIntervalRange(long start, long count, long initialDelay, long period, TimeUnit unit){
        Observable.intervalRange(start,count,initialDelay,period,unit)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Logger.log(TAG,"onSubscribe");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Logger.log(TAG,"onNext:"+aLong);
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
