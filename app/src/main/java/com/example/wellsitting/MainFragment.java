package com.example.wellsitting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.LayoutRes;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
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

import static android.content.Context.MODE_PRIVATE;
import static android.support.constraint.Constraints.TAG;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainFragment extends Fragment {
    private View view;
    long escapeTime = 0;
    boolean IsCheckIn; //用於確認是否有簽到過
    boolean IsCheckInForTime = true; //用於確認過去是否有簽到時間

    private ImageView mSignIn;
    private ImageView redDot;
    private TextView signSuccess;
    private AnimationSet set;
    private String isSign;
    private TextView textView;
    private Context mContext;
    private Toast mToast;
    FirebaseAuth mAuth;
    Context mcontext;
    private TextView timeremains;
    int time_sum=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);


        timeremain.start();
        mcontext=inflater.getContext();

        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mSignIn = view.findViewById(R.id.iv_sign);//签到
        redDot = view.findViewById(R.id.iv_redpoint);//显示未签到的红圆点
        textView = view.findViewById(R.id.tv_score);//积分
        timeremains=view.findViewById(R.id.timeremains);
        MediaPlayer mediaPlayer=MediaPlayer.create(getContext(),R.raw.select08);

//--------------------------------------------------------------------------------------------

        FirebaseUser user = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("account").child(user.getUid()).child("coin");
        DatabaseReference myRef_status = database.getReference("account").child(user.getUid()).child("status");

//-----------------------------------------------------------------------------------------------

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //------------------------------------------------------------------------
                //以下為coin
                //抓下來的型態是常整數型態，如此可以避免字串過長
                Long value = dataSnapshot.child("coin").getValue(Long.class);
                //如果抓下來的值為空值，表示沒有存在這個欄位，亦即為新帳號
                if (value == null) {
                    //開一個新的欄位，並帶入0(Integer)
                    myRef.child("coin").setValue(0);
                }
                //因為textView裡面僅能放String，因此要進行轉換
                String value_Temp = String.valueOf(value);
                textView.setText("coin: " + value_Temp);
                //------------------------------------------------------------------------
                //以下為date
                //取得當前時間，並記錄進資料庫

                //先行定義時間格式
                SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
                //取得現在時間
                Date today = new Date();
                //透過SimpleDateFormat的format方法將Date轉為字串
                String todayString = sdf.format(today);
                int todayYear = Integer.parseInt(todayString.substring(0, 4));
                int todayMonth = Integer.parseInt(todayString.substring(4, 6));
                int todayDate = Integer.parseInt(todayString.substring(6));

                //抓下來的型態是常整數型態，如此可以避免字串過長
                Long DataBaseYear = dataSnapshot.child("coin_year").getValue(Long.class);
                Long DataBaseMonth = dataSnapshot.child("coin_month").getValue(Long.class);
                Long DataBaseDate = dataSnapshot.child("coin_date").getValue(Long.class);

                if (DataBaseYear == null || DataBaseMonth == null || DataBaseDate == null) {
                    //因為新用戶從未簽到過，因此將其預設帶為使用當下的前一天
                    //如此一來新用戶當次可簽到，且可避免閃退
                    todayDate -= 1;
                    myRef.child("coin_year").setValue(todayYear);
                    myRef.child("coin_month").setValue(todayMonth);
                    myRef.child("coin_date").setValue(todayDate);
                } else {
                    //由Long轉換成String
                    String DateBaseYearString = String.valueOf(DataBaseYear);
                    String DateBaseMonthString = String.valueOf(DataBaseMonth);
                    String DateBaseDateString = String.valueOf(DataBaseDate);

                    //由String轉換成int
                    int DateBaseYearInt = Integer.parseInt(DateBaseYearString);
                    int DateBaseMonthInt = Integer.parseInt(DateBaseMonthString);
                    int DateBaseDateInt = Integer.parseInt(DateBaseDateString);
                    if (DateBaseYearInt < todayYear) { //簽到年份比今天小，表示還沒簽到過
                        IsCheckIn = false;
                    } else if (DateBaseYearInt == todayYear) { //簽到年份與資料庫年份相同
                        if (DateBaseMonthInt < todayMonth) { //簽到月份比今天小，表示還沒簽到過
                            IsCheckIn = false;
                        } else if (DateBaseMonthInt == todayMonth) { //簽到月份與今天為同月份，需要比較日期
                            if (DateBaseDateInt < todayDate) {
                                IsCheckIn = false;
                            } else {//已經簽到過了
                                IsCheckIn = true;
                                mSignIn.setImageResource(R.drawable.btn_sign_done);//已签到
                                redDot.setVisibility(View.GONE);//圆点隐藏
                            }
                        } else {//已經簽到過了
                            IsCheckIn = true;
                            mSignIn.setImageResource(R.drawable.btn_sign_done);//已签到
                            redDot.setVisibility(View.GONE);//圆点隐藏
                        }
                    } else {//已經簽到過了
                        IsCheckIn = true;
                        mSignIn.setImageResource(R.drawable.btn_sign_done);//已签到
                        redDot.setVisibility(View.GONE);//圆点隐藏
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        myRef_status.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = String.valueOf(dataSnapshot.getValue());
                Log.d(TAG, "Value is: " + value);
                if (value.equals("n")||value.equals("N")){
                    AlertDialog.Builder builder1 = new  AlertDialog.Builder(getContext());
                    builder1.setTitle("姿勢錯誤");
                    builder1.setMessage("請調整您的坐姿，計時將重新開始");
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                    builder1.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            mediaPlayer.stop();
                            myRef_status.setValue("y");
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

//-----------------------------------------------------------------------------------------------
        mSignIn.setOnClickListener(new View.OnClickListener() {
            private int i = 0;
            private int count = 0;

            @Override
            public void onClick(View v) {
                Log.d("xxx", "test");

                if (IsCheckIn == false) {
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
                    //先行定義時間格式
                    SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
                    //取得現在時間
                    Date today = new Date();
                    //透過SimpleDateFormat的format方法將Date轉為字串
                    String todayString = sdf.format(today);
                    int todayYear = Integer.parseInt(todayString.substring(0, 4));
                    int todayMonth = Integer.parseInt(todayString.substring(4, 6));
                    int todayDate = Integer.parseInt(todayString.substring(6));

                    myRef.child("coin").setValue(i);
                    myRef.child("coin_year").setValue(todayYear);
                    myRef.child("coin_month").setValue(todayMonth);
                    myRef.child("coin_date").setValue(todayDate);
                    IsCheckIn = true;
                } else {
                    mSignIn.setImageResource(R.drawable.btn_sign_done);//已签到
                    redDot.setVisibility(View.GONE);//圆点隐藏
                    Toast.makeText(mcontext, "當前已簽到", Toast.LENGTH_LONG).show();
                }
            }
        });
//-----------------------------------------------------------------------------------------------
        //签到添加积分动画文本
        signSuccess = view.findViewById(R.id.iv_sign_success);
        // 　获取签到成功图片的位置
        int left = signSuccess.getLeft();
        int top = signSuccess.getTop();
        //-----------------------------------------------------------------------------------------------
        // 创建平移和渐变的动画集合
        // 定义一个平移动画对象
        TranslateAnimation translate = new TranslateAnimation(left, left, top, top - 100);
        translate.setDuration(2000);
        //translate.setRepeatCount(1);
//-----------------------------------------------------------------------------------------------
        // 渐变动画
        AlphaAnimation alpha = new AlphaAnimation(1, 0);
        alpha.setDuration(2000);
        alpha.setFillAfter(true);
//-----------------------------------------------------------------------------------------------
        // 创建动画集合，将平移动画和渐变动画添加到集合中，一起start
        set = new AnimationSet(false);
        set.addAnimation(translate);
        set.addAnimation(alpha);


        Button Start = view.findViewById(R.id.start);
//        Button Stop = view.findViewById(R.id.stop);
        Button Reset = view.findViewById(R.id.reset);


        Start.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(mcontext,Service.class);
                mcontext.startService(intent);
                timeremain.start();

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
                Intent intent = new Intent(mcontext,Service.class);
                mcontext.stopService(intent);
                timeremain.cancel();

            }
        });
        return view;
    }

    CountDownTimer timeremain = new CountDownTimer(30000, 1000){
        @Override
        public void onTick(long millisUntilFinished) {
            SharedPreferences prefs = mcontext.getSharedPreferences("TIMERSUM", MODE_PRIVATE);
            Integer pre_sum = prefs.getInt("SUM", 0);

            if (pre_sum != null) {
                //time_sum++;
                timeremains.setText(String.valueOf(pre_sum));

                //String name = prefs.getString("REMAINS", "No name defined");//"No name defined" is the default value.
                //int idName = prefs.getInt("idName", 0); //0 is the default value.
            }

        }
        @Override
        public void onFinish() {

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //Log.d("ddd","change");
        timeremain.cancel();
    }
}

//  https://blog.csdn.net/fengyeNom1/article/details/79614844 簽到獲取積分





