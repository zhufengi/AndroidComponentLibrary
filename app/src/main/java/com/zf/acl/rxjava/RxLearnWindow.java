package com.zf.acl.rxjava;

import com.zf.land.Logger;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/19
 * @description: RxLearnWindow
 * 发送指定数量的事件时，就将这些事件分为一组。
 * window 中的 count 的参数就是代表指定的数量，例如将 count 指定为2，那么每发2个数据就会将这2个数据分成一组。
 */
public class RxLearnWindow {
    private static String TAG = "RxLearnWindow";

    public static void onRxLearnWindow(int window){
        List<Integer> list = new ArrayList<>();
        for (int i=0;i<2 * window;i++){
            list.add(i);
        }
        Observable.fromIterable(list)
                .window(window)
                .subscribe(new Observer<Observable<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Logger.log(TAG,"onSubscribe");
                    }

                    @Override
                    public void onNext(Observable<Integer> integerObservable) {
                        integerObservable.subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                Logger.log(TAG,"onSubscribe");
                            }

                            @Override
                            public void onNext(Integer integer) {
                                Logger.log(TAG,"onNext:===>"+integer);
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
