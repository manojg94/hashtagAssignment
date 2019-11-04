package com.manoj.hashtagassignment.api.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.manoj.hashtagassignment.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.viewHolder>{
    private  Integer values;
    Context context;
    public HorizontalViewAdapter adapter;

    //model and interface

    ArrayList<String> artist = new ArrayList<String>();
    Map<String, ArrayList<String>> namess = new LinkedHashMap<>();


    //step 1
//    public recyclerViewAdapter(List<recyclerModel> recyclerModel) {
//        this.recyclerModel=recyclerModel;
//    }

    public recyclerViewAdapter(ArrayList<String> artist, Map<String, ArrayList<String>> namess, Integer values, Context context) {
        this.artist=artist;
        this.namess=namess;
        this.values=values;
        this.context=context;
    }

    ////step 2
    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView names;
        ImageView image;
        Context context;
        RecyclerView horirecyclerview;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            //initializtions of the UI like textview etc

            context=itemView.getContext();
            names = itemView.findViewById(R.id.moviename);
            image = itemView.findViewById(R.id.image);
            horirecyclerview = itemView.findViewById(R.id.horirecyclerview);

        }

        @Override
        public void onClick(View view) {
        }
    }

    //below are the implementations of recycler view
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //create views here like layout initializtions only and retrurn the layout view
        //retrun viewholder with params as view and interface
        // .inflate 3rd parameter should be false
        final View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_list,viewGroup,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        //here  binding the data to the UI like textview etc.

        for (int j=0;j<namess.size();j++){
            Log.d("gjgh",String.valueOf(namess.get(artist.get(i))));
            viewHolder.names.setText(artist.get(i));
            //viewHolder.ratingrate.setText(String.valueOf(namess.get(artist.get(i))));

            adapter=new HorizontalViewAdapter(namess.get(artist.get(i)));

            GridLayoutManager layoutManager =
                    new GridLayoutManager(context, 3, GridLayoutManager.HORIZONTAL, false);
            viewHolder.horirecyclerview.setLayoutManager(layoutManager);
            viewHolder.horirecyclerview.setAdapter(adapter);



        }
//        viewHolder.names.setText(recyclerModel.get(i).getArtist());
//        viewHolder.ratingrate.setText(String.valueOf(recyclerModel.get(i).getName()));
    }

    @Override
    public int getItemCount() {
        return values;
    }




}
