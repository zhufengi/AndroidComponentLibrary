package com.zf.acl;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;



/**
 * @author wang.xiaotong 2018/11/28
 * 播放音频工具类
 */
public class MediaPlayerUtils {

    private static final String TAG = "MediaPlayerUtils";

    private static MediaPlayerUtils instance;
    private MediaPlayer mediaPlayer = null;

    private MediaPlayerUtils(){}

    public static MediaPlayerUtils getInstance() {
        if (instance == null) {
            synchronized (MediaPlayerUtils.class) {
                if (instance == null) {
                    instance = new MediaPlayerUtils();
                }
            }
        }
        return instance;
    }

    /**
     * @param mcontext
     * @param resid
     * @param isLoop
     */
    private void mediaPlay(Context mcontext, int resid, boolean isLoop) {
        try {
            mediaPlayer = MediaPlayer.create(mcontext, resid);
            mediaPlayer.setLooping(isLoop);
            mediaPlayer.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /**
     * call状态播报语音
     * @param mcontext
     * @param status
     */
    public void exchangeMediaPlayer(Context mcontext, int status) {
        int max, current;
        AudioManager audioManager = (AudioManager) mcontext.getSystemService(Context.AUDIO_SERVICE);
        max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        current = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        Log.i(TAG, "max volume is: " + max + "current volume is: " + current);

        if (current == 0) {
            Log.w(TAG, "媒体音量为0,调整音量为最大值的7/10");
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, (int) max * 7 / 10, AudioManager.FLAG_PLAY_SOUND);
            try {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (status == 1) {
            //密钥协商中


            mediaPlay(mcontext, R.raw.connecting, false);
            onCompletion(1);
            Log.i(TAG, "call exchange processing");

        } else if (status == 2) {
            //密钥协商成功
            Log.i(TAG, "call exchange success");
            mediaPlay(mcontext, R.raw.connect_success, false);
            onCompletion(2);
        } else if (status == 3) {

            //密钥协商失败
            Log.w(TAG, "call exchange failed");
            mediaPlay(mcontext, R.raw.connect_failed, false);
            onCompletion(3);
        } else if (status == 4) {
            //停止响铃
            try {
                onReset();
                onStop();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        } else {
            //播放一秒的空白音
//                mediaPlay(mcontext, R.raw.sec, false);
            onStop();
        }
    }


    /**
     * 开始播放
     */
    public void onStart() {
        mediaPlayer.start();
    }

    /**
     * 暂停
     */
    public void onPause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    /**
     * 重置
     */
    public void onReset() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.reset();
        }
    }

    /**
     * 停止播放
     */
    public void onStop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    /**
     * 完成时回调
     */
    private void onCompletion(final int status){
        if (mediaPlayer != null){
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Log.i(TAG,"onCompletion");
                    if (status == 1){
                        Log.i(TAG,"onCompletion 1");
                    }else if (status == 2){
                        Log.i(TAG,"onCompletion 2");
                    }else if (status == 3){
                        Log.i(TAG,"onCompletion 3");
                    }

                }
            });
        }
    }
}
