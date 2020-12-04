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

import com.sefvi.seamarket.Model.ProductModel;
import com.sefvi.seamarket.R;
import com.squareup.picasso.Picasso;


import java.util.List;

public class HomeSuggestionAdapter extends RecyclerView.Adapter<HomeSuggestionAdapter.ViewHolder> {
    private final List<ProductModel> productModelList;
    LayoutInflater inflater;

    public HomeSuggestionAdapter(List<ProductModel> productModels, Context context) {
        this.productModelList = productModels;
        this.inflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public HomeSuggestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = inflater.inflate(R.layout.custom_home_suggestions,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductModel productModel = productModelList.get(position);

        holder.name.setText(productModel.getName());
        holder.price.setText(productModel.getPrice() + "/kg");
        String url = "https://api.sefvi.com/SeaMarketApi/V1/uploads/" + productModel.getImage();
        Picasso.get()
                .load(url)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.drawable.home_combo_hot_img_cua)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return productModelList.size();
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
