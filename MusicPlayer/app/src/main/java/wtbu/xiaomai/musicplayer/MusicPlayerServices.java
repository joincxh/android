package wtbu.xiaomai.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class MusicPlayerServices extends Service {

    private MediaPlayer mediaPlayer; // 多媒体对象
    private Timer timer; // 时钟对象

    public MusicPlayerServices() {}

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicControl(); // 绑定服务的时候，把音乐控制类实例化
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 实例化多媒体对象
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    // 增加计时器
    public void addTimer() {
        if (timer == null) {
            timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    int duration = mediaPlayer.getDuration(); // 获取歌曲总时长
                    int currentPos = mediaPlayer.getCurrentPosition(); // 获取当前播放进度
                    Message msg = MainActivity.handler.obtainMessage(); // 创建消息对象
                    // 将音乐的总时长和播放进度封装至消息对象中
                    Bundle bundle = new Bundle();
                    bundle.putInt("duration", duration);
                    bundle.putInt("currentPosition", currentPos);
                    msg.setData(bundle);
                    // 将消息发送到主线程的消息队列
                    MainActivity.handler.sendMessage(msg);
                }
            };
            //开始计时任务后的5毫秒，第一次执行task任务，以后每500毫秒执行一次
            timer.schedule(task, 5, 500);
        }
    }

    class MusicControl extends Binder {
        // 播放音乐
        public void play() {
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.skyhigh);
            mediaPlayer.start();
            addTimer();
        }
        // 暂停
        public void pause() {
            mediaPlayer.pause(); // 暂停音乐
        }
        // 继续
        public void resume() {
            mediaPlayer.start(); // 播放，不会重置
        }
        // 停止
        public void stop() {
            mediaPlayer.stop(); // 停止音乐
            mediaPlayer.release();
            try {
                timer.cancel();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 打带
        public void seekTo(int ms) {
            mediaPlayer.seekTo(ms);
        }
    }
}
