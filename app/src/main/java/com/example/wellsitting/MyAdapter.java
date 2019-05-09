package com.example.wellsitting;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Characterheadshot> characterheadshots;
    private AdapterView.OnItemClickListener mOnItemClickListener;


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

        holder.parenlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Characterdetail.class);
                intent.putExtra("pic",characterheadshots.get(position).getPic_halfbody());
                intent.putExtra("name",characterheadshots.get(position).getName());
                intent.putExtra("intro",characterheadshots.get(position).getAll_intro());
                context.startActivity(intent);
            }
        });
        // item click
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return characterheadshots.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView name,intro;
        ImageView pic;
        LinearLayout parenlayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.headname);
            intro=(TextView)itemView.findViewById(R.id.intro);
            pic=(ImageView)itemView.findViewById(R.id.headshot);
            parenlayout=itemView.findViewById(R.id.character_headshot_display);
        }
    }
}

