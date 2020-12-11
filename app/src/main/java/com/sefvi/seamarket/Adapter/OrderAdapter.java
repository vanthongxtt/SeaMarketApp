package com.sefvi.seamarket.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sefvi.seamarket.Api.DeteteCartDetail.DeleteCartDetailApiLml;
import com.sefvi.seamarket.Interface.CartInterface;
import com.sefvi.seamarket.Model.CartModel;
import com.sefvi.seamarket.R;
import com.sefvi.seamarket.View.Activity.DetailProductActivity;
import com.sefvi.seamarket.View.Activity.Personal.BasketActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder>  {
    private final Context context;
    private final List<CartModel> cartModels;

    public OrderAdapter(Context context, List<CartModel> cartModels){
        this.context = context;
        this.cartModels = cartModels;
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtItemOrderName, txtItemOrderPrice, txtItemOrderNumber, txtItemOrderSumPrice, txtItemOrderStatus, txtItemOrderId;
        ImageView imgItemOrderImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
                imgItemOrderImage = itemView.findViewById(R.id.imgItemOrderImage);
                txtItemOrderName = itemView.findViewById(R.id.txtItemOrderName);
                txtItemOrderPrice =  itemView.findViewById(R.id.txtItemOrderPrice);
                txtItemOrderNumber = itemView.findViewById(R.id.txtItemOrderNumber);
                txtItemOrderSumPrice = itemView.findViewById(R.id.txtItemOrderSumPrice);
                txtItemOrderStatus = itemView.findViewById(R.id.txtItemOrderStatus);
                txtItemOrderId = itemView.findViewById(R.id.txtItemOrderId);
        }
    }
    @NonNull
    @Override
    public OrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_item_order,parent,false);
        return new OrderAdapter.MyViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.MyViewHolder holder, int position) {
        CartModel cartModel = cartModels.get(position);
        holder.txtItemOrderId.setText(String.valueOf(cartModel.getId()));
        holder.txtItemOrderName.setText(cartModel.getName());
        holder.txtItemOrderPrice.setText(FormatCost(String.valueOf(cartModel.getPrice()))+ "đ");
        holder.txtItemOrderNumber.setText(String.valueOf(cartModel.getQuantily()));
        holder.txtItemOrderSumPrice.setText(FormatCost(String.valueOf(cartModel.getSumPrice())) +"đ");

        String status = "Đang xử lý";

        switch (cartModel.getStatus()){
            case 1:
                status =  "Đang xử lý";
                break;
            case 2:
                status =  "Đã thanh toán";
                break;
            default:
                break;
        }

        holder.txtItemOrderStatus.setText(status);

        String url = "https://api.sefvi.com/SeaMarketApi/V1/uploads/" + cartModel.getImage();
        Picasso.get()
                .load(url)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.drawable.home_combo_hot_img_cua)
                .into(holder.imgItemOrderImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailProductActivity.class);
                i.putExtra("idProduct", cartModel.getProductId());
                context.startActivity(i);
            }
        });

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
