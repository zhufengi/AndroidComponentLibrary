package com.zf.acl.rxjava;

import com.zf.land.Logger;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/22
 * @description: RxLearnCombineLatest
 * combineLatest() 的作用与 zip() 类似，但是 combineLatest() 发送事件的序列是与发送的时间线有关的，
 * 当 combineLatest() 中所有的 Observable 都发送了事件，只要其中有一个 Observable 发送事件，
 * 这个事件就会和其他 Observable 最近发送的事件结合起来发送
 *
 */
public class RxLearnCombineLatest {
    private static String TAG = "RxLearnCombineLatest";

    public static void onRxLearnCombineLatest(){
        Observable.combineLatest(Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS)
                .map(new Function<Long, String>() {
                    @Override
                    public String apply(Long aLong) throws Exception {
                        return "A:" + aLong;
                    }
                }), Observable.intervalRange(0, 2, 2, 2, TimeUnit.SECONDS)
                .map(new Function<Long, String>() {
                    @Override
                    public String apply(Long aLong) throws Exception {
                        return "B:" + aLong;
                    }
                }), new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) throws Exception {
                return s+"=="+s2;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Logger.log(TAG,"onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Logger.log(TAG,"onNext:"+s);
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
