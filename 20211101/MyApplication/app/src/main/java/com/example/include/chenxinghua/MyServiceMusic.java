package com.example.include.chenxinghua;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MyServiceMusic extends Service {
    private static final String TAG="MyServiceMusic";
    public MediaPlayer mediaPlayer;
    public MyServiceMusic() {
    }
    class MyBinder extends Binder{
        public void Play(){
            try {
                if (mediaPlayer==null){
                    mediaPlayer=MediaPlayer.create(MyServiceMusic.this,R.raw.cuoweishikong);//定位音乐路径
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mediaPlayer.start();
                        }
                    });
                }else {
                    int pos=getCurrentPro();
                    mediaPlayer.seekTo(pos);
                    try {
                        mediaPlayer.prepare();
                    }catch (IllegalStateException e){
                       e.printStackTrace();
                    }
                    mediaPlayer.start();

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        public void Pause(){
            mediaPlayer=MediaPlayer.create(MyServiceMusic.this,R.raw.cuoweishikong);
            if (mediaPlayer!=null&&mediaPlayer.isPlaying()){
                mediaPlayer.pause();//暂停
            }else if (mediaPlayer!=null&&(!mediaPlayer.isPlaying())){
                mediaPlayer.start();//播放
            }
            mediaPlayer.release();
        }
    }
    public void onCreate(){
        super.onCreate();
    }
    public int getCurrentPro(){
        mediaPlayer=MediaPlayer.create(MyServiceMusic.this,R.raw.cuoweishikong);
        if (mediaPlayer!=null&mediaPlayer.isPlaying()){
            return mediaPlayer.getCurrentPosition();
        }else if (mediaPlayer != null&(!mediaPlayer.isPlaying())) {
            return mediaPlayer.getCurrentPosition();
        }
        return 0;
    }
    public void onDestroy(){
        if (mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;
        }
        super.onDestroy();
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return new MyBinder();
    }
}
