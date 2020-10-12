package com.sefvi.seamarket.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sefvi.seamarket.Model.Home_ComboHot;
import com.sefvi.seamarket.R;

import java.util.List;

public class Home_ComboHot_Adapter extends RecyclerView.Adapter<Home_ComboHot_Adapter.MyViewHolder> {
    private  Context context;
    private List<Home_ComboHot> homecomboHots;

    public Home_ComboHot_Adapter(Context context, List<Home_ComboHot> homecomboHots) {
        this.context = context;
        this.homecomboHots = homecomboHots;
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder {
       TextView mTen,mGia;
       ImageView mImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTen = itemView.findViewById(R.id.home_combo_hot_tv_ten);
            mGia = itemView.findViewById(R.id.home_combo_hot_tv_gia);
            mImage = itemView.findViewById(R.id.home_combo_hot_img);
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
        Home_ComboHot home_comboHot = homecomboHots.get(position);

        holder.mTen.setText(home_comboHot.getTen());
        holder.mGia.setText(home_comboHot.getGia()+ "/kg");
        holder.mImage.setImageResource(home_comboHot.getHinh());
    }




    @Override
    public int getItemCount() {
        return homecomboHots.size();
    }


}
