package com.zf.acl.rxjava;

import android.util.Log;

import com.orhanobut.logger.Logger;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/18
 * @description: RxLearnBuffer
 * 从需要发送的事件当中获取一定数量的事件，并将这些事件放到缓冲区当中一并发出。
 * buffer 有两个参数，一个是 count，另一个 skip。count 缓冲区元素的数量，skip 就代表缓冲区满了之后，
 * 发送下一次事件序列的时候要跳过多少元素。
 */
public class RxLearnBuffer {
    private static String TAG = "RxLearnBuffer";

    /**
     * 每次发送事件，指针都会往后移动一个元素再取值，直到指针移动到没有元素的时候就会停止取值。
     */
    public static void onRxLearnBuffer(){
        Observable.just(1,2,3,4,5,6)
                .buffer(2,2)
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG,"onSubscribe");
                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        Log.d(TAG,"当前缓冲区大小："+integers.size());
                        for (Integer integer:integers) {
                            Log.d(TAG,"当前缓冲区数："+integer);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"onSubscribe");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG,"onSubscribe");
                    }
                });
    }
}
