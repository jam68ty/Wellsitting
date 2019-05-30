package com.example.wellsitting;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
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


public class Storyline_cindy extends Fragment {


    private View view;

    ImageView gift;

    private Button cindych1;
    private Button cindych2;
    private Button cindych3;
    private Button cindych4;
    private Button cindych5;
    private Button cindych6;
    private Button cindych7;

    private TextView cindy_score;
    private TextView textView;
    private Context mContext;

    private TextView cPrice_2;
    private TextView cPrice_3;
    private TextView cPrice_4;
    private TextView cPrice_5;
    private TextView cPrice_6;
    private TextView cPrice_7;

    Context mcontext;
    FirebaseAuth mAuth;
    Context context;
    private ImageButton cbuy1;
    private ImageButton cbuy2;
    private ImageButton cbuy3;
    private ImageButton cbuy4;
    private ImageButton cbuy5;
    private ImageButton cbuy6;
    private ImageButton cbuy7;

    boolean check_cch2;
    boolean check_cch3;
    boolean check_cch4;
    boolean check_cch5;
    boolean check_cch6;
    boolean check_cch7;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_storyline_cindy, container, false);

        gift=view.findViewById(R.id.gift_b);
        gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopWindow(v);
            }
        });

        cindych2=view.findViewById(R.id.cindych2);
        mcontext=inflater.getContext();
        mAuth = FirebaseAuth.getInstance();
        cindy_score=view.findViewById(R.id.cindy_score);

        cPrice_2=view.findViewById(R.id.cPrice_2);
        cPrice_3=view.findViewById(R.id.cPrice_3);
        cPrice_4=view.findViewById(R.id.cPrice_4);
        cPrice_5=view.findViewById(R.id.cPrice_5);
        cPrice_6=view.findViewById(R.id.cPrice_6);
        cPrice_7=view.findViewById(R.id.cPrice_7);

//--------------------------------------------------------------------------------------------

        FirebaseUser user = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("account").child(user.getUid()).child("coin");
        DatabaseReference myRef_rch2 = database.getReference("account").child(user.getUid()).child("story_cindy");
        DatabaseReference myRef_rch3 = database.getReference("account").child(user.getUid()).child("story_cindy");
        DatabaseReference myRef_rch4 = database.getReference("account").child(user.getUid()).child("story_cindy");
        DatabaseReference myRef_rch5 = database.getReference("account").child(user.getUid()).child("story_cindy");
        DatabaseReference myRef_rch6 = database.getReference("account").child(user.getUid()).child("story_cindy");
        DatabaseReference myRef_rch7 = database.getReference("account").child(user.getUid()).child("story_cindy");
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
                String value_Temp = String.valueOf(value);
                cindy_score.setText(value_Temp);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });




        myRef_rch2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //------------------------------------------------------------------------
                //以下為coin
                //抓下來的型態是常整數型態，如此可以避免字串過長
                String buyornot = dataSnapshot.child("2").getValue(String.class);


                //如果抓下來的值為空值，表示沒有存在這個欄位，亦即為新帳號

                // String s=rPrice_2.getText().toString().substring(0);

                if(buyornot!=null && buyornot.equals("true")) {
                    cbuy2.setImageResource(R.drawable.story_cgot);
                    cPrice_2.setText(null);
                    cbuy2.setEnabled(false);
                }else{
                    cbuy2.setImageResource(R.drawable.lock);
                    cPrice_2.setText("150");
                }



            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        myRef_rch3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //------------------------------------------------------------------------
                //以下為coin
                //抓下來的型態是常整數型態，如此可以避免字串過長
                String buyornot = dataSnapshot.child("3").getValue(String.class);


                //如果抓下來的值為空值，表示沒有存在這個欄位，亦即為新帳號
                // String s3=rPrice_3.getText().toString().substring(0);



                if(buyornot!=null && buyornot.equals("true")) {
                    cbuy3.setImageResource(R.drawable.story_cgot);
                    cPrice_3.setText(null);
                    cbuy3.setEnabled(false);
                }else{
                    cbuy3.setImageResource(R.drawable.lock);
                    cPrice_3.setText("150");

                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        myRef_rch4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //------------------------------------------------------------------------
                //以下為coin
                //抓下來的型態是常整數型態，如此可以避免字串過長
                String buyornot = dataSnapshot.child("4").getValue(String.class);


                //如果抓下來的值為空值，表示沒有存在這個欄位，亦即為新帳號
                //String s4=rPrice_4.getText().toString().substring(0);

                if(buyornot!=null && buyornot.equals("true")) {
                    cbuy4.setImageResource(R.drawable.story_cgot);
                    cPrice_4.setText(null);
                    cbuy4.setEnabled(false);
                }else{
                    cbuy4.setImageResource(R.drawable.lock);
                    cPrice_4.setText("200");

                }



            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        myRef_rch5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //------------------------------------------------------------------------
                //以下為coin
                //抓下來的型態是常整數型態，如此可以避免字串過長
                String buyornot = dataSnapshot.child("5").getValue(String.class);


                //如果抓下來的值為空值，表示沒有存在這個欄位，亦即為新帳號

                // String s5=rPrice_5.getText().toString().substring(0);

                if(buyornot!=null && buyornot.equals("true")) {
                    cbuy5.setImageResource(R.drawable.story_cgot);
                    cPrice_5.setText(null);
                    cbuy5.setEnabled(false);
                }else{
                    cbuy5.setImageResource(R.drawable.lock);
                    cPrice_5.setText("200");

                }



            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        myRef_rch6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //------------------------------------------------------------------------
                //以下為coin
                //抓下來的型態是常整數型態，如此可以避免字串過長
                String buyornot = dataSnapshot.child("6").getValue(String.class);


                //如果抓下來的值為空值，表示沒有存在這個欄位，亦即為新帳號

                // String s5=rPrice_5.getText().toString().substring(0);

                if(buyornot!=null && buyornot.equals("true")) {
                    cbuy6.setImageResource(R.drawable.story_cgot);
                    cPrice_6.setText(null);
                    cbuy6.setEnabled(false);
                }else{
                    cbuy6.setImageResource(R.drawable.lock);
                    cPrice_6.setText("250");

                }



            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        myRef_rch7.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //------------------------------------------------------------------------
                //以下為coin
                //抓下來的型態是常整數型態，如此可以避免字串過長
                String buyornot = dataSnapshot.child("7").getValue(String.class);


                //如果抓下來的值為空值，表示沒有存在這個欄位，亦即為新帳號

                // String s5=rPrice_5.getText().toString().substring(0);

                if(buyornot!=null && buyornot.equals("true")) {
                    cbuy7.setImageResource(R.drawable.story_cgot);
                    cPrice_7.setText(null);
                    cbuy7.setEnabled(false);
                }else{
                    cbuy7.setImageResource(R.drawable.lock);
                    cPrice_7.setText("250");

                }



            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
//-----------------------------------------------------------------------------------------------

        // Inflate the layout for this fragment


        cbuy1=view.findViewById(R.id.cbuy_1);
        cbuy2=view.findViewById(R.id.cbuy_2);
        cbuy3=view.findViewById(R.id.cbuy_3);
        cbuy4=view.findViewById(R.id.cbuy_4);
        cbuy5=view.findViewById(R.id.cbuy_5);
        cbuy6=view.findViewById(R.id.cbuy_6);
        cbuy7=view.findViewById(R.id.cbuy_7);

        cindych1=view.findViewById(R.id.cindych1);
        cindych2=view.findViewById(R.id.cindych2);
        cindych3=view.findViewById(R.id.cindych3);//未做
        cindych4=view.findViewById(R.id.cindych4);//未做
        cindych5=view.findViewById(R.id.cindych5);//未做
        cindych6=view.findViewById(R.id.cindych6);//未做
        cindych7=view.findViewById(R.id.cindych7);//未做

        cindych1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor line = getActivity().getSharedPreferences("LINE", MODE_PRIVATE).edit();
                line.putString("LINE", "CINDY");
                line.apply();

                SharedPreferences.Editor ch1 = getActivity().getSharedPreferences("CINDY", MODE_PRIVATE).edit();
                ch1.putInt("CH",1);
                ch1.apply();

                Intent intent=new Intent(getActivity(), StorylineActivity.class);
                startActivity(intent);
            }
        });

        cindych2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=cPrice_2.getText().toString().substring(0);
                if(s.equals("")){
                    SharedPreferences.Editor line = getActivity().getSharedPreferences("LINE", MODE_PRIVATE).edit();
                    line.putString("LINE", "CINDY");
                    line.apply();

                    SharedPreferences.Editor ch2 = getActivity().getSharedPreferences("CINDY", MODE_PRIVATE).edit();
                    ch2.putInt("CH",2);
                    ch2.apply();

                    Intent intent=new Intent(getActivity(), StorylineActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(mcontext, "未購買", Toast.LENGTH_LONG).show();
                }

            }
        });

        cindych3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=cPrice_3.getText().toString().substring(0);
                if(s.equals("")){
                    SharedPreferences.Editor line = getActivity().getSharedPreferences("LINE", MODE_PRIVATE).edit();
                    line.putString("LINE", "CINDY");
                    line.apply();

                    SharedPreferences.Editor ch3 = getActivity().getSharedPreferences("CINDY", MODE_PRIVATE).edit();
                    ch3.putInt("CH",3);
                    ch3.apply();

                    Intent intent=new Intent(getActivity(), StorylineActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(mcontext, "未購買", Toast.LENGTH_LONG).show();
                }

            }
        });

        cindych4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=cPrice_4.getText().toString().substring(0);
                if(s.equals("")){
                    SharedPreferences.Editor line = getActivity().getSharedPreferences("LINE", MODE_PRIVATE).edit();
                    line.putString("LINE", "CINDY");
                    line.apply();

                    SharedPreferences.Editor ch4 = getActivity().getSharedPreferences("CINDY", MODE_PRIVATE).edit();
                    ch4.putInt("CH",4);
                    ch4.apply();

                    Intent intent=new Intent(getActivity(), StorylineActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(mcontext, "未購買", Toast.LENGTH_LONG).show();
                }

            }
        });

        cindych5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=cPrice_5.getText().toString().substring(0);
                if(s.equals("")){
                    SharedPreferences.Editor line = getActivity().getSharedPreferences("LINE", MODE_PRIVATE).edit();
                    line.putString("LINE", "CINDY");
                    line.apply();

                    SharedPreferences.Editor ch5 = getActivity().getSharedPreferences("CINDY", MODE_PRIVATE).edit();
                    ch5.putInt("CH",5);
                    ch5.apply();

                    Intent intent=new Intent(getActivity(), StorylineActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(mcontext, "未購買", Toast.LENGTH_LONG).show();
                }

            }
        });

        cindych6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=cPrice_6.getText().toString().substring(0);
                if(s.equals("")){
                    SharedPreferences.Editor line = getActivity().getSharedPreferences("LINE", MODE_PRIVATE).edit();
                    line.putString("LINE", "CINDY");
                    line.apply();

                    SharedPreferences.Editor ch6 = getActivity().getSharedPreferences("CINDY", MODE_PRIVATE).edit();
                    ch6.putInt("CH",6);
                    ch6.apply();

                    Intent intent=new Intent(getActivity(), StorylineActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(mcontext, "未購買", Toast.LENGTH_LONG).show();
                }

            }
        });

        cindych7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=cPrice_7.getText().toString().substring(0);
                if(s.equals("")){
                    SharedPreferences.Editor line = getActivity().getSharedPreferences("LINE", MODE_PRIVATE).edit();
                    line.putString("LINE", "CINDY");
                    line.apply();

                    SharedPreferences.Editor ch7 = getActivity().getSharedPreferences("CINDY", MODE_PRIVATE).edit();
                    ch7.putInt("CH",7);
                    ch7.apply();

                    Intent intent=new Intent(getActivity(), StorylineActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(mcontext, "未購買", Toast.LENGTH_LONG).show();
                }

            }
        });

        cbuy1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();

            }});

        cbuy2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String i=String.valueOf(cindy_score.getText());
                String s=cPrice_2.getText().toString().substring(0);
                int t =Integer.parseInt(i.trim());
                String value_Temp = String.valueOf(t);



                if(check_cch2==true){
                    Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();
                    cindych2.setEnabled(true);

                } else{
                    if (t<150){
                        Toast.makeText(mcontext, "金幣不足", Toast.LENGTH_LONG).show();
                        cindy_score.setText(value_Temp);

                    }else{
                        cindych2.setEnabled(true);
                        myRef.child("coin").setValue(t-150);
                        String value_Temp1 = String.valueOf(t-150);
                        cindy_score.setText(value_Temp1);
                        cbuy2.setImageResource(R.drawable.story_rgot);
                        cPrice_2.setText(null);
                        Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();

                        myRef_rch2.child("2").setValue("true");
                        check_cch2=true;
                    }
                }
            }
        });

        cbuy3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String i=String.valueOf(cindy_score.getText());
                String s=cPrice_3.getText().toString().substring(0);
                int t =Integer.parseInt(i.trim());
                String value_Temp = String.valueOf(t);


                if(check_cch3==true){
                    Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();
                    cindych3.setEnabled(true);

                } else{
                    if (t<150){
                        Toast.makeText(mcontext, "金幣不足", Toast.LENGTH_LONG).show();
                        cindy_score.setText(value_Temp);

                    }else{
                        cindych3.setEnabled(true);
                        myRef.child("coin").setValue(t-150);
                        String value_Temp1 = String.valueOf(t-150);
                        cindy_score.setText(value_Temp1);
                        cbuy3.setImageResource(R.drawable.story_cgot);
                        cPrice_3.setText(null);
                        Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();

                        myRef_rch3.child("3").setValue("true");
                        check_cch3=true;
                    }
                }
            }
        });

        cbuy4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String i=String.valueOf(cindy_score.getText());
                String s=cPrice_4.getText().toString().substring(0);
                int t =Integer.parseInt(i.trim());
                String value_Temp = String.valueOf(t);


                if(check_cch4==true){
                    Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();
                    cindych4.setEnabled(true);

                } else{
                    if (t<200){
                        Toast.makeText(mcontext, "金幣不足", Toast.LENGTH_LONG).show();
                        cindy_score.setText(value_Temp);

                    }else{
                        cindych4.setEnabled(true);
                        myRef.child("coin").setValue(t-200);
                        String value_Temp1 = String.valueOf(t-200);
                        cindy_score.setText(value_Temp1);
                        cbuy4.setImageResource(R.drawable.story_cgot);
                        cPrice_4.setText(null);
                        Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();

                        myRef_rch4.child("4").setValue("true");
                        check_cch4=true;
                    }
                }
            }
        });

        cbuy5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String i=String.valueOf(cindy_score.getText());
                String s=cPrice_5.getText().toString().substring(0);
                int t =Integer.parseInt(i.trim());
                String value_Temp = String.valueOf(t);

                if(check_cch5==true){
                    Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();
                    cindych5.setEnabled(true);

                } else{
                    if (t<200){
                        Toast.makeText(mcontext, "金幣不足", Toast.LENGTH_LONG).show();
                        cindy_score.setText(value_Temp);

                    }else{
                        cindych5.setEnabled(true);
                        myRef.child("coin").setValue(t-200);
                        String value_Temp1 = String.valueOf(t-200);
                        cindy_score.setText(value_Temp1);
                        cbuy5.setImageResource(R.drawable.story_cgot);
                        cPrice_5.setText(null);
                        Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();

                        myRef_rch5.child("5").setValue("true");
                        check_cch5=true;
                    }
                }
            }
        });

        cbuy6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String i=String.valueOf(cindy_score.getText());
                String s=cPrice_6.getText().toString().substring(0);
                int t =Integer.parseInt(i.trim());
                String value_Temp = String.valueOf(t);

                if(check_cch6==true){
                    Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();
                    cindych6.setEnabled(true);

                } else{
                    if (t<250){
                        Toast.makeText(mcontext, "金幣不足", Toast.LENGTH_LONG).show();
                        cindy_score.setText(value_Temp);

                    }else{
                        cindych6.setEnabled(true);
                        myRef.child("coin").setValue(t-250);
                        String value_Temp1 = String.valueOf(t-250);
                        cindy_score.setText(value_Temp1);
                        cbuy6.setImageResource(R.drawable.story_cgot);
                        cPrice_6.setText(null);
                        Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();

                        myRef_rch6.child("6").setValue("true");
                        check_cch6=true;
                    }
                }
            }
        });

        cbuy7.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String i=String.valueOf(cindy_score.getText());
                String s=cPrice_7.getText().toString().substring(0);
                int t =Integer.parseInt(i.trim());
                String value_Temp = String.valueOf(t);

                if(check_cch7==true){
                    Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();
                    cindych7.setEnabled(true);

                } else{
                    if (t<250){
                        Toast.makeText(mcontext, "金幣不足", Toast.LENGTH_LONG).show();
                        cindy_score.setText(value_Temp);

                    }else{
                        cindych7.setEnabled(true);
                        myRef.child("coin").setValue(t-250);
                        String value_Temp1 = String.valueOf(t-250);
                        cindy_score.setText(value_Temp1);
                        cbuy7.setImageResource(R.drawable.story_cgot);
                        cPrice_7.setText(null);
                        Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();

                        myRef_rch7.child("7").setValue("true");
                        check_cch7=true;
                    }
                }
            }
        });

        return view;
    }

    private void initPopWindow(View v) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_popup, null, false);
        Button btn_xixi = (Button) view.findViewById(R.id.btn_shopee);
        Button btn_hehe = (Button) view.findViewById(R.id.btn_books);
        EditText web=view.findViewById(R.id.shopUrl);
        String s=String.valueOf(web.getText());
        web.setText(s);

        //1.构造一个PopupWindow，参数依次是加载的View，宽高
        final PopupWindow popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        //popWindow.setAnimationStyle(R.anim.anim_pop);  //设置加载动画

        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
        //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效


        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popWindow.showAsDropDown(v, 10, -1200);

        //设置popupWindow里的按钮的事件
        btn_xixi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("https://shopee.tw/");
                Intent i=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);
                Toast.makeText(getContext(), "即將前往蝦皮頁面", Toast.LENGTH_SHORT).show();
            }
        });
        btn_hehe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("https://www.books.com.tw/?loc=tw_logo_001");
                Intent i=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);
                Toast.makeText(getContext(), "即將前往博客來頁面", Toast.LENGTH_SHORT).show();
                popWindow.dismiss();
            }
        });
    }
/*    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Storyline_red() {
        // Required empty public constructor
    }

    *//**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Storyline_red.
     *//*
    // TODO: Rename and change types and number of parameters
    public static Storyline_red newInstance(String param1, String param2) {
        Storyline_red fragment = new Storyline_red();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_storyline_red, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    *//**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     *//*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
