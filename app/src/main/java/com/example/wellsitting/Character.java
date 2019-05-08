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
import android.widget.Toast;

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


    //ImageView imageView;
    //private StorageReference mStorageRef;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_character, container, false);



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
                //int count=0;
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Characterheadshot chs=dataSnapshot1.getValue(Characterheadshot.class);
                    list.add(chs);
                    //count++;
                }
                //String ccc=String.valueOf(count);
                //Log.d("ooo",String.valueOf(count));

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
