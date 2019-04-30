package com.example.wellsitting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;




public class MainFragment extends Fragment {
    private View view;
    long escapeTime = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        Button Start = view.findViewById(R.id.start);
//        Button Stop = view.findViewById(R.id.stop);
        Button Reset = view.findViewById(R.id.reset);
        final Chronometer timer = view.findViewById(R.id.timer);
        final Chronometer count = view.findViewById(R.id.count);

        Start.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                // SystemClock.elapsedRealtime()方法會回傳從開機到現在的時間
                // 把這個時間做為一個Base,從這個Base開始計時
                timer.setBase(SystemClock.elapsedRealtime() + escapeTime);
                timer.start();

                count.setBase(SystemClock.elapsedRealtime() + 1800000 );
                count.setCountDown(true);
                count.start();

            }});

//        Stop.setOnClickListener(new Button.OnClickListener(){
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                timer.stop();
//            }});

        Reset.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                timer.setBase(SystemClock.elapsedRealtime());
                timer.stop();
                escapeTime = 0;

                count.setBase(SystemClock.elapsedRealtime());
                count.stop();

            }});

        return view;


    }
}



