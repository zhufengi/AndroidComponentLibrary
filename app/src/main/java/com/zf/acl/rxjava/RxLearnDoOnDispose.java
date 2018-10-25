package com.zf.acl.rxjava;

import com.zf.land.Logger;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/25
 * @description: RxLearnDoOnDispose
 * 当调用 Disposable 的 dispose() 之后回调该方法。
 *
 */
public class RxLearnDoOnDispose {
    private static String TAG = "RxLearnDoOnDispose";

    /**
     * 经过验证doOnDispose之后的方法不会走
     */
    public static void onRxLearnDoOnDispose(){
        Observable.just(1)
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        Logger.log(TAG,"doOnDispose");
                    }
                }).subscribe(new Observer<Integer>() {

            Disposable disposable;
            @Override
            public void onSubscribe(Disposable d) {
                Logger.log(TAG,"onSubscribe");
//                disposable = d;
                d.dispose();
//                disposable.dispose();

            }

            @Override
            public void onNext(Integer integer) {
                Logger.log(TAG,"onNext:"+integer);
//                disposable.dispose();
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
