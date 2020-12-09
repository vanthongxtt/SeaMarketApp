package com.sefvi.seamarket.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.sefvi.seamarket.Api.AddCart.AddCartApiLml;
import com.sefvi.seamarket.Interface.CartInterface;
import com.sefvi.seamarket.Model.ProductModel;
import com.sefvi.seamarket.R;
import com.sefvi.seamarket.View.Activity.DetailProductActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

public class Home_ComboHot_Adapter extends RecyclerView.Adapter<Home_ComboHot_Adapter.MyViewHolder> {
    private final Context context;
    private final List<ProductModel> homecomboHots;
    BottomSheetDialog bottomSheetDialog;
    public Home_ComboHot_Adapter(Context context, List<ProductModel> homecomboHots) {
        this.context = context;
        this.homecomboHots = homecomboHots;
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder {
       TextView mTen,mGia;
       ImageView mImage,combothotaddbasket;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTen = itemView.findViewById(R.id.home_combo_hot_tv_ten);
            mGia = itemView.findViewById(R.id.home_combo_hot_tv_gia);
            mImage = itemView.findViewById(R.id.home_combo_hot_img);
            combothotaddbasket =itemView.findViewById(R.id.home_combo_hot_addbasket);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_home_combo_hot,parent,false);
        return new MyViewHolder(v) ;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ProductModel productModel = homecomboHots.get(position);

        holder.mTen.setText(productModel.getName());
        holder.mGia.setText(FormatCost(String.valueOf(productModel.getPrice())) + "đ" + "/kg");
        String url = "https://api.sefvi.com/SeaMarketApi/V1/uploads/" + productModel.getImage();
        Picasso.get()
                .load(url)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.drawable.home_combo_hot_img_cua)
                .into(holder.mImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailProductActivity.class);
                intent.putExtra("idProduct", productModel.getId());
                context.startActivity(intent);
            }
        });
        holder.combothotaddbasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAddCart(holder.itemView, productModel);
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


    private void showDialogAddCart(View view, ProductModel productModel){
         bottomSheetDialog = new BottomSheetDialog(
                view.getContext(),R.style.BottomSheetDialogTheme
        );
        View bottomSheetView = LayoutInflater.from(view.getContext())
                .inflate(
                        R.layout.bottom_dialog_detail_product,
                        view.findViewById(R.id.bottomSheetContainer)
                );
        TextView nameProductDetailItem = bottomSheetView.findViewById(R.id.nameProductDetailItem);
        ImageView product_tablayout_item_img = bottomSheetView.findViewById(R.id.product_tablayout_item_img);
        TextView txtViewPriceItemDetail = bottomSheetView.findViewById(R.id.txtViewPriceItemDetail);
        ImageView imgProductDetailMin = bottomSheetView.findViewById(R.id.imgProductDetailMin);
        TextView txtProductDetailSum = bottomSheetView.findViewById(R.id.txtProductDetailSum);
        ImageView imgProductDetailMax = bottomSheetView.findViewById(R.id.imgProductDetailMax);
        TextView txtViewPriceSumPrice = bottomSheetView.findViewById(R.id.txtViewPriceSumPrice);
        Button bottomSheet_btn_add_basket = bottomSheetView.findViewById(R.id.bottomSheet_btn_add_basket);

        final Integer[] countNumber = {1};

        txtProductDetailSum.setText(String.valueOf(countNumber[0]));



        imgProductDetailMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countNumber[0] < 10){
                    countNumber[0] += 1;
                    txtProductDetailSum.setText(String.valueOf(countNumber[0]));
                    txtViewPriceSumPrice.setText(FormatCost(String.valueOf(productModel.getPrice() * countNumber[0])) + "đ");
                }
            }
        });

        imgProductDetailMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countNumber[0] > 1){
                    countNumber[0] -= 1;
                    txtProductDetailSum.setText(String.valueOf(countNumber[0]));
                    txtViewPriceSumPrice.setText(FormatCost(String.valueOf(productModel.getPrice() * countNumber[0])) + "đ");
                }
            }
        });

        nameProductDetailItem.setText(productModel.getName());
        txtViewPriceItemDetail.setText(FormatCost(String.valueOf(productModel.getPrice())) + "đ" + "/kg");
        txtViewPriceSumPrice.setText(FormatCost(String.valueOf(productModel.getPrice() * countNumber[0])) + "đ");
        String url = "https://api.sefvi.com/SeaMarketApi/V1/uploads/" + productModel.getImage();
        Picasso.get()
                .load(url)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.drawable.home_combo_hot_img_cua)
                .into(product_tablayout_item_img);

        bottomSheet_btn_add_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCart(productModel.getId(), countNumber[0]);
            }
        });
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }
    private void addCart(Integer idProduct, Integer quantily){

        SharedPreferences prefs = context.getSharedPreferences("Sea",MODE_PRIVATE);
        String tokenCart = prefs.getString("TOKEN_CART", "");
        if (tokenCart.isEmpty()){
            SharedPreferences.Editor editor = context.getSharedPreferences("Sea",MODE_PRIVATE).edit();
            String rd = String.valueOf(getRandomNumber(100000, 9999999));
            editor.putString("TOKEN_CART", rd);
            editor.apply();
            addCartDetail(rd, idProduct, quantily);
        }
        Log.d("token_Cart", tokenCart);
        addCartDetail(tokenCart, idProduct, quantily);
    }
    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(100);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }
    private int getRandomNumber(int min,int max) {
        return (new Random()).nextInt((max - min) + 1) + min;
    }
    private void addCartDetail(String tokenCart, Integer idProduct, Integer quantily){
        SharedPreferences prefs = context.getSharedPreferences("Sea",MODE_PRIVATE);
        String token = prefs.getString("TOKEN", "");
        AddCartApiLml addCartApiLml = new AddCartApiLml();
        addCartApiLml.AddCart(token, tokenCart, idProduct, quantily, new CartInterface() {
            @Override
            public void getDataSuccess(String mess) {
                Toast.makeText(context, mess, Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }

            @Override
            public void getDataError(String err) {
                Toast.makeText(context, err, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void getDataSuccess(JSONArray list) {

            }
        });
    }
    @Override
    public int getItemCount() {
        return homecomboHots.size();
    }


}
