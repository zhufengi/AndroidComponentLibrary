package com.zf.acl.rxjava;

import android.util.Log;

import com.orhanobut.logger.Logger;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/24
 * @description: RxLearnDoAfterNext
 */
public class RxLearnDoAfterNext {
    private static String TAG = "RxLearnDoAfterNext";

    /**
     * 从结果就可以看出每发送一个事件之前都会回调 doOnNext 方法，并且可以取出 onNext() 发送的值。
     */
    public static void onRxLearnDoAfterNext(){
        Observable.just(1,2,3)
                .doAfterNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG,"doAfterNext:"+integer);
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG,"integer:"+integer);
            }
        });
    }
}
