package com.example.wellsitting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class Story extends Fragment {

    private View view;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_story, container, false);

        //進入小紅帽主線
        ImageButton btn_redhat=view.findViewById(R.id.redhat);
        btn_redhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storyline_red storyline_red=new Storyline_red();
                FragmentTransaction fragmentTransaction_character = getFragmentManager().beginTransaction().replace(R.id.fragment_main,storyline_red,null);
                fragmentTransaction_character.commit();
            }
        });
        //進入小美人魚主線
        ImageButton btn_mermaid=view.findViewById(R.id.mermaid);
        btn_mermaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storyline_mermaid storyline_mermaid=new Storyline_mermaid();
                FragmentTransaction fragmentTransaction_character = getFragmentManager().beginTransaction().replace(R.id.fragment_main,storyline_mermaid,null);
                fragmentTransaction_character.commit();
            }
        });

        //進入灰姑娘主線
        ImageButton btn_cindy=view.findViewById(R.id.cindy);
        btn_cindy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storyline_cindy storyline_cindy=new Storyline_cindy();
                FragmentTransaction fragmentTransaction_character = getFragmentManager().beginTransaction().replace(R.id.fragment_main,storyline_cindy,null);
                fragmentTransaction_character.commit();
            }
        });

        //進入青蛙王子主線
        ImageButton btn_frog=view.findViewById(R.id.frog);
        btn_frog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storyline_forg storyline_forg=new Storyline_forg();
                FragmentTransaction fragmentTransaction_character = getFragmentManager().beginTransaction().replace(R.id.fragment_main,storyline_forg,null);
                fragmentTransaction_character.commit();
            }
        });

        return view;
    }
}
