package com.zf.acl.rxjava;

import com.zf.land.Logger;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/12
 * @description: RxLearnFromIterable
 */
public class RxLearnFromIterable {
    private static String TAG = "RxLearnFromIterable";

    public static void onRxLearnFromIterable(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        Observable.fromIterable(list)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Logger.log(TAG,"onSubscribe:"+d.toString());
                    }

                    @Override
                    public void onNext(String s) {
                        Logger.log(TAG,"onNext:"+s);
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
