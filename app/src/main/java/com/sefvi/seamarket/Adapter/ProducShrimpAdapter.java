package com.sefvi.seamarket.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sefvi.seamarket.Model.ProducShrimpModel;
import com.sefvi.seamarket.R;

import java.util.List;

public class ProducShrimpAdapter extends RecyclerView.Adapter<ProducShrimpAdapter.MyViewHolder> {
    private Context context;
    private List<ProducShrimpModel> producShrimpModels;

    public ProducShrimpAdapter (Context context, List<ProducShrimpModel> producShrimpModels){
        this.context = context;
        this.producShrimpModels = producShrimpModels;
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mName,mPrice;
        ImageView mImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.product_tablayout_item_name);
            mPrice = itemView.findViewById(R.id.product_tablayout_item_price);
            mImg = itemView.findViewById(R.id.product_tablayout_item_img);
        }
    }
    @NonNull
    @Override
    public ProducShrimpAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_product_tablayout,parent,false);
        return new ProducShrimpAdapter.MyViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ProducShrimpAdapter.MyViewHolder holder, int position) {
        ProducShrimpModel producFishModel = producShrimpModels.get(position);

        holder.mName.setText(producFishModel.getName());
        holder.mPrice.setText(producFishModel.getPrice() + "/kg");
        holder.mImg.setImageResource(producFishModel.getImg());

    }

    @Override
    public int getItemCount() {
        return producShrimpModels.size();
    }


}

