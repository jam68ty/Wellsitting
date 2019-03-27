package com.example.wellsitting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class Setting extends Fragment {
    private View view;
    FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting, container, false);

        //Button button = view.findViewById(R.id.button2);
        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Test for button in fragment2",Toast.LENGTH_LONG).show();
                //在fragment裡面可以用一樣的方法替代fragment
                //getFragmentManager與getSupportFragmentManager差別在於使用的是android.app.FragmentManager或android.support.v4.app.FragmentManager
                Character character = new Character();
                FragmentTransaction fragmentTransaction_character = getFragmentManager().beginTransaction().replace(R.id.fragment_main,character,null);
                //fragmentTransaction_character.addToBackStack(null);
                fragmentTransaction_character.commit();
            }
        });*/
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        updateUI(user);

        return view;
    }
//顯示使用者資訊
    private void updateUI(FirebaseUser user) {
        TextView info=view.findViewById(R.id.textInformation);
        ImageView imageAccount=view.findViewById(R.id.imageAccount);
            if (user!=null){
                String name = user.getDisplayName();
                String email=user.getEmail();
                String photo = String.valueOf(user.getPhotoUrl());

                info.append(name+"\n");
                info.append(email);

                Picasso.get().load(photo).resize(400,400).into(imageAccount);
            }else {
                info.setText(getString(R.string.app_name));
                Picasso.get().load(R.drawable.como).into(imageAccount);
            }

    }


}
