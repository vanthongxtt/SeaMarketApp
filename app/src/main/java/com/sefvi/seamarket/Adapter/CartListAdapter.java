package com.sefvi.seamarket.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.sefvi.seamarket.Api.AddCart.AddCartApiLml;
import com.sefvi.seamarket.Api.DeteteCartDetail.DeleteCartDetailApiLml;
import com.sefvi.seamarket.Interface.CartInterface;
import com.sefvi.seamarket.Model.CartModel;
import com.sefvi.seamarket.Model.ProductModel;
import com.sefvi.seamarket.R;
import com.sefvi.seamarket.View.Activity.DetailProductActivity;
import com.sefvi.seamarket.View.Activity.Personal.BasketActivity;
import com.sefvi.seamarket.View.Activity.Personal.LanguageActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.MyViewHolder> {
    private final Context context;
    private final List<CartModel> cartModels;

    public CartListAdapter(Context context, List<CartModel> cartModels){
        this.context = context;
        this.cartModels = cartModels;
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itemBasketName, item_basket_price;
        ImageView itemBasketImg, itemBasketImgClose;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemBasketImg = itemView.findViewById(R.id.itemBasketImg);
            itemBasketImgClose = itemView.findViewById(R.id.itemBasketImgClose);
            itemBasketName = itemView.findViewById(R.id.itemBasketName);
            item_basket_price = itemView.findViewById(R.id.item_basket_price);
        }
    }
    @NonNull
    @Override
    public CartListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_item_basket,parent,false);
        return new CartListAdapter.MyViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.MyViewHolder holder, int position) {
        CartModel cartModel = cartModels.get(position);

        holder.itemBasketName.setText(cartModel.getName());
        holder.item_basket_price.setText(FormatCost(String.valueOf(cartModel.getPrice()))+ "đ x " + cartModel.getQuantily());

        String url = "https://api.sefvi.com/SeaMarketApi/V1/uploads/" + cartModel.getImage();
        Picasso.get()
                .load(url)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.drawable.home_combo_hot_img_cua)
                .into(holder.itemBasketImg);

        holder.itemBasketImgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Delete product?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteCartDetail(cartModel.getIdCartDetail());
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

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

    private void deleteCartDetail(Integer id){

        SharedPreferences prefs = context.getSharedPreferences("Sea",MODE_PRIVATE);
        String token = prefs.getString("TOKEN", "");

        DeleteCartDetailApiLml deleteCartDetailApiLml = new DeleteCartDetailApiLml();
        deleteCartDetailApiLml.DeleteCartDetailApi(token, id, new CartInterface() {
            @Override
            public void getDataSuccess(String mess) {
                Log.d("Ssssss", mess);
                BasketActivity.callBackBasket(context);
            }

            @Override
            public void getDataError(String err) {

            }

            @Override
            public void getDataSuccess(JSONArray list) {

            }

            @Override
            public void getDataSuccess(JSONObject jsonObject) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return cartModels.size();
    }
}
