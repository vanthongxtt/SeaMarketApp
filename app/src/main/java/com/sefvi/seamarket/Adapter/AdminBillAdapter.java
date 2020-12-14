package com.sefvi.seamarket.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sefvi.seamarket.Api.ConfrimBillOrderCart.ConfrimBillOrderCartApiLml;
import com.sefvi.seamarket.Interface.CartInterface;
import com.sefvi.seamarket.Model.CartModel;
import com.sefvi.seamarket.R;
import com.sefvi.seamarket.View.Activity.ConfirmActivity;
import com.sefvi.seamarket.View.Activity.DetailProductActivity;
import com.sefvi.seamarket.View.Activity.MainActivity;
import com.sefvi.seamarket.View.Activity.Personal.AdminAccessBills;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class AdminBillAdapter extends RecyclerView.Adapter<AdminBillAdapter.MyViewHolder>  {
    private final Context context;
    private final List<CartModel> cartModels;
    List<CartModel> cartModelList;
    AdminBillProductAdapter adminBillAdapter;
    String token;
    public AdminBillAdapter(Context context, List<CartModel> cartModels){
        this.context = context;
        this.cartModels = cartModels;
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtItemOrderSumPrice, txtItemOrderId, txtItemOrderAddressUser, txtItemOrderPhoneUser, txtItemOrderNameUser;
        Button btnBillAdminConfirm;
        RecyclerView recyclerview_bill_list_product_admin;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btnBillAdminConfirm = itemView.findViewById(R.id.btnBillAdminConfirm);
            txtItemOrderSumPrice = itemView.findViewById(R.id.txtItemOrderSumPrice);
            txtItemOrderId = itemView.findViewById(R.id.txtItemOrderId);
            txtItemOrderAddressUser = itemView.findViewById(R.id.txtItemOrderAddressUser);
            txtItemOrderPhoneUser = itemView.findViewById(R.id.txtItemOrderPhoneUser);
            txtItemOrderNameUser = itemView.findViewById(R.id.txtItemOrderNameUser);
            recyclerview_bill_list_product_admin = itemView.findViewById(R.id.recyclerview_bill_list_product_admin);


        }
    }
    @NonNull
    @Override
    public AdminBillAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_item_bill_admin,parent,false);
        return new AdminBillAdapter.MyViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminBillAdapter.MyViewHolder holder, int position) {
        CartModel cartModel = cartModels.get(position);
        holder.txtItemOrderId.setText(String.valueOf(cartModel.getId()));
        holder.txtItemOrderSumPrice.setText(FormatCost(String.valueOf(cartModel.getSumPrice())) +"đ");
        holder.txtItemOrderNameUser.setText(cartModel.getNameUser());
        holder.txtItemOrderPhoneUser.setText(cartModel.getPhoneUser() + " ");
        holder.txtItemOrderAddressUser.setText(cartModel.getAddressUser());
        SharedPreferences prefs = context.getSharedPreferences("Sea",MODE_PRIVATE);
        token = prefs.getString("TOKEN", "");
        holder.btnBillAdminConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfrimBillOrderCartApiLml confrimBillOrderCartApiLml = new ConfrimBillOrderCartApiLml();
                confrimBillOrderCartApiLml.ConfrimBillOrderCartApi(token, cartModel.getId(), 2, new CartInterface() {
                    @Override
                    public void getDataSuccess(String mess) {
                        AdminAccessBills.callBackBasket(context);
                        Toast.makeText(context, "Thành công!", Toast.LENGTH_SHORT).show();
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
        });

        cartModelList = new ArrayList<>();

        try {
            for (int i = 0; i <= cartModel.getJsonArray().length(); i++){
                JSONObject jsonObject = new JSONObject(cartModel.getJsonArray().get(i).toString());
                CartModel cartModel1 = new CartModel();
                cartModel1.setName(jsonObject.getString("name"));
                cartModel1.setPrice(jsonObject.getInt("price"));
                cartModel1.setQuantily(jsonObject.getInt("quantily"));
                cartModel1.setImage(jsonObject.getJSONObject("image").getString("image"));
                cartModelList.add(cartModel1);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        adminBillAdapter = new AdminBillProductAdapter(context, cartModelList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1,GridLayoutManager.VERTICAL,false);
        holder.recyclerview_bill_list_product_admin.setLayoutManager(gridLayoutManager);
        holder.recyclerview_bill_list_product_admin.setAdapter(adminBillAdapter);
        adminBillAdapter.notifyDataSetChanged();


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
