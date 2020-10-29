package com.sefvi.seamarket.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sefvi.seamarket.Model.ProducClamModel;
import com.sefvi.seamarket.Model.ProducFishModel;
import com.sefvi.seamarket.R;

import java.util.List;

public class ProducClamAdapter extends RecyclerView.Adapter<ProducClamAdapter.MyViewHolder> {
    private Context context;
    private List<ProducClamModel> producClamModels;

    public ProducClamAdapter (Context context, List<ProducClamModel> producClamModels){
        this.context = context;
        this.producClamModels = producClamModels;
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
    public ProducClamAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_product_tablayout,parent,false);
        return new ProducClamAdapter.MyViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ProducClamAdapter.MyViewHolder holder, int position) {
        ProducClamModel producClamModel = producClamModels.get(position);

        holder.mName.setText(producClamModel.getName());
        holder.mPrice.setText(producClamModel.getPrice() + "/kg");
        holder.mImg.setImageResource(producClamModel.getImg());

    }

    @Override
    public int getItemCount() {
        return producClamModels.size();
    }


}
