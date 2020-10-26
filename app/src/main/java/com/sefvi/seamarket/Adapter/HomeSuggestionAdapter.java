package com.sefvi.seamarket.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sefvi.seamarket.R;


import java.util.List;

public class HomeSuggestionAdapter extends RecyclerView.Adapter<HomeSuggestionAdapter.ViewHolder> {

    List<String> name;
    List<Integer> price;
    List<Integer> img;
    LayoutInflater inflater;

    public HomeSuggestionAdapter(List<String> name, List<Integer> price, List<Integer> img, Context context) {
        this.name = name;
        this.price = price;
        this.img = img;
        this.inflater = LayoutInflater.from(context);

    }

    Context context;


    @NonNull
    @Override
    public HomeSuggestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = inflater.inflate(R.layout.custom_home_suggestions,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(name.get(position));
        holder.price.setText(price.get(position) +"/kg");
        holder.img.setImageResource(img.get(position));
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView price;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.home_suggestion_tv_ten);
            price = itemView.findViewById(R.id.home_suggestion_tv_gia);
            img = itemView.findViewById(R.id.home_suggestion_img);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
