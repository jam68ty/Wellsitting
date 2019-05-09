package com.example.wellsitting;

import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Alarm extends AppCompatActivity {

    ImageButton btn_start;//未完成
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
                if (settime!=null){
                    i=Integer.parseInt(settime.getText().toString().trim());
                    //將直傳入Service
                    SharedPreferences.Editor editor = getSharedPreferences("SETTIME", MODE_PRIVATE).edit();
                    editor.putInt("TIME",i*1000);
                    editor.apply();
                    //倒數計時
                    timehold = new CountDownTimer(i*1000, 1000){
                        @Override
                        public void onTick(long millisUntilFinished) {
                            settime.setText(String.valueOf(millisUntilFinished/1000));
                            Log.d("sss",String.valueOf(millisUntilFinished/1000));
                        }
                        @Override
                        public void onFinish() {

                        }
                    };
                    timehold.start();
                    //settime.setText(i);
                }else{

                }
            }
        });


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timehold.cancel();
                //button變色未實現
                finish();
            }
        });

    }


}