package com.zf.acl.rxjava;

import com.zf.land.Logger;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/24
 * @description: RxLearnStartWith
 * 在发送事件之前追加事件，startWith() 追加一个事件，startWithArray() 可以追加多个事件。追加的事件会先发出。
 */
public class RxLearnStartWith {
    private static String TAG = "RxLearnStartWith";

    public static void onRxLearnStartWith(){
        Observable.just(7,5,3)
                .startWith(8)
                .startWithArray(4,2,1)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Logger.log(TAG,"integer:"+integer);
                    }
                });
    }
}
