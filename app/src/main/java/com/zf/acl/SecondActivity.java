package com.zf.acl;

import android.os.Bundle;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.zf.land.base.BaseActivity;
import com.zf.land.eventbus.MessageEvent;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class SecondActivity extends BaseActivity {

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        EventBusUtils.getInstance().post(new MessageEvent("MainActivity123556"));
//        testObserver();
    }

    @Override
    public Object onMessageEvent(MessageEvent messageEvent) {
        Log.i(TAG,""+messageEvent.getMessage());
        return super.onMessageEvent(messageEvent);
    }

    public static Observer testObserver(){
        return  new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String o) {
                Log.d(TAG,"testObserver:"+o);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
}
