package com.example.wellsitting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class Setting extends Fragment {
    private View view;
    FirebaseAuth mAuth;
    TextView btn_award;
    TextView btn_clock;
    //ImageButton btn_award;
    //ImageButton btn_clock;
    TextView today_sum;
    EditText nickname;
    String str;
    Button edit;
    int sum;
    String nick="nickname";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting, container, false);
        timeremain.start();

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
        today_sum=view.findViewById(R.id.today_sum);
        nickname=view.findViewById(R.id.setting_nickname);
        edit=view.findViewById(R.id.setting_edit);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        updateUI(user);

        //進入獎勵設定

        btn_award=view.findViewById(R.id.award);
        btn_award.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reward reward=new Reward();
                Intent intent = new Intent(getActivity(),Reward.class);
                startActivity(intent);

                // Storyline_forg storyline_forg=new Storyline_forg();
                //                FragmentTransaction fragmentTransaction_character = getFragmentManager().beginTransaction().replace(R.id.fragment_main,storyline_forg,null);
                //                fragmentTransaction_character.commit();
                //Toast.makeText(getContext(),"win",Toast.LENGTH_LONG).show();
                //Log.d("ooo","yes");
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str=String.valueOf(nickname.getText());
                nickname.setText(String.valueOf(nickname.getText()));
                renew();
            }
        });

        //進入鬧鐘設定
        btn_clock=view.findViewById(R.id.clock);
        btn_clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Alarm.class);
                startActivity(intent);
            }
        });
        return view;
    }
//顯示使用者資訊
    private void updateUI(FirebaseUser user) {
        TextView info=view.findViewById(R.id.textInformation);
        ImageView imageAccount=view.findViewById(R.id.imageAccount);
            if (user!=null){
                //String name = user.getDisplayName();
                String email=user.getEmail();
                String photo = String.valueOf(user.getPhotoUrl());

                //info.append(name+"\n");
                info.append(email);

                Picasso.get().load(photo).resize(400,400).into(imageAccount);
            }else {
                info.setText(getString(R.string.app_name));
                Picasso.get().load(R.drawable.como).into(imageAccount);
            }

    }

    //<計時器--Start>

    CountDownTimer timeremain = new CountDownTimer(30000, 1000){//30分鐘：1800000
        @Override
        public void onTick(long millisUntilFinished) {
            SharedPreferences prefs = getActivity().getSharedPreferences("TIMERSUM", MODE_PRIVATE);
            Integer pre_sum = prefs.getInt("SUM", 0);

            if (pre_sum != null) {
                sum=sum+pre_sum;
                today_sum.setText(String.valueOf(pre_sum));
            }
        }
        @Override
        public void onFinish() {

        }
    };
    //<計時器--End>

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        timeremain.cancel();
    }

    //<Database基本功能設定--Start>
    public void delete(){//刪除
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("userset");
        myRef.child(nick).removeValue();
    }
    public void update(){//新增
        final FirebaseDatabase database = FirebaseDatabase.getInstance();//取得資料庫連結
        DatabaseReference myRef= database.getReference("userset");
        myRef.child(nick).setValue("userset");
    }
    public void renew(){//更新
        FirebaseDatabase database = FirebaseDatabase.getInstance();//取得資料庫連結
        DatabaseReference myRef = null;
        myRef= database.getReference("userset");
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(nick,String.valueOf(str));//前面的字是child後面的字是要修改的value值
        myRef.updateChildren(childUpdates);
    }
    //<Database基本功能設定--End>



}
