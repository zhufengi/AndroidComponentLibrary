package com.zf.acl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestVoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_voice);

        findViewById(R.id.bt_connecting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayerUtils.getInstance().exchangeMediaPlayer(TestVoiceActivity.this,1);
            }
        });
        findViewById(R.id.bt_connect_succ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayerUtils.getInstance().exchangeMediaPlayer(TestVoiceActivity.this,2);
            }
        });
        findViewById(R.id.bt_connect_failed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayerUtils.getInstance().exchangeMediaPlayer(TestVoiceActivity.this,3);
            }
        });
    }


}
