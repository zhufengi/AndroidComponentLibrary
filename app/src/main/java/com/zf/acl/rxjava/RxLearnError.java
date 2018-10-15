package com.zf.acl.rxjava;

import com.zf.land.Logger;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/15
 * @description: RxLearnError
 */
public class RxLearnError {
    private static String TAG = "RxLearnError";

    public static void onRxLearnError(){
        Throwable throwable = new Throwable("错误消息");
        Observable.error(throwable)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Logger.log(TAG,"onSubscribe");
                    }

                    @Override
                    public void onNext(Object o) {
                        Logger.log(TAG,"onNext");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.log(TAG,"onError:"+e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Logger.log(TAG,"onComplete");
                    }
                });
    }
}
