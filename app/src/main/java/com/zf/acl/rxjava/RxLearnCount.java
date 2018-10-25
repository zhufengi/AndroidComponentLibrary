package com.zf.acl.rxjava;

import com.zf.land.Logger;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/24
 * @description: RxLearnCount
 * 返回被观察者发送事件的数量
 */
public class RxLearnCount {
    private static String TAG = "RxLearnCount";

    public static void onRxLearnCount(){
        Observable.just(1,2,3,4,5,99)
                .count()
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Logger.log(TAG,"count:"+aLong);
                    }
                });
    }
}
