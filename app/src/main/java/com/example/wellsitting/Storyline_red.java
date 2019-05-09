package com.example.wellsitting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static android.content.Context.MODE_PRIVATE;


public class Storyline_red extends Fragment {


    private View view;

    private Button redch1;
    private Button redch2;
    private Button redch3;
    private Button redch4;
    private Button redch5;
    private Button redch6;
    private Button redch7;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_storyline_red, container, false);
        redch1=view.findViewById(R.id.redch1);
        redch2=view.findViewById(R.id.redch2);
        redch3=view.findViewById(R.id.redch3);//未做
        redch4=view.findViewById(R.id.redch4);//未做
        redch5=view.findViewById(R.id.redch5);//未做
        redch6=view.findViewById(R.id.redch6);//未做
        redch7=view.findViewById(R.id.redch7);//未做
        redch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor ch1 = getActivity().getSharedPreferences("RED", MODE_PRIVATE).edit();
                ch1.putInt("CH",1);
                ch1.apply();

                Intent intent=new Intent(getActivity(), StorylineActivity.class);
                startActivity(intent);
            }
        });
        redch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor ch2 = getActivity().getSharedPreferences("RED", MODE_PRIVATE).edit();
                ch2.putInt("CH",2);
                ch2.apply();

                Intent intent=new Intent(getActivity(), StorylineActivity.class);
                startActivity(intent);

            }
        });


        return view;
    }
/*    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Storyline_red() {
        // Required empty public constructor
    }

    *//**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Storyline_red.
     *//*
    // TODO: Rename and change types and number of parameters
    public static Storyline_red newInstance(String param1, String param2) {
        Storyline_red fragment = new Storyline_red();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_storyline_red, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    *//**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     *//*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
