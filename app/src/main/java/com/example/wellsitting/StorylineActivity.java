package com.example.wellsitting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class StorylineActivity extends AppCompatActivity {

    private View view;
    private DatabaseReference mDatabase;
    DatabaseReference reference;
    private TextView content;
    private Button btn_menu;
    private Button btn_next;
    private ImageView background;

    ArrayList<StoryInformation> storyInformations;
    List sortedList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storyline_fragment);
        content=findViewById(R.id.story_content);
        btn_menu= findViewById(R.id.story_menu);
        btn_next=findViewById(R.id.story_next);
        background=findViewById(R.id.story_background);
        storyInformations=new ArrayList<StoryInformation>();

        SharedPreferences prefs = getSharedPreferences("RED", MODE_PRIVATE);
        Integer pre_sum = prefs.getInt("CH1", 0);
        if(pre_sum==1){
            reference= FirebaseDatabase.getInstance().getReference().child("story/red/ch1");
        }

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {
                    Log.d("kkk", dataSnapshot1.toString());

                    StoryInformation si = dataSnapshot1.getValue(StoryInformation.class);
                    storyInformations.add(si);
                }
                // Sort storyInformations
                Comparator c = new Comparator<StoryInformation>() {
                    @Override
                    public int compare(StoryInformation o1, StoryInformation o2) {
                        // TODO Auto-generated method stub
                        if( o1.getOrder() > o2.getOrder())
                            return 1;
                        else
                            return -1;
                    }
                };


                sortedList = new ArrayList(storyInformations);
                //Collections.sort(storyInformations);
                sortedList.sort(c);
                ArrayList<StoryInformation> sortedStoryInformations = new ArrayList<StoryInformation>(sortedList);
                content.setText(sortedStoryInformations.get(0).getSence());
                Picasso.get().load(sortedStoryInformations.get(0).getBackgroud()).into(background);


                 Log.d("jingjing", "aaa");

 /*                   Log.d("kkk-getBackgroud" , si.getBackgroud());
                    Log.d("kkk-getCharacter_name", si.getCharacter_name());
                    Log.d("kkk-getHead", si.getHead());
                    Log.d("kkk-getSence", si.getSence());

                    content.setText(si.getSence());
                    Picasso.get().load(si.getBackgroud()).into(background);*/

/*
                    Characterheadshot chs=dataSnapshot1.getValue(Characterheadshot.class);
                    list.add(chs);

                    SharedPreferences prefs = getActivity().getSharedPreferences("RED", MODE_PRIVATE);
                    Integer pre_sum = prefs.getInt("CH1", 0);
                    if(pre_sum==1){
                        btn_next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //content.setText(storyInformations.get(po));
                            }
                        });
                    }*/


                    //count++;
                //}
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


}
