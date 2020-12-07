package com.sefvi.seamarket.Adapter;

import android.content.Context;
import android.content.Intent;
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

public class Home_ComboHot_Adapter extends RecyclerView.Adapter<Home_ComboHot_Adapter.MyViewHolder> {
    private  Context context;
    private final List<ProductModel> homecomboHots;

    public Home_ComboHot_Adapter(Context context, List<ProductModel> homecomboHots) {
        this.context = context;
        this.homecomboHots = homecomboHots;
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder {
       TextView mTen,mGia;
       ImageView mImage,combothotaddbasket;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTen = itemView.findViewById(R.id.home_combo_hot_tv_ten);
            mGia = itemView.findViewById(R.id.home_combo_hot_tv_gia);
            mImage = itemView.findViewById(R.id.home_combo_hot_img);
            combothotaddbasket =itemView.findViewById(R.id.home_combo_hot_addbasket);

            combothotaddbasket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "combo hot chưa thêm đc", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_home_combo_hot,parent,false);
        return new MyViewHolder(v) ;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ProductModel productModel = homecomboHots.get(position);

        holder.mTen.setText(productModel.getName());
        holder.mGia.setText(FormatCost(String.valueOf(productModel.getPrice())) + productModel.getUnit() + "/kg");
        String url = "https://api.sefvi.com/SeaMarketApi/V1/uploads/" + productModel.getImage();
        Picasso.get()
                .load(url)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.drawable.home_combo_hot_img_cua)
                .into(holder.mImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailProductActivity.class);
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
        return homecomboHots.size();
    }


}
