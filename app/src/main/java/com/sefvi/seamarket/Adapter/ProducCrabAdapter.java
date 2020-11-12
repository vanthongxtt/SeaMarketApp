package com.sefvi.seamarket.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sefvi.seamarket.Model.ProducCrabModel;
import com.sefvi.seamarket.R;

import java.util.List;

public class ProducCrabAdapter extends RecyclerView.Adapter<ProducCrabAdapter.MyViewHolder> {
    private Context context;
    private List<ProducCrabModel> producCrabModels;

    public ProducCrabAdapter (Context context, List<ProducCrabModel> producCrabModels){
        this.context = context;
        this.producCrabModels= producCrabModels;
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
    public ProducCrabAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_item_product_tablayout,parent,false);
        return new ProducCrabAdapter.MyViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ProducCrabAdapter.MyViewHolder holder, int position) {
        ProducCrabModel producCrabModel = producCrabModels.get(position);

        holder.mName.setText(producCrabModel.getName());
        holder.mPrice.setText(producCrabModel.getPrice() + "/kg");
        holder.mImg.setImageResource(producCrabModel.getImg());

    }

    @Override
    public int getItemCount() {
        return producCrabModels.size();
    }


}

