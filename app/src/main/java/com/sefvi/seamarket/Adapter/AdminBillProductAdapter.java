package com.sefvi.seamarket.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sefvi.seamarket.Model.CartModel;
import com.sefvi.seamarket.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

public class AdminBillProductAdapter extends RecyclerView.Adapter<AdminBillProductAdapter.MyViewHolder>  {
    private final Context context;
    private final List<CartModel> cartModels;

    public AdminBillProductAdapter(Context context, List<CartModel> cartModels){
        this.context = context;
        this.cartModels = cartModels;
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtItemOrderName, txtItemOrderPrice, txtItemOrderNumber;
        ImageView imgItemOrderImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItemOrderImage = itemView.findViewById(R.id.imgItemOrderImage);
            txtItemOrderName = itemView.findViewById(R.id.txtItemOrderName);
            txtItemOrderPrice = itemView.findViewById(R.id.txtItemOrderPrice);
            txtItemOrderNumber = itemView.findViewById(R.id.txtItemOrderNumber);
        }
    }
    @NonNull
    @Override
    public AdminBillProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_item_bill_admin_product_list,parent,false);
        return new AdminBillProductAdapter.MyViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminBillProductAdapter.MyViewHolder holder, int position) {
        CartModel cartModel = cartModels.get(position);

        holder.txtItemOrderName.setText(cartModel.getName());
        holder.txtItemOrderNumber.setText(cartModel.getQuantily() + "");
        holder.txtItemOrderPrice.setText(cartModel.getPrice() + "đ");
        String url = "https://api.sefvi.com/SeaMarketApi/V1/uploads/" + cartModel.getImage();
        Picasso.get()
                .load(url)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.drawable.home_combo_hot_img_cua)
                .into(holder.imgItemOrderImage);


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
        return cartModels.size();
    }
}
