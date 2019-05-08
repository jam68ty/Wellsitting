package com.example.wellsitting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.Toast;

public class Service extends android.app.Service {

    MediaPlayer mediaPlayer;
    //int i=0;
    int sum;

    //Log.d("mmm","check");
    //int remains=30000;

    @Override
    public IBinder onBind(Intent arg0) {
// TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
// TODO Auto-generated method stub
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.twice);
    }


    @Override
    public void onStart(Intent intent, int startId) {
        Toast.makeText(this, "Service start", Toast.LENGTH_SHORT).show();
        //Log.d("fff","finish");
        Log.d("mmm","check");
        timer.start();
/*
        // TODO Auto-generated method stub
        // SystemClock.elapsedRealtime()方法會回傳從開機到現在的時間
        // 把這個時間做為一個Base,從這個Base開始計時
        timer.setBase(SystemClock.elapsedRealtime() + escapeTime);
        timer.start();

        count.setBase(SystemClock.elapsedRealtime() + 1800000);
        count.setCountDown(true);
        count.start();
 */

    }
    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(this, "Service stop", Toast.LENGTH_SHORT).show();
        timer.cancel();
        mediaPlayer.stop();
    }

    //倒數計時設定
    CountDownTimer timer = new CountDownTimer(10000, 1000){
        @Override
        public void onTick(long millisUntilFinished) {
            SharedPreferences.Editor editor = getSharedPreferences("TIMER", MODE_PRIVATE).edit();
            editor.putLong("REMAINS",millisUntilFinished);
            editor.apply();


            SharedPreferences prefs = getSharedPreferences("TIMERSUM", MODE_PRIVATE);
            Integer pre_sum = prefs.getInt("SUM", 0);
            if(pre_sum!=null){
                sum=pre_sum+1;
            }else{
                sum=0;
            }
            SharedPreferences.Editor time_sum = getSharedPreferences("TIMERSUM", MODE_PRIVATE).edit();
            time_sum.putInt("SUM",sum);
            time_sum.apply();



            //mSampleTv.setText(millisUntilFinished / 1000 + "s");
        }
        @Override
        public void onFinish() {
            mediaPlayer.start();
            //Log.d("fff","finish");
        }
    };




}
