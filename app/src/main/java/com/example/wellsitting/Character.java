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
import java.util.Random;


public class Character extends Fragment {


    private View view;
    DatabaseReference reference;
    RecyclerView recyclerView;
    Context mycontext;
    Context correct_context;
    ArrayList<Characterheadshot> list;
    MyAdapter adapter;
    FirebaseAuth mAuth;
    ArrayList<Integer> check_actor;//確認是否擁有角色:yes
    ArrayList<Integer> check_actor_no;//確認是否擁有角色:no
    boolean check_actor_done = false;
    TextView actor_coin;
    boolean actor_coin_read = false;
    Button buy_actor;


    //ImageView imageView;
    //private StorageReference mStorageRef;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_character, container, false);


        correct_context=getContext();
        actor_coin = view.findViewById(R.id.actor_coin);
        buy_actor = view.findViewById(R.id.buy_actor);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef_1 = database.getReference("account").child(user.getUid()).child("1");
        //讀入account底下的actor項目，以確認使用者已擁有哪些角色
        DatabaseReference myRef = database.getReference("account").child(user.getUid()).child("coin");
        DatabaseReference myRef_actor = database.getReference("account").child(user.getUid()).child("actor");


        check_actor = new ArrayList<Integer>();
        check_actor_no = new ArrayList<Integer>();
        //check_actor.add(0);

        myRef_actor.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                check_actor.clear();
                check_actor_no.clear();

                for (int i = 0; i < 8; i++) {
                    String character = dataSnapshot.child(String.valueOf(i)).getValue(String.class);
                    if (character != null && character.equals("true")) {
                        check_actor.add(i);
                    } else {
                        check_actor_no.add(i);
                    }
                }
                check_actor_done = true;
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
                actor_coin_read = true;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });


        buy_actor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_actor_done != true || actor_coin_read != true) {
                    Toast.makeText(correct_context, "資料庫讀取中...", Toast.LENGTH_LONG);
                    return;
                }
                String i = String.valueOf(actor_coin.getText());
                int t = Integer.parseInt(i.trim());
                String value_Temp = String.valueOf(t);
                if (t < 100) {
                    Toast.makeText(correct_context, "錢不夠喔窮人", Toast.LENGTH_LONG);
                } else {
                    //和錢有關的動作
                    myRef.child("coin").setValue(t - 100);
                    String value_Temp1 = String.valueOf(t - 100);
                    actor_coin.setText(value_Temp1);

                    //角色
                    Random ran = new Random();
                    int r = ran.nextInt(check_actor_no.size());
                    myRef_actor.child(String.valueOf(check_actor_no.get(r))).setValue("true");
                    Toast.makeText(mycontext, "已購買", Toast.LENGTH_LONG);
                    check_actor.add(check_actor_no.get(r));
                    check_actor_no.remove(check_actor_no.get(r));
                    RefreshHeadCount();
                }


            }
        });





       /*imageView = view.findViewById(R.id.testimg);
       String url="https://firebasestorage.googleapis.com/v0/b/wellsitting-ef0e5.appspot.com/o/character_headshot%2Fcindy.png?alt=media&token=a0a0c5cf-9595-4948-aeb9-437c01d1a399";//Retrieved url as mentioned above
       Glide.with(getActivity().getApplicationContext()).load(url).into(imageView);
       //mStorageRef = FirebaseStorage.getInstance().getReference();*/


        // Inflate the layout for this fragment

        mycontext = inflater.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.myrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(mycontext));
        list = new ArrayList<Characterheadshot>();

        RefreshHeadCount();
        return view;
    }


    private void RefreshHeadCount() {
            reference = FirebaseDatabase.getInstance().getReference().child("character_headshot");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list.clear();

                    int c = 0;
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Characterheadshot chs = dataSnapshot1.getValue(Characterheadshot.class);
                        //如果getkey()有包含在check_actor中（即該角色值＝＝true），則讀出
                        Integer s = Integer.valueOf(chs.getKey());
                        if (check_actor.contains(s) == true) {
                            list.add(chs);
                        }
                        c++;
                    }
                    adapter = new MyAdapter(mycontext, list);
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(mycontext, "test", Toast.LENGTH_LONG).show();
                }
            });
    };
}
