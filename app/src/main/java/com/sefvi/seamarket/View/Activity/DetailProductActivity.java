package com.sefvi.seamarket.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.sefvi.seamarket.Adapter.SliderAdapter;
import com.sefvi.seamarket.Adapter.SliderProductAdapter;
import com.sefvi.seamarket.Api.AddCart.AddCartApiLml;
import com.sefvi.seamarket.Api.DetailProduct.DetailProductApiLml;
import com.sefvi.seamarket.Api.GetCountNotiCart.GetCountNotiCartApiLml;
import com.sefvi.seamarket.Interface.CartInterface;
import com.sefvi.seamarket.Interface.DetailProduct;
import com.sefvi.seamarket.Interface.ProductRandom;
import com.sefvi.seamarket.Model.ProductImageModel;
import com.sefvi.seamarket.Model.ProductModel;
import com.sefvi.seamarket.R;
import com.sefvi.seamarket.View.Activity.Personal.BasketActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class DetailProductActivity extends AppCompatActivity {
    ImageView backicon;
    LinearLayout home_basket_top;
    TextView title, detail_product_tv_name_product, item_product_tv_price,detail_product_tv_type_product,
            detail_product_tv_origin_product, detail_product_tv_dis_product, tv_count_image, home_number_basket;
    String token;
    Integer idProduct;
    List<ProductImageModel> productImageModels;
    ViewPager viewPager;
    Button detail_product_btn_add_basket;
    String nameProduct, priceProduct;
    BottomSheetDialog bottomSheetDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        initEvents();

        initControls();
        getCountNoti();
        GetDetailProduct();

    }

    private void  initEvents(){
        backicon = findViewById(R.id.detail_product_back);
        home_basket_top = findViewById(R.id.home_basket_top);
        title = findViewById(R.id.detail_product_title_product);
        detail_product_tv_name_product = findViewById(R.id.detail_product_tv_name_product);
        item_product_tv_price = findViewById(R.id.item_product_tv_price);
        detail_product_tv_type_product = findViewById(R.id.detail_product_tv_type_product);
        detail_product_tv_origin_product = findViewById(R.id.detail_product_tv_origin_product);
        detail_product_tv_dis_product = findViewById(R.id.detail_product_tv_dis_product);
        tv_count_image = findViewById(R.id.tv_count_image);
        viewPager = findViewById(R.id.detail_product_viewpager);
        home_number_basket = findViewById(R.id.home_number_basket);

        detail_product_btn_add_basket = findViewById(R.id.detail_product_btn_add_basket);


        productImageModels = new ArrayList<>();

        Intent intent = getIntent();
        idProduct = intent.getIntExtra("idProduct", 0);

        SharedPreferences prefs = getApplicationContext().getSharedPreferences("Sea",MODE_PRIVATE);
        token = prefs.getString("TOKEN", "");

    }

    private void initControls(){
        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        home_basket_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), BasketActivity.class);
                startActivity(intent);
            }
        });
        detail_product_btn_add_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog = new BottomSheetDialog(
                        DetailProductActivity.this,R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(
                                R.layout.bottom_dialog_detail_product,
                                findViewById(R.id.bottomSheetContainer)
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
                            txtViewPriceSumPrice.setText(FormatCost(String.valueOf(Integer.parseInt(priceProduct) * countNumber[0])) + "đ");
                        }
                    }
                });

                imgProductDetailMin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (countNumber[0] > 1){
                            countNumber[0] -= 1;
                            txtProductDetailSum.setText(String.valueOf(countNumber[0]));
                            txtViewPriceSumPrice.setText(FormatCost(String.valueOf(Integer.parseInt(priceProduct) * countNumber[0])) + "đ");
                        }
                    }
                });

                nameProductDetailItem.setText(nameProduct);
                txtViewPriceItemDetail.setText(FormatCost(priceProduct) + "đ" + "/kg");
                txtViewPriceSumPrice.setText(FormatCost(String.valueOf(Integer.parseInt(priceProduct) * countNumber[0])) + "đ");
                String url = "https://api.sefvi.com/SeaMarketApi/V1/uploads/" + productImageModels.get(0).getNameImage();
                Picasso.get()
                        .load(url)
                        .placeholder(R.mipmap.ic_launcher_round)
                        .error(R.drawable.home_combo_hot_img_cua)
                        .into(product_tablayout_item_img);

                bottomSheet_btn_add_basket.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addCart(idProduct, countNumber[0]);
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });
    }
    private void addCart(Integer idProduct, Integer quantily){

        SharedPreferences prefs = getApplicationContext().getSharedPreferences("Sea",MODE_PRIVATE);
        String tokenCart = prefs.getString("TOKEN_CART", "");

        if (tokenCart.isEmpty()){
            SharedPreferences.Editor editor = getSharedPreferences("Sea",MODE_PRIVATE).edit();
            String rd = String.valueOf(getRandomNumber(100000, 9999999));
            editor.putString("TOKEN_CART", rd);
            editor.apply();
            addCartDetail(rd, idProduct, quantily);
        }
        addCartDetail(tokenCart, idProduct, quantily);
    }
    private int getRandomNumber(int min,int max) {
        return (new Random()).nextInt((max - min) + 1) + min;
    }
    private void addCartDetail(String tokenCart, Integer idProduct, Integer quantily){
        AddCartApiLml addCartApiLml = new AddCartApiLml();
        addCartApiLml.AddCart(token, tokenCart, idProduct, quantily, new CartInterface() {
            @Override
            public void getDataSuccess(String mess) {
                Toast.makeText(getApplicationContext(), mess, Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
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
    private void GetDetailProduct(){
        DetailProductApiLml detailProductApiLml = new DetailProductApiLml();
        detailProductApiLml.DetailProductApi(token, idProduct, new DetailProduct() {
            @Override
            public void getDataSuccess(ProductModel productModel) {

            }

            @Override
            public void getDataError(String err) {
            }

            @Override
            public void getDataSuccess(JSONObject list) {
                try {

                    JSONArray jsonArrayType = new JSONArray(list.getString("type"));
                    JSONArray jsonArrayImages = new JSONArray(list.getString("image"));
                    JSONObject jsonObjectType = new JSONObject(jsonArrayType.getString(0));

                    title.setText(list.getString("name"));
                    detail_product_tv_name_product.setText(list.getString("name"));
                    item_product_tv_price.setText(FormatCost(String.valueOf(list.getInt("price"))) + "đ" + "/kg");
                    detail_product_tv_dis_product.setText(list.getString("description"));
                    detail_product_tv_origin_product.setText(list.getString("origin"));
                    detail_product_tv_type_product.setText(jsonObjectType.getString("nameType"));

                    nameProduct = list.getString("name");
                    priceProduct = list.getString("price");

                    for (int i = 0; i <= jsonArrayImages.length(); i++){
                        JSONObject jsonObjectImage = new JSONObject(jsonArrayImages.getString(i));
                        productImageModels.add(new ProductImageModel(jsonObjectImage.getInt("id"), jsonObjectImage.getString("image")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                viewPagerUpdate();

            }
        });
    }
    private void getCountNoti(){
        GetCountNotiCartApiLml getCountNotiCartApiLml = new GetCountNotiCartApiLml();
        getCountNotiCartApiLml.GetCountNotiCartApi(token, new CartInterface() {
            @Override
            public void getDataSuccess(String mess) {
                try {
                    if (Integer.parseInt(mess) == 0){
                        home_number_basket.setVisibility(View.INVISIBLE);
                    }else {
                        home_number_basket.setText(mess);
                    }
                }catch (Exception ignored){
                    home_number_basket.setVisibility(View.INVISIBLE);
                }
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

    private  void viewPagerUpdate(){
        final int[] currentPageCunter = {0};

        viewPager.setAdapter(new SliderProductAdapter(productImageModels, getApplicationContext()));

        final Handler handler = new Handler();
        final Runnable update  = new Runnable() {
            @Override
            public void run() {
                if (currentPageCunter[0] == productImageModels.size()){
                    currentPageCunter[0] = 0 ;
                }
                viewPager.setCurrentItem(currentPageCunter[0]++,true);
                getCountNoti();
            }
        };
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },2500,2500);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tv_count_image.setText((position + 1) + "/" + productImageModels.size());
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}