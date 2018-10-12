package com.zf.acl.rxjava;

import com.zf.land.Logger;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/12
 * @description: RxLearnFromFuture
 */
public class RxLearnFromFuture {
    private static String TAG = "RxLearnFromFuture";

    public static void onRxLearnFromFuture(){
        final FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Logger.log(TAG," call");
                return "FutureTask";
            }
        });
        Observable.fromFuture(futureTask)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        futureTask.run();
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Logger.log(TAG,"  accept:"+s);
            }
        });
    }

}
