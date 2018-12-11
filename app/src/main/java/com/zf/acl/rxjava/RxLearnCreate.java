package com.zf.acl.rxjava;

import android.util.Log;

import com.orhanobut.logger.Logger;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/12
 * @description: RxLearnCreate,create学习
 */
public class RxLearnCreate {

    private static String TAG = "RxLearnCreate";

    /**
     * 正常写法
     */
    public static void onRxLearnCreate(){
        //创建被观察者
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                Log.d(TAG,"ObservableEmitter");
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });
        //创建观察者
        Observer observer = new Observer<Integer>(){
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG,"onSubscribe:"+d.toString());
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG," integer:"+integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"onError:"+e.toString());
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"onComplete");
            }
        };
        observable.subscribe(observer);
    }

    /**
     * 流式写法
     */
    public static void onRxLearnCreate2(){
        //创建被观察者
        Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                emitter.onNext("a");
                emitter.onNext("b");
                emitter.onNext("c");
            }
        })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String o) {
                        Log.d(TAG,""+o);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
