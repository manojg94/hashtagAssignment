package com.manoj.hashtagassignment.api.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manoj.hashtagassignment.R;

import java.util.ArrayList;

public class HorizontalViewAdapter extends  RecyclerView.Adapter<HorizontalViewAdapter.viewHolder>{
    Context context;
    ArrayList<String> artist = new ArrayList<String>();

    public HorizontalViewAdapter(ArrayList<String> artist) {
        this.artist = artist;
        this.context = context;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView names;
        Context context;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            context=itemView.getContext();
            names = itemView.findViewById(R.id.moviename);
        }
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.datadetails,viewGroup,false);

        return new HorizontalViewAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        viewHolder.names.setText(artist.get(i));
    }

    @Override
    public int getItemCount() {
        return artist.size();
    }




}
