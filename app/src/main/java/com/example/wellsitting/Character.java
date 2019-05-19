package com.example.wellsitting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Character extends Fragment {


    private View view;
    DatabaseReference reference;
    RecyclerView recyclerView;
    Context mycontext;
    ArrayList<Characterheadshot> list;
    MyAdapter adapter;
    FirebaseAuth mAuth;
    ArrayList<Integer> check_actor;//確認是否擁有角色
    TextView actor_coin;
    Button buy_actor;


    //ImageView imageView;
    //private StorageReference mStorageRef;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_character, container, false);

        actor_coin=view.findViewById(R.id.actor_coin);
        buy_actor=view.findViewById(R.id.buy_actor);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef_1 = database.getReference("account").child(user.getUid()).child("1");
        //讀入account底下的actor項目，以確認使用者已擁有哪些角色
        DatabaseReference myRef = database.getReference("account").child(user.getUid()).child("coin");
        DatabaseReference myRef_0 = database.getReference("account").child(user.getUid()).child("actor");
        DatabaseReference myRef_1 = database.getReference("account").child(user.getUid()).child("actor");
        DatabaseReference myRef_2 = database.getReference("account").child(user.getUid()).child("actor");
        DatabaseReference myRef_3 = database.getReference("account").child(user.getUid()).child("actor");
        DatabaseReference myRef_4 = database.getReference("account").child(user.getUid()).child("actor");
        DatabaseReference myRef_5 = database.getReference("account").child(user.getUid()).child("actor");
        DatabaseReference myRef_6 = database.getReference("account").child(user.getUid()).child("actor");
        DatabaseReference myRef_7 = database.getReference("account").child(user.getUid()).child("actor");
        //DatabaseReference myRef_8 = database.getReference("account").child(user.getUid()).child("actor");



        check_actor=new ArrayList<Integer>();
        //check_actor.add(0);

        myRef_0.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String character = dataSnapshot.child("1").getValue(String.class);
                if(character!=null && character.equals("true")){
                    check_actor.add(0);
                }
                //Log.d("get",String.valueOf(check_actor.get(0)));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myRef_1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String character = dataSnapshot.child("1").getValue(String.class);
                if(character!=null && character.equals("true")){
                    check_actor.add(1);
                }
                //Log.d("get",String.valueOf(check_actor.get(0)));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myRef_2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String character = dataSnapshot.child("2").getValue(String.class);
                if(character!=null && character.equals("true")){
                    check_actor.add(2);
                }
                //Log.d("get",String.valueOf(check_actor.get(1)));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myRef_3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String character = dataSnapshot.child("3").getValue(String.class);
                if(character!=null && character.equals("true")){
                    check_actor.add(3);
                }
                //Log.d("get",String.valueOf(check_actor.get(2)));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myRef_4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String character = dataSnapshot.child("4").getValue(String.class);
                if(character!=null && character.equals("true")){
                    check_actor.add(4);
                }
                //Log.d("get",String.valueOf(check_actor.get(3)));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myRef_5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String character = dataSnapshot.child("5").getValue(String.class);
                if(character!=null && character.equals("true")){
                    check_actor.add(5);
                }
                //Log.d("get",String.valueOf(check_actor.get(4)));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myRef_6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String character = dataSnapshot.child("6").getValue(String.class);
                if(character!=null && character.equals("true")){
                    check_actor.add(6);
                }
                //Log.d("get",String.valueOf(check_actor.get(5)));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myRef_7.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String character = dataSnapshot.child("7").getValue(String.class);
                if(character!=null && character.equals("true")){
                    check_actor.add(7);
                }
                //Log.d("get",String.valueOf(check_actor.get(6)));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //讀取代幣
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
                actor_coin.setText(value_Temp);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });




        /*myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String character1 = dataSnapshot.child("1").getValue(String.class);
                String character2 = dataSnapshot.child("2").getValue(String.class);
                String character3 = dataSnapshot.child("3").getValue(String.class);
                String character4 = dataSnapshot.child("4").getValue(String.class);
                String character5 = dataSnapshot.child("5").getValue(String.class);
                String character6 = dataSnapshot.child("6").getValue(String.class);
                String character7 = dataSnapshot.child("7").getValue(String.class);
                String character8 = dataSnapshot.child("8").getValue(String.class);

                if(character1!=null && character1.equals("true")){
                    check_actor.add(1);
                }else if(character2!=null && character2.equals("true")){
                    check_actor.add(2);
                }else if(character3!=null && character3.equals("true")){
                    check_actor.add(3);
                }else if(character4!=null && character4.equals("true")){
                    check_actor.add(4);
                }else if(character5!=null && character5.equals("true")){
                    check_actor.add(5);
                }else if(character6!=null && character6.equals("true")){
                    check_actor.add(6);
                }else if(character7!=null && character7.equals("true")){
                    check_actor.add(7);
                }else if(character8!=null && character8.equals("true")){
                    check_actor.add(8);
                }else{

                }

                Log.d("get",String.valueOf(check_actor.get(0)));
                Log.d("get",String.valueOf(check_actor.get(1)));
                Log.d("get",String.valueOf(check_actor.get(2)));
                Log.d("get",String.valueOf(check_actor.get(3)));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/



       /*imageView = view.findViewById(R.id.testimg);
       String url="https://firebasestorage.googleapis.com/v0/b/wellsitting-ef0e5.appspot.com/o/character_headshot%2Fcindy.png?alt=media&token=a0a0c5cf-9595-4948-aeb9-437c01d1a399";//Retrieved url as mentioned above
       Glide.with(getActivity().getApplicationContext()).load(url).into(imageView);
       //mStorageRef = FirebaseStorage.getInstance().getReference();*/


        // Inflate the layout for this fragment

        mycontext=inflater.getContext();
        recyclerView=(RecyclerView)view.findViewById(R.id.myrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(mycontext));
        list=new ArrayList<Characterheadshot>();


        reference= FirebaseDatabase.getInstance().getReference().child("character_headshot");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int c=0;
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Characterheadshot chs=dataSnapshot1.getValue(Characterheadshot.class);
                    //如果getkey()有包含在check_actor中（即該角色值＝＝true），則讀出
                    Integer s=Integer.valueOf(chs.getKey());
                    if(check_actor.contains(s)==true){
                        list.add(chs);
                    }
                    c++;
                }
                adapter =new MyAdapter(mycontext,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(mycontext,"test",Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
