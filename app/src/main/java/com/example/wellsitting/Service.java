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



    Long total;
    MediaPlayer mediaPlayer;
    int sum;//首頁倒計參數
    int today_sum;//總正計參數
    CountDownTimer timer;
    CountDownTimer timer_homepage;

    //<取得日期--Start>:以方便作為每日總時間的key值計入資料庫
    Calendar mCal = Calendar.getInstance();
    String dateformat = "yyyyMMdd";
    SimpleDateFormat df = new SimpleDateFormat(dateformat);
    String today = df.format(mCal.getTime());
    String yesterday;
    //<取得日期--End>

    //CHECK DATE



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

    //整個Service被啟動會執行的動作存在該方法中
    @Override
    public void onStart(Intent intent, int startId) {//官方準備遺棄它了
        Toast.makeText(this, "Service start", Toast.LENGTH_SHORT).show();

        //<自定義時間的計時功能--Start>
        //接受Alarm傳來的值
        SharedPreferences settime = getSharedPreferences("SETTIME", MODE_PRIVATE);
        Integer set = settime.getInt("TIME", 0);
        total=set.longValue();
        //倒數計時設定
        timer = new CountDownTimer(total, 1000){//30分鐘：1800000
            @Override
            public void onTick(long millisUntilFinished) {
                SharedPreferences.Editor editor = getSharedPreferences("TIMER", MODE_PRIVATE).edit();
                editor.putLong("REMAINS",millisUntilFinished);
                editor.apply();
                /*SharedPreferences.Editor time_sum = getSharedPreferences("TIMERSUM", MODE_PRIVATE).edit();
                time_sum.putInt("SUM",sum);
                time_sum.apply();*/
            }
            @Override
            public void onFinish() {
                mediaPlayer.start();
                dialog_all();
                //update();
                //renew();
            }
        };
        //<自定義時間的計時功能--End>

        //計入今天時間
        SharedPreferences.Editor editor = getSharedPreferences("DATE", MODE_PRIVATE).edit();
        editor.putString("TODAY",today);
        editor.apply();



        //<系統時間的計時功能--Start>
        timer_homepage = new CountDownTimer(10000, 1000){//30分鐘：1800000
            @Override
            public void onTick(long millisUntilFinished) {
                //<首頁倒計時計算--Start>
                SharedPreferences.Editor editor = getSharedPreferences("TIMER", MODE_PRIVATE).edit();
                editor.putInt("COUNTDOWN",sum);
                editor.apply();


                SharedPreferences prefs = getSharedPreferences("TIMER", MODE_PRIVATE);
                Integer pre_sum = prefs.getInt("COUNTDOWN", 0);
                if(pre_sum!=null){
                    sum=pre_sum+1;

                }else{
                    sum=0;
                }
                //<首頁倒計時計算--End>



                //<總計時計算--Start>
                SharedPreferences time = getSharedPreferences("TIMERSUM", MODE_PRIVATE);
                Integer test = time.getInt("SUM", 0);
                if(test!=null){
                    today_sum=test+1;
                }else{
                    today_sum=0;
                }
                SharedPreferences.Editor time_sum = getSharedPreferences("TIMERSUM", MODE_PRIVATE).edit();
                time_sum.putInt("SUM",today_sum);
                time_sum.apply();
                //<總計時計算--End>
            }
            @Override
            public void onFinish() {
                sum=0;
                mediaPlayer.start();
                dialog();

            }
        };
        timer_homepage.start();
        //<系統時間的計時功能--End>

//待刪
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
        //update();
        renew();
        Toast.makeText(this, "Service stop", Toast.LENGTH_SHORT).show();
        timer_homepage.cancel();
        timer.cancel();
        mediaPlayer.stop();
    }

    //<系統計時的通知功能--Start>
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
    }
    //<系統計時的通知功能--End>

    //<自定義計時的通知功能--Start>
    public void dialog_all(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("坐滿"+total+"分鐘囉～")
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
    }
    //<自定義計時的通知功能--End>


    //<Database基本功能設定--Start>
    public void delete(){//刪除
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("alert");
        myRef.child(today).removeValue();
    }
    public void update(){//新增
        final FirebaseDatabase database = FirebaseDatabase.getInstance();//取得資料庫連結
        DatabaseReference myRef= database.getReference("alert");
        myRef.child(today).setValue("30");
    }
    public void renew(){//更新
        FirebaseDatabase database = FirebaseDatabase.getInstance();//取得資料庫連結
        DatabaseReference myRef = null;
        myRef= database.getReference("alert");
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(today,String.valueOf(today_sum));//前面的字是child後面的字是要修改的value值
        myRef.updateChildren(childUpdates);
    }
    //<Database基本功能設定--End>


}
