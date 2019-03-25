package com.example.wellsitting;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    static final  int GOOGLE_SIGN = 123;
    FirebaseAuth mAuth;
    Button btn_login;
    GoogleSignInClient mGoogleSignInClient;
    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);

        btn_login=findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();  //創建登入對象

        mGoogleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);  //獲得登入者的資料
        btn_login.setOnClickListener(v -> SignInGoogle());  //Lambda: input -> body
        if(mAuth.getCurrentUser()!=null){
            FirebaseUser user = mAuth.getCurrentUser();
            //updateUI(user);
        }
    }

    void SignInGoogle(){
        Intent signIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signIntent,GOOGLE_SIGN);  //登入作為result
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_SIGN) {
            Task<GoogleSignInAccount> task = GoogleSignIn
                    .getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account!=null){
                    firebaseAuthWithGoogle(account);
                }
            }catch (ApiException e){
                e.getStackTrace();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d("TAG","frebaseAuthWithGoogle: "+account.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this,task->{
                    if (task.isSuccessful()){
                        Log.d("TAG","signin success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        //updateUI(user);
                        //這裡開啟一個新頁面
                        startActivity(new Intent(this,WellSitting.class));
                        finish();
                    }else {
                        Log.w("TAG","signin failure",task.getException());
                        Toast.makeText(this,"Sign in Failed!",Toast.LENGTH_LONG);
                        //updateUI(null);
                    }

                });
    }
//帳戶設定可用

    private void updateUI(FirebaseUser user) {
        if (user!=null){
            String name = user.getDisplayName();
            String email=user.getEmail();
            String photo = String.valueOf(user.getPhotoUrl());

            textView.append("Info: \n");
            textView.append(name);
            textView.append(email);

            Picasso.get().load(photo).into(imageView);
        }else {
            textView.setText(getString(R.string.app_name));
            Picasso.get().load(R.drawable.como).into(imageView);
        }
    }
}

