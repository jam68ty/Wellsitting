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
import com.squareup.picasso.RequestCreator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    int numberOfChapter;
    String filebasePrefix;

    ArrayList<StoryInformation> storyInformations;
    List sortedList;

    // Provide cache mechanism.
    private Map<String, RequestCreator> backgroundCache = new HashMap<String, RequestCreator>();
    private Map<String, RequestCreator> headCache = new HashMap<String, RequestCreator>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storyline_fragment);
        content = findViewById(R.id.story_content);
        name = findViewById(R.id.story_name);
        btn_menu = findViewById(R.id.story_menu);
        btn_next = findViewById(R.id.story_next);
        background = findViewById(R.id.story_background);
        head = findViewById(R.id.story_head);
        storyInformations = new ArrayList<StoryInformation>();


        SharedPreferences lineDatabase = getSharedPreferences("LINE", MODE_PRIVATE);
        String whichLine = lineDatabase.getString("LINE", "UNKNWON");


        if (whichLine.equals("RED")) {
            numberOfChapter = 5; // 小紅帽故事線 has 5 chapter.
            filebasePrefix = "story/red/ch";
        } else if (whichLine.equals("FROG")) {
            numberOfChapter = 4;
            filebasePrefix = "story/frog/ch";
        }else if(whichLine.equals("CINDY")){
            numberOfChapter = 7;
            filebasePrefix = "story/cindy/ch";
        }else if(whichLine.equals("MERMAID")){
            numberOfChapter = 7;
            filebasePrefix = "story/mermaid/ch";
        }
        // TODO(JingJing)

        // Cache experiment of 小紅帽故事線
        for (int i = 0; i < numberOfChapter; ++i) {
            reference = FirebaseDatabase.getInstance().getReference().child(filebasePrefix + String.valueOf(i));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    StoryInformation si = null;
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        si = dataSnapshot1.getValue(StoryInformation.class);
                        backgroundCache.put(si.getBackgroud(), Picasso.get().load(si.getBackgroud()));
                        headCache.put(si.getHead(), Picasso.get().load(si.getHead()));

                    }
                    for (RequestCreator rc : backgroundCache.values())
                        rc.fetch();
                    for (RequestCreator rc : headCache.values())
                        rc.fetch();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }

        //小紅帽故事線
        SharedPreferences redDatabase = getSharedPreferences(whichLine, MODE_PRIVATE);
        Integer chapeter = redDatabase.getInt("CH", 0);
        reference = FirebaseDatabase.getInstance().getReference().child(filebasePrefix + String.valueOf(chapeter));


        /*//青蛙王子主線
        SharedPreferences fch1 = getSharedPreferences("FROG", MODE_PRIVATE);
        Integer fch1_1 = fch1.getInt("CH", 0);
        if (fch1_1 == 1) {
            Log.d("test", "redch1");
            reference = FirebaseDatabase.getInstance().getReference().child("story/frog/ch1");

        }
        SharedPreferences fch2 = getSharedPreferences("FROG", MODE_PRIVATE);
        Integer fch2_2 = fch2.getInt("CH", 0);
        if (fch2_2 == 2) {
            reference = FirebaseDatabase.getInstance().getReference().child("story/frog/ch2");
        }

        SharedPreferences fch3 = getSharedPreferences("FROG", MODE_PRIVATE);
        Integer fch3_3 = fch3.getInt("CH", 0);
        if (fch3_3 == 3) {
            reference = FirebaseDatabase.getInstance().getReference().child("story/frog/ch3");
        }

        SharedPreferences fch4 = getSharedPreferences("FROG", MODE_PRIVATE);
        Integer fch4_4 = ch3.getInt("CH", 0);
        if (ch4_4 == 4) {
            reference = FirebaseDatabase.getInstance().getReference().child("story/frog/ch4");
        }
*/



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("kkk", dataSnapshot1.toString());

                    StoryInformation si = dataSnapshot1.getValue(StoryInformation.class);
                    storyInformations.add(si);
                }
                // Sort storyInformations
                Comparator c = new Comparator<StoryInformation>() {
                    @Override
                    public int compare(StoryInformation o1, StoryInformation o2) {
                        // TODO Auto-generated method stub
                        if (o1.getOrder() > o2.getOrder())
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
                backgroundCache.get(sortedStoryInformations.get(0).getBackgroud()).into(background);


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

        i = 1;
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("ttt","yes");
                if (i != 0 && i < sortedList.size()) {

                    ArrayList<StoryInformation> sortedStoryInformations = new ArrayList<StoryInformation>(sortedList);
                    if (sortedStoryInformations.get(i).getBackgroud().equals("same")) {//背景

                    } else {
                        backgroundCache.get(sortedStoryInformations.get(i).getBackgroud()).into(background);
                    }

                    headCache.get(sortedStoryInformations.get(i).getHead()).into(head);

                    if (sortedStoryInformations.get(i).getCharacter_name().equals("no")) {
                        name.setText("  ");
                    } else if (sortedStoryInformations.get(i).getCharacter_name().equals("???")) {
                        name.setText("???");
                    } else {
                        name.setText(sortedStoryInformations.get(i).getCharacter_name());
                    }
                    content.setText(sortedStoryInformations.get(i).getSence());//對話筐內容


                    i++;
                } else {

                    //Toast.makeText(mcontext,"sorty end",Toast.LENGTH_LONG).show();
                    i = 0;
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
