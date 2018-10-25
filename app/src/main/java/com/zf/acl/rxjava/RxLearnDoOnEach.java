package com.zf.acl.rxjava;

import com.zf.land.Logger;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/24
 * @description: RxLearnDoOnEach
 * Observable 每发送一件事件之前都会先回调这个方法。
 */
public class RxLearnDoOnEach {
    private static String TAG = "RxLearnDoOnEach";

    /**
     * 从结果就可以看出每发送一个事件之前都会回调 doOnEach 方法，并且可以取出 onNext() 发送的值。一直到doOnEach取到的值为null为止
     */
    public static void onRxLearnDoOnEach(){
        Observable.just(1,2,3)
                .doOnEach(new Consumer<Notification<Integer>>() {
                    @Override
                    public void accept(Notification<Integer> integerNotification) throws Exception {
                        Logger.log(TAG,"doOnEach===>"+integerNotification.getValue());
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Logger.log(TAG,"integer:"+integer);
            }
        });
    }
}
