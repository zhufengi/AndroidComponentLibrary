package com.zf.acl.rxjava;

import com.zf.land.Logger;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/18
 * @description: RxLearnGroupBy将数据进行分组,然后返回一个观察者
 */
public class RxLearnGroupBy {
    private static String TAG = "RxLearnGroupBy";

    public static void onRxLearnGroupBy(){
        Observable.just(1,2,3,4,5,6,7,8,9)
                .groupBy(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        return integer % 2;
                    }
                }).subscribe(new Observer<GroupedObservable<Integer, Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Logger.log(TAG,"onSubscribe");
            }

            @Override
            public void onNext(final GroupedObservable<Integer, Integer> integerIntegerGroupedObservable) {
                Logger.log(TAG,"onNext");
                integerIntegerGroupedObservable.subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Logger.log(TAG,"onNext===> name:"+integerIntegerGroupedObservable.getKey()+",integer:"+integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                Logger.log(TAG,"onSubscribe");
            }

            @Override
            public void onComplete() {
                Logger.log(TAG,"onSubscribe");
            }
        });
    }
}
