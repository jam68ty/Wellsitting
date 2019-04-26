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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        Button Start = view.findViewById(R.id.start);
        Button Stop = view.findViewById(R.id.stop);
        Button Reset = view.findViewById(R.id.reset);
        final Chronometer timer = view.findViewById(R.id.timer);

        Start.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                timer.start();
            }});

        Stop.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                timer.stop();
            }});

        Reset.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                timer.setBase(SystemClock.elapsedRealtime());
            }});

        return view;


    }
}



