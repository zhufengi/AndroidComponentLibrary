package com.zf.acl.rxjava;

import com.zf.land.Logger;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/19
 * @description: RxLearnConcat
 *可以将多个观察者组合在一起成一个观察者，然后按照之前发送顺序发送事件。需要注意的是，concat() 最多只可以发送4个事件。
 */
public class RxLearnConcat {
    private static String TAG = "RxLearnConcat";

    public static void onRxLearnConcat(){
        Observable.concat(Observable.just(1,2),
                Observable.just(3,4),
                Observable.just(5,6),
                Observable.just(7,8))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Logger.log(TAG,"onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Logger.log(TAG,"onNext:"+integer);
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
