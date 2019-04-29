package com.example.wellsitting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Reward extends AppCompatActivity {

    ImageButton btn_start;//未完成
    ImageButton btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);

        findViewById(R.id.btn_cancel).setBackgroundResource(R.drawable.btn_cancel_d);
        btn_cancel=findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button變色未實現
                finish();
            }
        });
    }
}
