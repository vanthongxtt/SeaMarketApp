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
import com.sefvi.seamarket.Model.ProductModel;
import com.sefvi.seamarket.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProducAdapter extends RecyclerView.Adapter<ProducAdapter.MyViewHolder> {
    private final Context context;
    private final List<ProductModel> producModels;

    public ProducAdapter(Context context, List<ProductModel> producModels){
        this.context = context;
        this.producModels = producModels;
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mName,mPrice,mDescribe;
        ImageView mImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.product_tablayout_item_name);
            mPrice = itemView.findViewById(R.id.product_tablayout_item_price);
            mImg = itemView.findViewById(R.id.product_tablayout_item_img);
            mDescribe = itemView.findViewById(R.id.product_tablayout_describe);
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
        ProductModel producModel = producModels.get(position);

        holder.mName.setText(producModel.getName());
        holder.mDescribe.setText(producModel.getDescription());
        holder.mPrice.setText(producModel.getPrice() + "/kg");
        String url = "https://api.sefvi.com/SeaMarketApi/V1/uploads/" + producModel.getImage();
        Picasso.get()
                .load(url)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.drawable.home_combo_hot_img_cua)
                .into(holder.mImg);

    }

    @Override
    public int getItemCount() {
        return producModels.size();
    }


}
