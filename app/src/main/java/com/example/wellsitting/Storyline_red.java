package com.example.wellsitting;

import android.content.ClipboardManager;
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

import static android.content.Context.CLIPBOARD_SERVICE;
import static android.content.Context.MODE_PRIVATE;
import static android.support.v4.content.ContextCompat.getSystemService;


public class Storyline_red extends Fragment {


    private View view;

    ImageView gift;

    private Button redch1;
    private Button redch2;
    private Button redch3;
    private Button redch4;
    private Button redch5;

    private TextView t_score;
    private TextView textView;
    private Context mContext;

    private TextView rPrice_2;
    private TextView rPrice_3;
    private TextView rPrice_4;
    private TextView rPrice_5;

    Context mcontext;
    FirebaseAuth mAuth;
    Context context;
    private ImageButton rbuy1;
    private ImageButton rbuy2;
    private ImageButton rbuy3;
    private ImageButton rbuy4;
    private ImageButton rbuy5;

    boolean check_rch2;
    boolean check_rch3;
    boolean check_rch4;
    boolean check_rch5;

    ClipboardManager myClipboard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_storyline_red, container, false);

        gift=view.findViewById(R.id.gift_red);
        gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopWindow(v);
            }
        });

        myClipboard = (ClipboardManager) this.getActivity().getSystemService(CLIPBOARD_SERVICE);

        redch2=view.findViewById(R.id.redch2);
        mcontext=inflater.getContext();
        mAuth = FirebaseAuth.getInstance();
        t_score=view.findViewById(R.id.t_score);

        rPrice_2=view.findViewById(R.id.rPrice_2);
        rPrice_3=view.findViewById(R.id.rPrice_3);
        rPrice_4=view.findViewById(R.id.rPrice_4);
        rPrice_5=view.findViewById(R.id.rPrice_5);
//--------------------------------------------------------------------------------------------

        FirebaseUser user = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("account").child(user.getUid()).child("coin");
        DatabaseReference myRef_rch2 = database.getReference("account").child(user.getUid()).child("story_red");
        DatabaseReference myRef_rch3 = database.getReference("account").child(user.getUid()).child("story_red");
        DatabaseReference myRef_rch4 = database.getReference("account").child(user.getUid()).child("story_red");
        DatabaseReference myRef_rch5 = database.getReference("account").child(user.getUid()).child("story_red");

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
                t_score.setText(value_Temp);
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
                    rbuy2.setImageResource(R.drawable.story_rgot);
                    rPrice_2.setText(null);
                    rbuy2.setEnabled(false);
                }else{
                    rbuy2.setImageResource(R.drawable.lock);
                    rPrice_2.setText("150");
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
                    rbuy3.setImageResource(R.drawable.story_rgot);
                    rPrice_3.setText(null);
                    rbuy3.setEnabled(false);
                }else{
                    rbuy3.setImageResource(R.drawable.lock);
                    rPrice_3.setText("150");

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
                    rbuy4.setImageResource(R.drawable.story_rgot);
                    rPrice_4.setText(null);
                    rbuy4.setEnabled(false);
                }else{
                    rbuy4.setImageResource(R.drawable.lock);
                    rPrice_4.setText("200");

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
                    rbuy5.setImageResource(R.drawable.story_rgot);
                    rPrice_5.setText(null);
                    rbuy5.setEnabled(false);
                }else{
                    rbuy5.setImageResource(R.drawable.lock);
                    rPrice_5.setText("200");

                }



            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
//-----------------------------------------------------------------------------------------------

        // Inflate the layout for this fragment


        rbuy1=view.findViewById(R.id.rbuy_1);
        rbuy2=view.findViewById(R.id.rbuy_2);
        rbuy3=view.findViewById(R.id.rbuy_3);
        rbuy4=view.findViewById(R.id.rbuy_4);
        rbuy5=view.findViewById(R.id.rbuy_5);

        redch1=view.findViewById(R.id.redch1);
        redch3=view.findViewById(R.id.redch3);//未做
        redch4=view.findViewById(R.id.redch4);//未做
        redch5=view.findViewById(R.id.redch5);//未做
        redch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ch1 = getActivity().getSharedPreferences("RED", MODE_PRIVATE).edit();
                ch1.putInt("CH",1);
                ch1.apply();

                SharedPreferences.Editor line = getActivity().getSharedPreferences("LINE", MODE_PRIVATE).edit();
                line.putString("LINE", "RED");
                line.apply();

                Intent intent=new Intent(getActivity(), StorylineActivity.class);
                startActivity(intent);
            }
        });
        redch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=rPrice_2.getText().toString().substring(0);
                if(s.equals("")){
                    SharedPreferences.Editor ch2 = getActivity().getSharedPreferences("RED", MODE_PRIVATE).edit();
                    ch2.putInt("CH",2);
                    ch2.apply();

                    SharedPreferences.Editor line = getActivity().getSharedPreferences("LINE", MODE_PRIVATE).edit();
                    line.putString("LINE", "RED");
                    line.apply();

                    Intent intent=new Intent(getActivity(), StorylineActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(mcontext, "未購買", Toast.LENGTH_LONG).show();
                }

            }
        });

        redch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=rPrice_3.getText().toString().substring(0);
                if(s.equals("")){
                    SharedPreferences.Editor ch3 = getActivity().getSharedPreferences("RED", MODE_PRIVATE).edit();
                    ch3.putInt("CH",3);
                    ch3.apply();

                    SharedPreferences.Editor line = getActivity().getSharedPreferences("LINE", MODE_PRIVATE).edit();
                    line.putString("LINE", "RED");
                    line.apply();

                    Intent intent=new Intent(getActivity(), StorylineActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(mcontext, "未購買", Toast.LENGTH_LONG).show();
                }

            }
        });

        redch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=rPrice_4.getText().toString().substring(0);
                if(s.equals("")){
                    SharedPreferences.Editor ch4 = getActivity().getSharedPreferences("RED", MODE_PRIVATE).edit();
                    ch4.putInt("CH",4);
                    ch4.apply();

                    SharedPreferences.Editor line = getActivity().getSharedPreferences("LINE", MODE_PRIVATE).edit();
                    line.putString("LINE", "RED");
                    line.apply();

                    Intent intent=new Intent(getActivity(), StorylineActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(mcontext, "未購買", Toast.LENGTH_LONG).show();
                }

            }
        });

        redch5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=rPrice_5.getText().toString().substring(0);
                if(s.equals("")){
                    SharedPreferences.Editor ch5 = getActivity().getSharedPreferences("RED", MODE_PRIVATE).edit();
                    ch5.putInt("CH",5);
                    ch5.apply();

                    SharedPreferences.Editor line = getActivity().getSharedPreferences("LINE", MODE_PRIVATE).edit();
                    line.putString("LINE", "RED");
                    line.apply();

                    Intent intent=new Intent(getActivity(), StorylineActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(mcontext, "未購買", Toast.LENGTH_LONG).show();
                }

            }
        });

        rbuy1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();

            }});

        rbuy2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String i=String.valueOf(t_score.getText());
                String s=rPrice_2.getText().toString().substring(0);
                int t =Integer.parseInt(i.trim());
                String value_Temp = String.valueOf(t);

                if(check_rch2==true){
                    Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();
                    redch2.setEnabled(true);

                } else{
                    if (t<150){
                        Toast.makeText(mcontext, "金幣不足", Toast.LENGTH_LONG).show();
                        t_score.setText(value_Temp);

                    }else{
                        redch2.setEnabled(true);
                        myRef.child("coin").setValue(t-150);
                        String value_Temp1 = String.valueOf(t-150);
                        t_score.setText(value_Temp1);
                        rbuy2.setImageResource(R.drawable.story_rgot);
                        rPrice_2.setText(null);
                        Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();

                        myRef_rch2.child("2").setValue("true");
                        check_rch2=true;
                    }
                }
            }
        });

        rbuy3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String i=String.valueOf(t_score.getText());
                String s=rPrice_3.getText().toString().substring(0);
                int t =Integer.parseInt(i.trim());
                String value_Temp = String.valueOf(t);


                if(check_rch3==true){
                    Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();
                    redch3.setEnabled(true);

                } else{
                    if (t<150){
                        Toast.makeText(mcontext, "金幣不足", Toast.LENGTH_LONG).show();
                        t_score.setText(value_Temp);

                    }else{
                        redch3.setEnabled(true);
                        myRef.child("coin").setValue(t-150);
                        String value_Temp1 = String.valueOf(t-150);
                        t_score.setText(value_Temp1);
                        rbuy3.setImageResource(R.drawable.story_rgot);
                        rPrice_3.setText(null);
                        Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();

                        myRef_rch3.child("3").setValue("true");
                        check_rch3=true;
                    }
                }
            }
        });

        rbuy4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String i=String.valueOf(t_score.getText());
                String s=rPrice_4.getText().toString().substring(0);
                int t =Integer.parseInt(i.trim());
                String value_Temp = String.valueOf(t);


                if(check_rch4==true){
                    Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();
                    redch4.setEnabled(true);

                } else{
                    if (t<200){
                        Toast.makeText(mcontext, "金幣不足", Toast.LENGTH_LONG).show();
                        t_score.setText(value_Temp);

                    }else{
                        redch4.setEnabled(true);
                        myRef.child("coin").setValue(t-200);
                        String value_Temp1 = String.valueOf(t-200);
                        t_score.setText(value_Temp1);
                        rbuy4.setImageResource(R.drawable.story_rgot);
                        rPrice_4.setText(null);
                        Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();

                        myRef_rch4.child("4").setValue("true");
                        check_rch4=true;
                    }
                }
            }
        });

        rbuy5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String i=String.valueOf(t_score.getText());
                String s=rPrice_5.getText().toString().substring(0);
                int t =Integer.parseInt(i.trim());
                String value_Temp = String.valueOf(t);

                if(check_rch5==true){
                    Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();
                    redch5.setEnabled(true);

                } else{
                    if (t<200){
                        Toast.makeText(mcontext, "金幣不足", Toast.LENGTH_LONG).show();
                        t_score.setText(value_Temp);

                    }else{
                        redch5.setEnabled(true);
                        myRef.child("coin").setValue(t-200);
                        String value_Temp1 = String.valueOf(t-200);
                        t_score.setText(value_Temp1);
                        rbuy5.setImageResource(R.drawable.story_rgot);
                        rPrice_5.setText(null);
                        Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();

                        myRef_rch5.child("5").setValue("true");
                        check_rch5=true;
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


        web.setLongClickable(true);
        web.setTextIsSelectable(true);
        web.setFocusableInTouchMode(true);
        web.setFocusable(true);
        web.setClickable(true);

        web.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("Jing", "LongClick");
                if (myClipboard.hasPrimaryClip()) {
                    Log.d("Jing", "Clipboard has something ?" + myClipboard.getPrimaryClip().getItemCount() );
                    web.setText(myClipboard.getPrimaryClip().getItemAt(0).getText());
                }
                Log.d("Jing", "Print");
                return false;
            }
        });

        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popWindow.setFocusable(true);
        popWindow.showAsDropDown(v, 10, -1200);
        popWindow.update();
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
