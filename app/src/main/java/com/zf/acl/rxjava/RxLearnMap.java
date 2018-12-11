package com.zf.acl.rxjava;

import android.util.Log;

import com.orhanobut.logger.Logger;
import com.zf.acl.rxjava.bean.BookBean;
import com.zf.acl.rxjava.bean.BookType;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/16
 * @description: RxLearnMap可以将被观察者发送的数据类型转变成其他的类型
 */
public class RxLearnMap {
    private static String TAG = "RxLearnMap";

    /**
     * 普通场景转换
     */
    public static void onRxLearnMap() {
        Observable.just(1,2,3)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return "map :"+integer;
                    }
                }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG,"onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG,"onNext:"+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"onComplete");
            }
        });
    }

    /**
     * 一般场景转换
     * @param n
     */
    public static void onRxLearnMap2(int n){
        Observable.fromIterable(BookBean.buildBookListData(n))
                .map(new Function<BookBean, List<BookType>>() {
                    @Override
                    public List<BookType> apply(BookBean bookBean) throws Exception {
                        return bookBean.bookTypes;
                    }
                }).subscribe(new Observer<List<BookType>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG,"onSubscribe");
            }

            @Override
            public void onNext(List<BookType> bookTypes) {
                for (BookType bookType:bookTypes) {
                    Log.d(TAG,"onNext:"+bookType.life+"---"+bookType.history);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"onComplete");
            }
        });
    }
}
