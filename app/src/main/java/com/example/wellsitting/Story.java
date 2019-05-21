package com.example.wellsitting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Story extends Fragment {

    private View view;
    FirebaseAuth mAuth;
    ArrayList<Integer> check_actor;//確認是否擁有角色:yes
    Context mcontext;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_story, container, false);

        mcontext=getContext();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef_actor = database.getReference("account").child(user.getUid()).child("actor");
        check_actor = new ArrayList<Integer>();
        myRef_actor.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                check_actor.clear();
                //check_actor_no.clear();

                for (int i = 0; i < 8; i++) {
                    String character = dataSnapshot.child(String.valueOf(i)).getValue(String.class);
                    if (character != null && character.equals("true")) {
                        check_actor.add(i);
                    } else {
                        //check_actor_no.add(i);
                    }
                }
                //check_actor_done = true;

            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //進入小紅帽主線
        ImageButton btn_redhat=view.findViewById(R.id.redhat);
        btn_redhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_actor.contains(7) || check_actor.contains(5)){
                    Storyline_red storyline_red=new Storyline_red();
                    FragmentTransaction fragmentTransaction_character = getFragmentManager().beginTransaction().replace(R.id.fragment_main,storyline_red,null);
                    fragmentTransaction_character.commit();
                }else{
                    Toast.makeText(mcontext,"無該主線角色\n無法開啟該主線劇情！",Toast.LENGTH_LONG).show();
                }

            }
        });
        //進入小美人魚主線
        ImageButton btn_mermaid=view.findViewById(R.id.mermaid);
        btn_mermaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check_actor.contains(0) || check_actor.contains(4)){
                    Storyline_mermaid storyline_mermaid=new Storyline_mermaid();
                    FragmentTransaction fragmentTransaction_character = getFragmentManager().beginTransaction().replace(R.id.fragment_main,storyline_mermaid,null);
                    fragmentTransaction_character.commit();
                }else{
                    Toast.makeText(mcontext,"無該主線角色\n無法開啟該主線劇情！",Toast.LENGTH_LONG).show();
                }

            }
        });

        //進入灰姑娘主線
        ImageButton btn_cindy=view.findViewById(R.id.cindy);
        btn_cindy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_actor.contains(1) || check_actor.contains(3)){
                    Storyline_cindy storyline_cindy=new Storyline_cindy();
                    FragmentTransaction fragmentTransaction_character = getFragmentManager().beginTransaction().replace(R.id.fragment_main,storyline_cindy,null);
                    fragmentTransaction_character.commit();
                }else{
                    Toast.makeText(mcontext,"無該主線角色\n無法開啟該主線劇情！",Toast.LENGTH_LONG).show();
                }

            }
        });

        //進入青蛙王子主線
        ImageButton btn_frog=view.findViewById(R.id.frog);
        btn_frog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_actor.contains(2) || check_actor.contains(6)){
                    Storyline_forg storyline_forg=new Storyline_forg();
                    FragmentTransaction fragmentTransaction_character = getFragmentManager().beginTransaction().replace(R.id.fragment_main,storyline_forg,null);
                    fragmentTransaction_character.commit();
                }else{
                    Toast.makeText(mcontext,"無該主線角色\n無法開啟該主線劇情！",Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
}
