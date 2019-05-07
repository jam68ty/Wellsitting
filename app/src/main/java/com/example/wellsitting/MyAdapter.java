package com.example.wellsitting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Characterheadshot> characterheadshots;

    public MyAdapter(Context c,ArrayList<Characterheadshot> chs){
        context=c;
        characterheadshots=chs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.character_headhot_display,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(characterheadshots.get(position).getName());
        holder.intro.setText(characterheadshots.get(position).getBrief_intro());
        Picasso.get().load(characterheadshots.get(position).getPic_headshot()).into(holder.pic);

    }

    @Override
    public int getItemCount() {
        return characterheadshots.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView name,intro;
        ImageView pic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.headname);
            intro=(TextView)itemView.findViewById(R.id.intro);
            pic=(ImageView)itemView.findViewById(R.id.headshot);
        }
    }
}

