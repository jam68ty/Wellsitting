package com.example.wellsitting;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainFragment extends Fragment {
    private View view;
    long escapeTime = 0;

    private ImageView mSignIn;
    private ImageView redDot;
    private TextView signSuccess;
    private AnimationSet set;
    private String isSign;
    private TextView textView;
    private Context mContext;
    private Toast mToast;
    FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mSignIn = view.findViewById(R.id.iv_sign);//签到
        redDot = view.findViewById(R.id.iv_redpoint);//显示未签到的红圆点
        textView = view.findViewById(R.id.tv_score);//积分

        FirebaseUser user = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("account").child(user.getUid()).child("coin");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //抓下來的型態是常整數型態，如此可以避免字串過長
                Long value = dataSnapshot.getValue(Long.class);
                //如果抓下來的值為空值，表示沒有存在這個欄位，亦即為新帳號
                if (value == null) {
                    //開一個新的欄位，並帶入0(Integer)
                    myRef.setValue(0);
                }
                //因為textView裡面僅能放String，因此要進行轉換
                String value_Temp = String.valueOf(value);
                textView.setText("coin: " + value_Temp);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        mSignIn.setOnClickListener(new View.OnClickListener() {
            private int i = 0;
            private int count = 0;

            @Override
            public void onClick(View v) {
                Log.d("xxx", "test");
                count++;
                if (count == 1) {
                    //在這裡進行資料的再次讀入(讀取TextView裡面的值，因為"coin: "共6個字元，故由index=6始於積分)
                    String Temp = textView.getText().toString().substring(6);
                    //將字串轉為數字
                    i = Integer.parseInt(Temp);
                    //每次簽到獲得25積分
                    i = i + 25;
                    mSignIn.setImageResource(R.drawable.btn_sign_done);//已签到
                    redDot.setVisibility(View.GONE);//圆点隐藏
                    // start平移和渐变动画
                    signSuccess.startAnimation(set);
                    signSuccess.setVisibility(View.GONE);
                    textView.setText("coin:" + i);
                    //   mSignIn.setClickable(false);

                    // Write a message to the database
                    myRef.setValue(i);
                } else {
                    Toast.makeText(getActivity(), "當前已簽到", Toast.LENGTH_LONG).show();
                }
            }
        });

        //签到添加积分动画文本
        signSuccess = view.findViewById(R.id.iv_sign_success);
        // 　获取签到成功图片的位置
        int left = signSuccess.getLeft();
        int top = signSuccess.getTop();

        // 创建平移和渐变的动画集合
        // 定义一个平移动画对象
        TranslateAnimation translate = new TranslateAnimation(left, left, top, top - 100);
        translate.setDuration(2000);
        //translate.setRepeatCount(1);

        // 渐变动画
        AlphaAnimation alpha = new AlphaAnimation(1, 0);
        alpha.setDuration(2000);
        alpha.setFillAfter(true);

        // 创建动画集合，将平移动画和渐变动画添加到集合中，一起start
        set = new AnimationSet(false);
        set.addAnimation(translate);
        set.addAnimation(alpha);


        Button Start = view.findViewById(R.id.start);
//        Button Stop = view.findViewById(R.id.stop);
        Button Reset = view.findViewById(R.id.reset);
        final Chronometer timer = view.findViewById(R.id.timer);
        final Chronometer count = view.findViewById(R.id.count);

        Start.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                // SystemClock.elapsedRealtime()方法會回傳從開機到現在的時間
                // 把這個時間做為一個Base,從這個Base開始計時
                timer.setBase(SystemClock.elapsedRealtime() + escapeTime);
                timer.start();

                count.setBase(SystemClock.elapsedRealtime() + 1800000);
                count.setCountDown(true);
                count.start();

            }
        });

//        Stop.setOnClickListener(new Button.OnClickListener(){
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                timer.stop();
//            }});

        Reset.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                timer.setBase(SystemClock.elapsedRealtime());
                timer.stop();
                escapeTime = 0;

                count.setBase(SystemClock.elapsedRealtime());
                count.stop();

            }
        });
        return view;
    }
}

//  https://blog.csdn.net/fengyeNom1/article/details/79614844 簽到獲取積分





