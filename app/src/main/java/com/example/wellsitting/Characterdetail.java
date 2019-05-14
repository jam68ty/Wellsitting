package com.example.wellsitting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Characterdetail extends AppCompatActivity {

    //public TextView Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characterdetail);
        getIncomingIntent();
    }

    private void getIncomingIntent(){
        Log.d("ccc","getIncomingIntent");
        if(getIntent().hasExtra("name")&&getIntent().hasExtra("pic")){
            //Log.d("ccc","getIncomingIntent inif");
            String name=getIntent().getStringExtra("name");
            String pic=getIntent().getStringExtra("pic");
            String intro=getIntent().getStringExtra("intro");
            String intro_brief=getIntent().getStringExtra("intro_brief");
            setImage(name,pic,intro,intro_brief);
        }
    }

    private void setImage(String name, String pic,String intro,String intro_brief){
        //Log.d(TAG, "setImage: setting te image and name to widgets.");

        TextView Name = findViewById(R.id.detail_name);
        TextView Intro = findViewById(R.id.detail_intro);
        TextView Intro_brief = findViewById(R.id.detail_intro_brief);
        ImageView Image = findViewById(R.id.detail_pic);

        Name.setText(name);
        Picasso.get().load(pic).into(Image);
        Intro_brief.setText(intro_brief);
        Intro.setText(intro);

    }
}
