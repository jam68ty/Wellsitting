package com.example.wellsitting;

import android.content.Context;
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
import android.widget.Toast;

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
    private TextView name;
    private Button btn_menu;
    private Button btn_next;
    private ImageView background;
    private ImageView head;
    Context mcontext;
    int i;

    ArrayList<StoryInformation> storyInformations;
    List sortedList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storyline_fragment);
        content=findViewById(R.id.story_content);
        name=findViewById(R.id.story_name);
        btn_menu= findViewById(R.id.story_menu);
        btn_next=findViewById(R.id.story_next);
        background=findViewById(R.id.story_background);
        head=findViewById(R.id.story_head);
        storyInformations=new ArrayList<StoryInformation>();

        SharedPreferences ch1 = getSharedPreferences("RED", MODE_PRIVATE);
        Integer ch1_1 = ch1.getInt("CH", 0);
        if(ch1_1==1){
            Log.d("test","redch1");
            reference= FirebaseDatabase.getInstance().getReference().child("story/red/ch1");

        }
        SharedPreferences ch2 = getSharedPreferences("RED", MODE_PRIVATE);
        Integer ch2_2 = ch2.getInt("CH", 0);
        if(ch2_2==2){
            reference= FirebaseDatabase.getInstance().getReference().child("story/red/ch2");
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
                name.setText(sortedStoryInformations.get(0).character_name);
                Picasso.get().load(sortedStoryInformations.get(0).getBackgroud()).into(background);


                 //Log.d("jingjing", "aaa");

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

        i=1;
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("ttt","yes");
                if(i!=0 && i<sortedList.size()){

                    ArrayList<StoryInformation> sortedStoryInformations = new ArrayList<StoryInformation>(sortedList);
                    if(sortedStoryInformations.get(i).getBackgroud().equals("same")){//背景

                    }else{
                        Picasso.get().load(sortedStoryInformations.get(i).getBackgroud()).into(background);
                    }

                    Picasso.get().load(sortedStoryInformations.get(i).getHead()).into(head);

                    if(sortedStoryInformations.get(i).getCharacter_name().equals("no")){
                        name.setText("  ");
                    }else if(sortedStoryInformations.get(i).getCharacter_name().equals("???")){
                        name.setText("???");
                    }else{
                        name.setText(sortedStoryInformations.get(i).getCharacter_name());
                    }
                    content.setText(sortedStoryInformations.get(i).getSence());//對話筐內容


                    i++;
                }else{

                    //Toast.makeText(mcontext,"sorty end",Toast.LENGTH_LONG).show();
                    i=0;
                    finish();
                    //Log.d("ttt","yes");
                }


            }
        });

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }


}
