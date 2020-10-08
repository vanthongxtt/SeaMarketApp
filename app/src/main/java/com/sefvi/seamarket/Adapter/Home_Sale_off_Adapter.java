package com.sefvi.seamarket.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sefvi.seamarket.Model.Home_SaleOff;
import com.sefvi.seamarket.R;

import java.util.List;

public abstract class Home_Sale_off_Adapter extends RecyclerView.Adapter<Home_Sale_off_Adapter.MyViewHolder> {
    private Context context;
    private List<Home_SaleOff> homeSaleOffs;

    public Home_Sale_off_Adapter(Context context, List<Home_SaleOff> homecomboHots) {
        this.context = context;
        this.homeSaleOffs = homecomboHots;
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder {
        TextView mTen,mGia;
        ImageView mImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTen = itemView.findViewById(R.id.home_sale_off_tv_ten);
            mGia = itemView.findViewById(R.id.home_sale_off_tv_gia);
            mImage = itemView.findViewById(R.id.home_sale_off_img);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_home_sale_off,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Home_Sale_off_Adapter.MyViewHolder holder, int position) {
        Home_SaleOff homeSaleOff = homeSaleOffs.get(position);

        holder.mTen.setText(homeSaleOff.getTen());
        holder.mGia.setText(homeSaleOff.getGia()+ "/kg");
        holder.mImage.setImageResource(homeSaleOff.getHinh());

    }





    @Override
    public int getItemCount() {
        return homeSaleOffs.size();
    }


}
