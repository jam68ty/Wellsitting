package com.example.wellsitting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Service extends android.app.Service {



    Long total;
    MediaPlayer mediaPlayer;
    int sum=0;//首頁倒計參數
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

    FirebaseAuth mAuth;

    Service toMyself = this;
    int count;
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
        /*mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("test").child("signal");*/


    }

    //整個Service被啟動會執行的動作存在該方法中
    @Override
    public void onStart(Intent intent, int startId) {//官方準備遺棄它了
        Toast.makeText(this, "Service start", Toast.LENGTH_SHORT).show();

        //首頁到數計時
        SharedPreferences.Editor c = getSharedPreferences("TIMER", MODE_PRIVATE).edit();
        c.putInt("COUNTDOWN",0);
        c.apply();


        //<系統時間的計時功能--Start>
        timer_homepage = new CountDownTimer(30000, 1000){//30分鐘：1800000
            @Override
            public void onTick(long millisUntilFinished) {
                //<首頁倒計時計算--Start>

                SharedPreferences prefs = getSharedPreferences("TIMER", MODE_PRIVATE);
                Integer pre_sum = prefs.getInt("COUNTDOWN", 0);
                if(pre_sum!=null){
                    sum=pre_sum+1;
                    SharedPreferences.Editor editor = getSharedPreferences("TIMER", MODE_PRIVATE).edit();
                    editor.putInt("COUNTDOWN",sum);
                    editor.apply();


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


                mediaPlayer.start();
                dialog();

            }
        };
        timer_homepage.start();
        //<系統時間的計時功能--End>

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


        //讀取資料庫資料<--Start>
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference baseRef = database.getReference("test");
        //DatabaseReference myRef = baseRef.child("js");
        DatabaseReference myRef = baseRef;

        if(myRef!=null){
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.getChildrenCount() < 1)
                        return ;

                    String value=snapshot.getValue().toString();
                    Log.d("value = ",value);
                    Map<String, Object> objectMap = (HashMap<String,Object>) snapshot.getValue();
                    Log.d("size",String.valueOf(objectMap.size()));
                    for (Object obj : objectMap.values()) {
                        count++;
                        Log.d("count = ",String.valueOf(count));
                    }
                    //錯誤提醒
                    //Log.d("count_service = ",String.valueOf(count));
                    if(objectMap.size()>5){
                        dialog_error();
                        delete();
                        toMyself.stopSelf();
                        //onDestroy();
                        //timer_homepage.cancel();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        //讀取資料庫資料<--End>
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

    //<錯誤提醒--Start>
    public void dialog_error(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("坐姿錯誤！")
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
    //<錯誤提醒--End>


    //<Database基本功能設定--Start>
    public void delete(){//刪除
        Log.d("Jing", "xxxxxx");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child("test").removeValue();
        //myRef.child("js").removeValue();
        //myRef.child("js/KeepAlive").setValue("HelloWorld");

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
