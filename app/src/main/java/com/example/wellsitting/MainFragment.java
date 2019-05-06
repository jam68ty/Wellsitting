package com.example.wellsitting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.support.constraint.Constraints.TAG;


public class MainFragment extends Fragment {
    private View view;
    long escapeTime = 0;
    FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        Button alert=view.findViewById(R.id.btn_alert);
        TextView user_info=view.findViewById(R.id.user);
        TextView childValue=view.findViewById(R.id.childValue);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        user_info.append(user.getUid());


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("account").child(user.getUid()).child("status");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = String.valueOf(dataSnapshot.getValue());
                Log.d(TAG, "Value is: " + value);
                childValue.setText(value);
                if(value.equals("n")||value.equals("N")){
                    AlertDialog.Builder builder1 = new  AlertDialog.Builder(getContext());
                    builder1.setTitle("姿勢錯誤");
                    builder1.setMessage("請調整您的坐姿，計時將重新開始");
                    builder1.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder1.create();
                    alertDialog.show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });



        Button Start = view.findViewById(R.id.start);
//        Button Stop = view.findViewById(R.id.stop);
        Button Reset = view.findViewById(R.id.reset);
        final Chronometer timer = view.findViewById(R.id.timer);
        final Chronometer count = view.findViewById(R.id.count);



        Start.setOnClickListener(new Button.OnClickListener(){

            @RequiresApi(api = Build.VERSION_CODES.N)
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



