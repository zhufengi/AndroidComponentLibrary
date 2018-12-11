package com.zf.acl.rxjava;

import android.util.Log;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/23
 * @description: RxLearnCollect
 * 将数据收集到数据结构当中。
 */
public class RxLearnCollect {
    private static String TAG = "RxLearnCollect";
    public static void onRxLearnCollect(){
        Observable.just(1,2,3,4,5)
                .collect(new Callable<ArrayList<Integer>>() {
                    @Override
                    public ArrayList<Integer> call() throws Exception {
                        return new ArrayList<>();
                    }
                }, new BiConsumer<ArrayList<Integer>, Integer>() {
                    @Override
                    public void accept(ArrayList<Integer> integers, Integer integer) throws Exception {
                        integers.add(integer);
                    }
                }).subscribe(new Consumer<ArrayList<Integer>>() {
            @Override
            public void accept(ArrayList<Integer> integers) throws Exception {
                Log.d(TAG, "===============accept " + integers);
                for (Integer integer:integers){
                    Log.d(TAG,"accept ==>"+integer);
                }

            }
        });
    }
}
