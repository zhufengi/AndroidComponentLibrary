package com.zf.acl.rxjava;

import com.zf.land.Logger;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/11/22
 * @description: RxLearnDoFinally
 * 在所有事件发送完毕之后回调该方法。
 */
public class RxLearnDoFinally {
    private String TAG = "RxLearnDoFinally";

    public static void onRxLearnDoFinally(){
        Observable.just(1,2,3,4)
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

            }
        });
    }
}
