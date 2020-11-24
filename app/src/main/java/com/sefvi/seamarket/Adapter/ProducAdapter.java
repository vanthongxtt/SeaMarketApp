package com.sefvi.seamarket.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sefvi.seamarket.Model.ProducModel;
import com.sefvi.seamarket.R;
import java.util.List;

public class ProducAdapter extends RecyclerView.Adapter<ProducAdapter.MyViewHolder> {
    private Context context;
    private List<ProducModel> producModels;

    public ProducAdapter(Context context, List<ProducModel> producSnailsModels){
        this.context = context;
        this.producModels = producSnailsModels;
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
    public ProducAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_item_product_tablayout,parent,false);
        return new ProducAdapter.MyViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ProducAdapter.MyViewHolder holder, int position) {
        ProducModel producModel = producModels.get(position);

        holder.mName.setText(producModel.getName());
        holder.mPrice.setText(producModel.getPrice() + "/kg");
        holder.mImg.setImageResource(producModel.getImg());

    }

    @Override
    public int getItemCount() {
        return producModels.size();
    }


}
