package com.example.wellsitting;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class MyCustomDialog extends DialogFragment {

    private static final String TAG="MyCustomDialog";

    public interface OnInputSelected{
        void sendInput(String input);
    }
    public OnInputSelected mOnInputSelected;

    private EditText mInput;
    private TextView mActionOk,mActionCancel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dialog_my_custom,container,false);
        mActionOk =view.findViewById(R.id.action_ok);
        mActionCancel=view.findViewById(R.id.action_cancel);
        mInput=view.findViewById(R.id.input);

        mActionCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick:  closing dialog");
                getDialog().dismiss();
            }
        });
        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick:  capturing input.");

              String input=mInput.getText().toString();
                if(!input.equals("")){
            /*      RewardFragment fragment=(RewardFragment) getActivity().getSupportFragmentManager().findFragmentByTag("RewardFragment");
                fragment.mInputDisplay.setText(input);
                */
                mOnInputSelected.sendInput(input);
            }
                getDialog().dismiss();
            }
        });
        return view;
    }
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            mOnInputSelected=(OnInputSelected)getTargetFragment();
        }catch (ClassCastException e){
            Log.e(TAG,"onAttach: ClassCastException :  "+e.getMessage());
        }
    }
}
