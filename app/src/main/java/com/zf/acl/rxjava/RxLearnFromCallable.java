package com.zf.acl.rxjava;

import com.zf.land.Logger;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/12
 * @description: RxLearnFromCallable
 */
public class RxLearnFromCallable {
    private static String TAG = "RxLearnFromCallable";

    public static void onRxLearnFromCallable(){
        Observable.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Logger.log(TAG," integer:"+integer);
            }
        });
    }
}
