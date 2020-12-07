package com.sefvi.seamarket.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sefvi.seamarket.Adapter.SliderAdapter;
import com.sefvi.seamarket.Adapter.SliderProductAdapter;
import com.sefvi.seamarket.Api.DetailProduct.DetailProductApiLml;
import com.sefvi.seamarket.Interface.DetailProduct;
import com.sefvi.seamarket.Interface.ProductRandom;
import com.sefvi.seamarket.Model.ProductImageModel;
import com.sefvi.seamarket.Model.ProductModel;
import com.sefvi.seamarket.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DetailProductActivity extends AppCompatActivity {
    ImageView backicon;
    TextView title, detail_product_tv_name_product, item_product_tv_price,detail_product_tv_type_product, detail_product_tv_origin_product, detail_product_tv_dis_product, tv_count_image;
    String token;
    Integer idProduct;
    List<ProductImageModel> productImageModels;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        initEvents();

        initControls();

        GetDetailProduct();

    }

    private void  initEvents(){
        backicon = findViewById(R.id.detail_product_back);
        title = findViewById(R.id.detail_product_title_product);
        detail_product_tv_name_product = findViewById(R.id.detail_product_tv_name_product);
        item_product_tv_price = findViewById(R.id.item_product_tv_price);
        detail_product_tv_type_product = findViewById(R.id.detail_product_tv_type_product);
        detail_product_tv_origin_product = findViewById(R.id.detail_product_tv_origin_product);
        detail_product_tv_dis_product = findViewById(R.id.detail_product_tv_dis_product);
        tv_count_image = findViewById(R.id.tv_count_image);
        viewPager = findViewById(R.id.detail_product_viewpager);

        productImageModels = new ArrayList<>();

        Intent intent = getIntent();
        idProduct = intent.getIntExtra("idProduct", 0);

        SharedPreferences prefs = getApplicationContext().getSharedPreferences("Sea",MODE_PRIVATE);
        token = prefs.getString("TOKEN", "");
    }

    private void initControls(){

        title.setText(getText(R.string.personal_text_caidat));
        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
                    item_product_tv_price.setText(list.getInt("price") + "/" + list.getString("unit"));
                    detail_product_tv_dis_product.setText(list.getString("description"));
                    detail_product_tv_origin_product.setText(list.getString("origin"));
                    detail_product_tv_type_product.setText(jsonObjectType.getString("nameType"));

                    for (int i = 0; i <= jsonArrayImages.length(); i++){
                        JSONObject jsonObjectImage = new JSONObject(jsonArrayImages.getString(i));
                        Log.d("j88", jsonObjectImage.toString());
                        productImageModels.add(new ProductImageModel(jsonObjectImage.getInt("id"), jsonObjectImage.getString("image")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("j87", String.valueOf(productImageModels.size()));
                viewPagerUpdate();

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

                Log.d("count", String.valueOf(currentPageCunter));

                viewPager.setCurrentItem(currentPageCunter[0]++,true);

            }
        };
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