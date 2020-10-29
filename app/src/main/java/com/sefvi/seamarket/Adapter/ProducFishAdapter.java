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
import com.sefvi.seamarket.R;

import java.util.List;

public class ProducFishAdapter extends RecyclerView.Adapter<ProducFishAdapter.MyViewHolder> {
private Context context;
private List<ProducFishModel> producFishModels;

public ProducFishAdapter (Context context, List<ProducFishModel> producFishModels){
    this.context = context;
    this.producFishModels = producFishModels;
}

public static  class MyViewHolder extends RecyclerView.ViewHolder{
    TextView mName,mPrice;
    ImageView mImg;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        mName = itemView.findViewById(R.id.product_fish_name);
        mPrice = itemView.findViewById(R.id.product_fish_price);
        mImg = itemView.findViewById(R.id.product_fish_img);
    }
}
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_product_tablayout_fish,parent,false);
        return new MyViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    ProducFishModel producFishModel = producFishModels.get(position);

    holder.mName.setText(producFishModel.getName());
    holder.mPrice.setText(producFishModel.getPrice() + "/kg");
    holder.mImg.setImageResource(producFishModel.getImg());

    }

    @Override
    public int getItemCount() {
        return producFishModels.size();
    }


}
