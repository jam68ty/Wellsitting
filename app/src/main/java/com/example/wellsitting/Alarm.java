package com.example.wellsitting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Alarm extends AppCompatActivity {

    ImageButton btn_start;
    ImageButton btn_cancel;
    private TextView timeholder;
    EditText settime;
    int i;
    CountDownTimer timehold;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        //timehold.start();

        findViewById(R.id.btn_cancel).setBackgroundResource(R.drawable.btn_cancel_d);
        btn_cancel=findViewById(R.id.btn_cancel);
        btn_start=findViewById(R.id.btn_start);
        timeholder=findViewById(R.id.timerholder);
        settime=(EditText) findViewById(R.id.set_time);
        //settime.getText().toString();

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent intent = new Intent(Alarm.this,AlarmService.class);
                //startService(intent);
                if (settime!=null){
                    i=Integer.parseInt(settime.getText().toString().trim());
                    //將值傳入Service
                    SharedPreferences.Editor editor = getSharedPreferences("SETTIME", MODE_PRIVATE).edit();
                    editor.putInt("TIME",i*1000);
                    editor.apply();
                    //<自定義的計時功能--Start>
                    timehold = new CountDownTimer(i*1000, 1000){
                        @Override
                        public void onTick(long millisUntilFinished) {
                            settime.setText(String.valueOf(millisUntilFinished/1000));
                        }
                        @Override
                        public void onFinish() {
                            dialog_all();
                        }
                    };
                    timehold.start();
                    //<自定義的計時功能--End>
                }else{

                }
            }
        });


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //timehold.cancel();
                finish();
            }
        });

    }

    //<自定義計時的通知功能--Start>
    public void dialog_all(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("坐滿"+i+"分鐘囉～")
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




}