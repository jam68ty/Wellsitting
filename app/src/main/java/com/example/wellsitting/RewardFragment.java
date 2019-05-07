package com.example.wellsitting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class RewardFragment extends Fragment implements MyCustomDialog.OnInputSelected{
    private  static final String TAG="RewardFragment";

    @Override
    public void sendInput(String input) {
        Log.d(TAG,"sendInput:  found incoming input:  " +input);
        mInputDisplay.setText(input);
    }

    private Button mOpenDialog;
    public TextView mInputDisplay;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.reward_fragment_main,container,false);
        mOpenDialog =view.findViewById(R.id.open_diaglog);
        mInputDisplay=view.findViewById(R.id.input_display);

        mOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick:opening dialog");

                MyCustomDialog dialog=new MyCustomDialog();
                dialog.setTargetFragment(RewardFragment.this,1);
                dialog.show(getFragmentManager(),"MyCustomDialog");
            }
        });

        return view;
    }


}

