package com.zf.acl.rxjava;

import android.util.Log;

import com.orhanobut.logger.Logger;
import com.zf.acl.rxjava.bean.BookBean;
import com.zf.acl.rxjava.bean.BookType;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/17
 * @description: RxLearnFlatMap
 * 这个方法可以将事件序列中的元素进行整合加工，返回一个新的被观察者。
 * flatMap() 其实与 map() 类似，但是 flatMap() 返回的是一个 Observerable。现在用一个例子来说明 flatMap() 的用法。
 */
public class RxLearnFlatMap {
    private static String TAG = "RxLearnFlatMap";

    /**
     * 一般场景 双层flatMap
     * @param n
     */
    public static void onRxLearnFlatMap(int n){
        Observable.fromIterable(BookBean.buildBookListData(n))
                .flatMap(new Function<BookBean, ObservableSource<BookType>>() {
                    @Override
                    public ObservableSource<BookType> apply(BookBean bookBean) throws Exception {
                        return Observable.fromIterable(bookBean.bookTypes);
                    }
                }).flatMap(new Function<BookType, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(BookType bookType) throws Exception {
                return Observable.just(bookType.life+"---"+bookType.history);
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
}
