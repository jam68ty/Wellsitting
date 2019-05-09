package com.example.wellsitting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Service extends android.app.Service {


    //DatabaseReference reference;
    MediaPlayer mediaPlayer;
    //int i=0;
    int sum;

    //Log.d("mmm","check");
    //int remains=30000;

    //取得日期
    Calendar mCal = Calendar.getInstance();
    String dateformat = "yyyyMMdd";
    SimpleDateFormat df = new SimpleDateFormat(dateformat);
    String today = df.format(mCal.getTime());



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
    public void onStart(Intent intent, int startId) {//官方準備遺棄它了
        Toast.makeText(this, "Service start", Toast.LENGTH_SHORT).show();
        //Log.d("fff","finish");
        //Log.d("mmm","check");
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
    CountDownTimer timer = new CountDownTimer(10000, 1000){//30分鐘：1800000
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
            dialog();
            update();
            renew();

            //Log.d("fff","finish");
        }
    };

    public void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("坐滿30分鐘囉～")
                .setTitle("提醒");
        // Add the buttons
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        alert.show();
        //Log.d("ddd","彈出對話匡");

    }

    public void delete(){//刪除
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("alert_test");
        myRef.child(today).removeValue();
    }
    public void update(){//新增
        final FirebaseDatabase database = FirebaseDatabase.getInstance();//取得資料庫連結
        DatabaseReference myRef= database.getReference("alert_test");
        myRef.child(today).setValue("30");
    }
    public void renew(){//更新
        FirebaseDatabase database = FirebaseDatabase.getInstance();//取得資料庫連結
        DatabaseReference myRef = null;
        myRef= database.getReference("alert_test");
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(today,String.valueOf(sum));//前面的字是child後面的字是要修改的value值
        myRef.updateChildren(childUpdates);
    }







}
