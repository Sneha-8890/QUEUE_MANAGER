package com.sneha.sih_2022_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LocationMartAdapter extends RecyclerView.Adapter<LocationMartAdapter.MartHolder> {

    List<LocationMart> data;
    Context context;
    OnClickMallInterface onClickMallInterface;


    public LocationMartAdapter(List<LocationMart> data, Context context, OnClickMallInterface onClickMallInterface) {
        this.data = data;
        this.context = context;
        this.onClickMallInterface = onClickMallInterface;
    }

    @NonNull
    @Override
    public MartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_mart, parent, false);
        return new MartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MartHolder holder, int position) {


        holder.name.setText(data.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MartHolder extends RecyclerView.ViewHolder{


        TextView name;

        public MartHolder(@NonNull View itemView) {
            super(itemView);


            name = itemView.findViewById(R.id.mart_name);
            itemView.setOnClickListener(v-> onClickMallInterface.onMallClick(getAbsoluteAdapterPosition()));

        }
    }

    interface  OnClickMallInterface{
        void onMallClick(int position);
    }


}
