package com.zf.acl.rxjava;

import com.zf.land.Logger;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/15
 * @description: RxLearnRangeLong，作用与 range() 一样，只是数据类型为 Long
 */
public class RxLearnRangeLong {
    private static String TAG = "RxLearnRangeLong";

    /**
     * 测试rangelong
     * @param start 开始数
     * @param count 总共数量
     */
    public static void onRxLearnRangeLong(long start, long count){
        Observable.rangeLong(start,count)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Logger.log(TAG,"onSubscribe");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Logger.log(TAG,"onNext："+aLong);
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
