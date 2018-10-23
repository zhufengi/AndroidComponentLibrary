package com.zf.acl.rxjava;

import com.zf.land.Logger;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/22
 * @description: RxLearnMergeArrayDelayError
 * 在 mergeArray() 方法当中，如果其中有一个被观察者发送了一个 Error 事件，那么就会停止发送事件，
 * 如果你想 onError() 事件延迟到所有被观察者都发送完事件后再执行的话，就可以使用 mergeArrayDelayError()
 */
public class RxLearnMergeArrayDelayError {
    private static String TAG = "RxLearnMergeArrayDelayError";

    public static void onRxLearnMergeArrayDelayError(){
        Observable.concatArrayDelayError(Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(5);
                emitter.onError(new NullPointerException());
            }
        }),Observable.just(1,2,3)).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Logger.log(TAG,"onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Logger.log(TAG,"onNext: "+integer);
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
