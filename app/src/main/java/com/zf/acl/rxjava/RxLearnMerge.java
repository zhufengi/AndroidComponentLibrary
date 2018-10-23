package com.zf.acl.rxjava;

import com.zf.land.Logger;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/22
 * @description: RxLearnMerge
 * 这个方法与concat() 作用基本一样，只是concat() 是串行发送事件，而 merge() 并行发送事件。
 */
public class RxLearnMerge {
    private static String TAG = "onRxLearnMerge";

    public static void onRxLearnMerge(){
        Observable.merge(Observable.interval(1,TimeUnit.SECONDS).map(new Function<Long, String>() {
            @Override
            public String apply(Long aLong) throws Exception {
                return "A: "+aLong;
            }
        })
                ,Observable.interval(1,TimeUnit.SECONDS).map(new Function<Long, String>() {
            @Override
            public String apply(Long aLong) throws Exception {
                return "B: "+aLong;
            }
        })).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Logger.log(TAG,"onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Logger.log(TAG,"onNext :"+s);
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
