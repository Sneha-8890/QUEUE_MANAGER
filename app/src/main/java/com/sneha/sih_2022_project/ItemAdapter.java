package com.sneha.sih_2022_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    ArrayList<ItemModel>list; Context context;

    public ItemAdapter(ArrayList<ItemModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_holder,parent,false);
        return new ItemViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.Itemname.setText(list.get(position).getProd_name());
        holder.Itemprice.setText(list.get(position).getProd_price());
        holder.textquantity.setText(list.get(position).getQty());
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{


        TextView Itemname, Itemprice;
       ImageView img;
       EditText textquantity;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            Itemname = itemView.findViewById(R.id.item_name);
            Itemprice = itemView.findViewById(R.id.item_price);
            textquantity = itemView.findViewById(R.id.item_qty);


        }
    }
}
