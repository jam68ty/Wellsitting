package com.example.wellsitting;



import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import android.widget.Spinner;


public class Reward extends AppCompatActivity  {

  /*  private EditText et_Name;
    private TextView tv_Show;
    private Button btn_Show;
*/


    private  static final String TAG="Reward";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);

       RewardFragment fragment=new RewardFragment();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_reward,fragment,"RewardFragment");
        transaction.commit();


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> timelist = ArrayAdapter.createFromResource(Reward.this,
                R.array.time,
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(timelist);

        /* et_Name=(EditText)findViewById(R.id.et_name);
         tv_Show=(TextView)findViewById(R.id.tv_Show);
         btn_Show=(Button)findViewById(R.id.btn_Show);
         btn_Show.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tv_Show.setText((et_Name.getText().toString()));
                et_Name.setText(null);
            }
        });
    }



    private void close(Context context, View editText){
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
    */


    }
}
