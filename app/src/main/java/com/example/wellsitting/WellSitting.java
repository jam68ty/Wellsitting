package com.example.wellsitting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class WellSitting extends AppCompatActivity {




    GoogleSignInClient mGoogleSignInClient;
    //Button btn_logout;
    CircleMenu circleMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_well_sitting);
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);
        //btn_logout=findViewById(R.id.logout);
        //btn_logout.setOnClickListener(v -> Logout());

        circleMenu = findViewById(R.id.circle_menu);
        circleMenu.setMainMenu(Color.parseColor("#869bbd"), R.drawable.menu, R.drawable.cancel)
                .addSubMenu(Color.parseColor("#45578B"), R.drawable.character)
                .addSubMenu(Color.parseColor("#79A4C8"), R.drawable.story)
                .addSubMenu(Color.parseColor("#81C2CC"), R.drawable.settings)
                .addSubMenu(Color.parseColor("#F1E1B5"), R.drawable.home)
                .addSubMenu(Color.parseColor("#C0C0C0"), R.drawable.logout)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {
                        indexCircle(i);
                    }
                }).setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {
            @Override
            public void onMenuOpened() {
            }
            @Override
            public void onMenuClosed() {}

        });
        if(findViewById(R.id.fragment_main)!=null){
            if (savedInstanceState!=null){
                return;
            }
            //一開始設定塞入一個fragment
            MainFragment mainFragment = new MainFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().add(R.id.fragment_main,mainFragment,null);
            fragmentTransaction.commit();
        }


    }
    private void indexCircle(int i) {
        switch (i){
            case 0:
                Toast.makeText(getApplicationContext(),"角色",Toast.LENGTH_SHORT).show();
                Character character = new Character();
                FragmentTransaction fragmentTransaction_character = getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main,character,null);
                fragmentTransaction_character.commit();
                break;
            case 1:
                Toast.makeText(getApplicationContext(),"故事",Toast.LENGTH_SHORT).show();
                Story story = new Story();
                FragmentTransaction fragmentTransaction_story = getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main,story,null);
                fragmentTransaction_story.commit();
                break;
            case 2:
                Toast.makeText(getApplicationContext(),"設定",Toast.LENGTH_SHORT).show();
                Setting setting = new Setting();
                FragmentTransaction fragmentTransaction_setting = getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main,setting,null);
                fragmentTransaction_setting.commit();
                break;
            case 3:
                Toast.makeText(getApplicationContext(),"首頁",Toast.LENGTH_SHORT).show();
                MainFragment main = new MainFragment();
                FragmentTransaction fragmentTransaction_main = getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main,main,null);
                fragmentTransaction_main.commit();
                break;
            case 4:
                Toast.makeText(getApplicationContext(),"登出中",Toast.LENGTH_SHORT).show();
                Logout();
                break;

        }

    }

    void Logout(){
        FirebaseAuth.getInstance().signOut();
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this,task -> {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                });
        finish();
    }
}
