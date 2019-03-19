package com.example.wellsitting;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

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
        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.drawable.menu, R.drawable.cancel)
                .addSubMenu(Color.parseColor("#258CFF"), R.drawable.character)
                .addSubMenu(Color.parseColor("#30A400"), R.drawable.story)
                .addSubMenu(Color.parseColor("#FF4B32"), R.drawable.settings)
                .addSubMenu(Color.parseColor("#8A39FF"), R.drawable.logout)
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
    }
    private void indexCircle(int i) {
        switch (i){
            case 0:
                Toast.makeText(getApplicationContext(),"角色",Toast.LENGTH_LONG).show();
                break;
            case 1:
                Toast.makeText(getApplicationContext(),"故事",Toast.LENGTH_LONG).show();
                break;
            case 2:
                Toast.makeText(getApplicationContext(),"設定",Toast.LENGTH_LONG).show();
                break;
            case 3:
                Toast.makeText(getApplicationContext(),"登出中",Toast.LENGTH_LONG).show();
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
