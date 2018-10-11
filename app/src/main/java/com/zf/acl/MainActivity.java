package com.zf.acl;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import com.zf.land.Logger;
import com.zf.land.base.BaseActivity;
import com.zf.land.eventbus.EventBusUtils;
import com.zf.land.eventbus.MessageEvent;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {
    private final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this,SecondActivity.class));
        EventBusUtils.getInstance().postSticky(new MessageEvent("MainActivity123"));

        onLearnRxJava1();
        onLearnRxJava2();
        onLearnRxjava3();
        onLearnRxjava4();
        onLearnRxJava5();
        onLearnRxJava6();
        testObservable();
    }

    @Override
    public Object onMessageEvent(MessageEvent messageEvent) {
        Log.i(TAG,""+messageEvent.getMessage());
        return super.onMessageEvent(messageEvent);
    }

    private void onLearnRxJava1(){
        //创建被观察者
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                Logger.log(TAG,"ObservableEmitter");
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });
        //创建观察者
        Observer observer = new Observer<Integer>(){
            @Override
            public void onSubscribe(Disposable d) {
                Logger.log(TAG,"onSubscribe:"+d.toString());
            }

            @Override
            public void onNext(Integer integer) {
                Logger.log(TAG,"onLearnRxJava1 integer:"+integer);
            }

            @Override
            public void onError(Throwable e) {
                Logger.log(TAG,"onError:"+e.toString());
            }

            @Override
            public void onComplete() {
                Logger.log(TAG,"onComplete");
            }
        };
        observable.subscribe(observer);
    }

    private void onLearnRxJava2(){
        //创建被观察者
         Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                emitter.onNext("a");
                emitter.onNext("b");
                emitter.onNext("c");
            }
        })
        .subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String o) {
                Logger.log(TAG,"onLearnRxJava2:"+o);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void onLearnRxjava3(){
        Observable.just(4,5,6)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Logger.log(TAG,"onLearnRxjava3 integer:"+integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void onLearnRxjava4(){
        String [] strings = {"7","8","9"};
        Observable.fromArray(strings)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String ints) {
                        Logger.log(TAG,"onLearnRxjava4 ints:"+ints);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void onLearnRxJava5(){
        Observable.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Logger.log(TAG,"fromCallable integer:"+integer);
            }
        });
    }

    private void onLearnRxJava6(){
        final FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Logger.log(TAG,"FutureTask call");
                return "FutureTask";
            }
        });
        Observable.fromFuture(futureTask)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        futureTask.run();
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Logger.log(TAG,"fromFuture  accept:"+s);
            }
        });
    }

    private void testObservable(){
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                emitter.onNext("啦啦啦啦啦啦啦啦");
                Logger.log(TAG,"testObservable");
            }
        });
        Observer observer = SecondActivity.testObserver();
        observable.subscribe(observer);
    }
}
