package com.sefvi.seamarket.Adapter;

import android.content.Context;
import android.content.Intent;
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
import com.sefvi.seamarket.Model.Home_SaleOff;
import com.sefvi.seamarket.Model.ProductModel;
import com.sefvi.seamarket.R;
import com.sefvi.seamarket.View.Activity.DetailProductActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

public class Home_Sale_off_Adapter extends RecyclerView.Adapter<Home_Sale_off_Adapter.MyViewHolder> {
    private final Context context;
    private final List<ProductModel> homeSaleOffs;

    public Home_Sale_off_Adapter(Context context, List<ProductModel> homecomboHots) {
        this.context = context;
        this.homeSaleOffs = homecomboHots;
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder {
        TextView mTen,mGia;
        ImageView mImage,saleoffaddbasket;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTen = itemView.findViewById(R.id.home_sale_off_tv_ten);
            mGia = itemView.findViewById(R.id.home_sale_off_tv_gia);
            mImage = itemView.findViewById(R.id.home_sale_off_img);
            saleoffaddbasket=itemView.findViewById(R.id.home_sale_off_addbasket);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_home_sale_off,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Home_Sale_off_Adapter.MyViewHolder holder, int position) {
        ProductModel productModel = homeSaleOffs.get(position);

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

        holder.saleoffaddbasket.setOnClickListener(new View.OnClickListener() {
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
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
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
                Toast.makeText(view.getContext(), "Chưa thêm vào giỏ hàng được đâu", Toast.LENGTH_SHORT).show();

            }
        });
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }



    @Override
    public int getItemCount() {
        return homeSaleOffs.size();
    }


}
