package com.sefvi.seamarket.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sefvi.seamarket.Model.ProducFishModel;
import com.sefvi.seamarket.Model.ProducSnailsModel;
import com.sefvi.seamarket.R;

import java.util.List;

public class ProducSnailsAdapter extends RecyclerView.Adapter<ProducSnailsAdapter.MyViewHolder> {
    private Context context;
    private List<ProducSnailsModel> producSnailsModels;

    public ProducSnailsAdapter (Context context, List<ProducSnailsModel> producSnailsModels){
        this.context = context;
        this.producSnailsModels = producSnailsModels;
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
    public ProducSnailsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_product_tablayout,parent,false);
        return new ProducSnailsAdapter.MyViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ProducSnailsAdapter.MyViewHolder holder, int position) {
        ProducSnailsModel producSnailsModel = producSnailsModels.get(position);

        holder.mName.setText(producSnailsModel.getName());
        holder.mPrice.setText(producSnailsModel.getPrice() + "/kg");
        holder.mImg.setImageResource(producSnailsModel.getImg());

    }

    @Override
    public int getItemCount() {
        return producSnailsModels.size();
    }


}
