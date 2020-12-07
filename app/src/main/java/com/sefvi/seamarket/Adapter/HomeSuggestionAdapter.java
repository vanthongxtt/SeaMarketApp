package com.sefvi.seamarket.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sefvi.seamarket.Model.ProductModel;
import com.sefvi.seamarket.R;
import com.sefvi.seamarket.View.Activity.DetailProductActivity;
import com.squareup.picasso.Picasso;


import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
        holder.price.setText(FormatCost(String.valueOf(productModel.getPrice())) + productModel.getUnit() + "/kg");
        String url = "https://api.sefvi.com/SeaMarketApi/V1/uploads/" + productModel.getImage();
        Picasso.get()
                .load(url)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.drawable.home_combo_hot_img_cua)
                .into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = inflater.getContext();
                Intent intent = new Intent(context, DetailProductActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("idProduct", productModel.getId());
                context.startActivity(intent);
            }
        });
    }
    private String FormatCost(String cost){
        try {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator(',');
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###", symbols);
            return decimalFormat.format(Integer.parseInt(cost+""));
        }catch (Exception e) {
            return cost + "";
        }
    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView price;
        ImageView img,suggestionaddbasket;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.home_suggestion_tv_ten);
            price = itemView.findViewById(R.id.home_suggestion_tv_gia);
            img = itemView.findViewById(R.id.home_suggestion_img);
            suggestionaddbasket=itemView.findViewById(R.id.home_suggestion_addbasket);

            suggestionaddbasket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "suggestion chưa thêm đc", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
