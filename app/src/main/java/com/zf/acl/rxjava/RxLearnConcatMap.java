package com.zf.acl.rxjava;

import com.zf.acl.rxjava.bean.BookBean;
import com.zf.acl.rxjava.bean.BookType;
import com.zf.land.Logger;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/17
 * @description: RxLearnConcatMap
 * 和 flatMap() 基本上是一样的，只不过 concatMap() 转发出来的事件是有序的，而 flatMap() 是无序的。
 */
public class RxLearnConcatMap {
    private static String TAG = "RxLearnConcatMap";

    public static void onRxLearnConcatMap(int n){
        if (n<2){
            return;
        }
        Observable.fromIterable(BookBean.buildBookListData(n))
                .concatMap(new Function<BookBean, ObservableSource<BookBean>>() {
                    @Override
                    public ObservableSource<BookBean> apply(BookBean bookBean) throws Exception {
                        if ("name1".equals(bookBean.name)){
                            return Observable.just(bookBean).delay(5,TimeUnit.SECONDS);
                        }
                        return Observable.just(bookBean);
                    }
                }).subscribe(new Observer<BookBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                Logger.log(TAG,"onSubscribe");
            }

            @Override
            public void onNext(BookBean bookBean) {
                Logger.log(TAG,"onNext:"+bookBean.name);
            }

            @Override
            public void onError(Throwable e) {
                Logger.log(TAG,"onSubscribe");
            }

            @Override
            public void onComplete() {
                Logger.log(TAG,"onSubscribe");
            }
        });
    }
}
