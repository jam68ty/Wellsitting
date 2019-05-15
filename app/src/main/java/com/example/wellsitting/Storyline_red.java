package com.example.wellsitting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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


public class Storyline_red extends Fragment {


    private View view;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_storyline_red, container, false);


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
        DatabaseReference myRef_rch2 = database.getReference("account").child(user.getUid()).child("rch2_buyornot");
        DatabaseReference myRef_rch3 = database.getReference("account").child(user.getUid()).child("rch3_buyornot");
        DatabaseReference myRef_rch4 = database.getReference("account").child(user.getUid()).child("rch4_buyornot");
        DatabaseReference myRef_rch5 = database.getReference("account").child(user.getUid()).child("rch5_buyornot");

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
                t_score.setText("coin:" + value_Temp);



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
                String buyornot = dataSnapshot.child("rch2_buyornot").getValue(String.class);


                //如果抓下來的值為空值，表示沒有存在這個欄位，亦即為新帳號

                // String s=rPrice_2.getText().toString().substring(0);

                if(buyornot!=null && buyornot.equals("true")) {
                    rbuy2.setImageResource(R.drawable.story_rgot);
                    rPrice_2.setText(null);
                    rbuy2.setEnabled(false);
                }else{
                    rbuy2.setImageResource(R.drawable.lock);
                    rPrice_2.setText("100");
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
                String buyornot = dataSnapshot.child("rch3_buyornot").getValue(String.class);


                //如果抓下來的值為空值，表示沒有存在這個欄位，亦即為新帳號
                // String s3=rPrice_3.getText().toString().substring(0);



                if(buyornot!=null && buyornot.equals("true")) {
                    rbuy3.setImageResource(R.drawable.story_rgot);
                    rPrice_3.setText(null);
                    rbuy3.setEnabled(false);
                }else{
                    rbuy3.setImageResource(R.drawable.lock);
                    rPrice_3.setText("100");

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
                String buyornot = dataSnapshot.child("rch4_buyornot").getValue(String.class);


                //如果抓下來的值為空值，表示沒有存在這個欄位，亦即為新帳號
                //String s4=rPrice_4.getText().toString().substring(0);

                if(buyornot!=null && buyornot.equals("true")) {
                    rbuy4.setImageResource(R.drawable.story_rgot);
                    rPrice_4.setText(null);
                    rbuy4.setEnabled(false);
                }else{
                    rbuy4.setImageResource(R.drawable.lock);
                    rPrice_4.setText("100");

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
                String buyornot = dataSnapshot.child("rch5_buyornot").getValue(String.class);


                //如果抓下來的值為空值，表示沒有存在這個欄位，亦即為新帳號

                // String s5=rPrice_5.getText().toString().substring(0);

                if(buyornot!=null && buyornot.equals("true")) {
                    rbuy5.setImageResource(R.drawable.story_rgot);
                    rPrice_5.setText(null);
                    rbuy5.setEnabled(false);
                }else{
                    rbuy5.setImageResource(R.drawable.lock);
                    rPrice_5.setText("100");

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
                String i=t_score.getText().toString().substring(5);
                String s=rPrice_2.getText().toString().substring(0);
                int t =Integer.parseInt(i.trim());
                String value_Temp = String.valueOf(t);



                if(check_rch2==true){
                    Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();
                    redch2.setEnabled(true);

                } else{
                    if (t<100){
                        Toast.makeText(mcontext, "金幣不足", Toast.LENGTH_LONG).show();
                        t_score.setText("coin:" + value_Temp);

                    }else{
                        redch2.setEnabled(true);
                        myRef.child("coin").setValue(t-100);
                        String value_Temp1 = String.valueOf(t-100);
                        t_score.setText("coin:"+value_Temp1);
                        rbuy2.setImageResource(R.drawable.story_rgot);
                        rPrice_2.setText(null);
                        Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();

                        myRef_rch2.child("rch2_buyornot").setValue("true");
                        check_rch2=true;
                    }
                }
            }
        });

        rbuy3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String i=t_score.getText().toString().substring(5);
                String s=rPrice_3.getText().toString().substring(0);
                int t =Integer.parseInt(i.trim());
                String value_Temp = String.valueOf(t);


                if(check_rch3==true){
                    Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();
                    redch3.setEnabled(true);

                } else{
                    if (t<100){
                        Toast.makeText(mcontext, "金幣不足", Toast.LENGTH_LONG).show();
                        t_score.setText("coin:" + value_Temp);

                    }else{
                        redch3.setEnabled(true);
                        myRef.child("coin").setValue(t-100);
                        String value_Temp1 = String.valueOf(t-100);
                        t_score.setText("coin:"+value_Temp1);
                        rbuy3.setImageResource(R.drawable.story_rgot);
                        rPrice_3.setText(null);
                        Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();

                        myRef_rch3.child("rch3_buyornot").setValue("true");
                        check_rch3=true;
                    }
                }
            }
        });

        rbuy4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String i=t_score.getText().toString().substring(5);
                String s=rPrice_4.getText().toString().substring(0);
                int t =Integer.parseInt(i.trim());
                String value_Temp = String.valueOf(t);


                if(check_rch4==true){
                    Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();
                    redch4.setEnabled(true);

                } else{
                    if (t<100){
                        Toast.makeText(mcontext, "金幣不足", Toast.LENGTH_LONG).show();
                        t_score.setText("coin:" + value_Temp);

                    }else{
                        redch4.setEnabled(true);
                        myRef.child("coin").setValue(t-100);
                        String value_Temp1 = String.valueOf(t-100);
                        t_score.setText("coin:"+value_Temp1);
                        rbuy4.setImageResource(R.drawable.story_rgot);
                        rPrice_4.setText(null);
                        Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();

                        myRef_rch4.child("rch4_buyornot").setValue("true");
                        check_rch4=true;
                    }
                }
            }
        });

        rbuy5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String i=t_score.getText().toString().substring(5);
                String s=rPrice_5.getText().toString().substring(0);
                int t =Integer.parseInt(i.trim());
                String value_Temp = String.valueOf(t);


                if(check_rch5==true){
                    Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();
                    redch5.setEnabled(true);

                } else{
                    if (t<100){
                        Toast.makeText(mcontext, "金幣不足", Toast.LENGTH_LONG).show();
                        t_score.setText("coin:" + value_Temp);

                    }else{
                        redch5.setEnabled(true);
                        myRef.child("coin").setValue(t-100);
                        String value_Temp1 = String.valueOf(t-100);
                        t_score.setText("coin:"+value_Temp1);
                        rbuy5.setImageResource(R.drawable.story_rgot);
                        rPrice_5.setText(null);
                        Toast.makeText(mcontext, "已購買", Toast.LENGTH_LONG).show();

                        myRef_rch5.child("rch5_buyornot").setValue("true");
                        check_rch5=true;
                    }
                }
            }
        });


        return view;
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
