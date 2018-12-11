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
 * @time: 2018/10/22
 * @description: RxLearnConcatArrayDelayError
 * 在 concatArray()方法当中，如果其中有一个被观察者发送了一个 Error 事件，那么就会停止发送事件，
 * 如果你想 onError() 事件延迟到所有被观察者都发送完事件后再执行的话，就可以使用  concatArrayDelayError()
 */
public class RxLearnConcatArrayDelayError {
    private static String TAG = "RxLearnConcatArrayDelayError";

    public static void onRxLearnConcatArrayDelayError(){
       Observable.concatArrayDelayError(Observable.create(new ObservableOnSubscribe<Integer>() {
           @Override
           public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
               emitter.onNext(5);
               emitter.onError(new NoClassDefFoundError());
           }
       }),Observable.just(1,2,3)).subscribe(new Observer<Integer>() {
           @Override
           public void onSubscribe(Disposable d) {
               Log.d(TAG,"onSubscribe");
           }

           @Override
           public void onNext(Integer integer) {
               Log.d(TAG,"onNext: "+integer);
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
