package com.sefvi.seamarket.View.Activity.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sefvi.seamarket.Adapter.HomeSuggestionAdapter;
import com.sefvi.seamarket.Api.GetProductRandom.GetProductRandomLml;
import com.sefvi.seamarket.Interface.ProductRandom;
import com.sefvi.seamarket.Model.ProductModel;
import com.sefvi.seamarket.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SellALotActivity extends AppCompatActivity {
    RecyclerView sellalot;
    List<ProductModel> sellalotList;
    String token;
    HomeSuggestionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_a_lot);
        ImageView backicon = findViewById(R.id.toolbar_back);
        TextView name = findViewById(R.id.toolbar_name);
        sellalot= findViewById(R.id.sellalot_rv_suggestion);

        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        name.setText("Sản phẩm bán chạy");


        SharedPreferences prefs = getApplicationContext().getSharedPreferences("Sea",MODE_PRIVATE);
        token = prefs.getString("TOKEN", "");
        Log.d("toktn", token);

        GetProductRandomLml getProductRandomLml = new GetProductRandomLml();
        getProductRandomLml.GetProductRandomApi(token, new ProductRandom() {
            @Override
            public void getDataSuccess(ProductModel productModel) {

            }

            @Override
            public void getDataError(String err) {

            }

            @Override
            public void getDataSuccess(JSONArray list) {
                sellalotList = new ArrayList<>();
                for (int i = 0; i <= list.length(); i++){
                    try {
                        JSONObject jsonObject = new JSONObject(list.get(i).toString());
                        Log.d("ahihihi-c", jsonObject.getString("name"));
                        ProductModel productModel = new ProductModel();
                        productModel.setId(jsonObject.getInt("id"));
                        productModel.setIdType(jsonObject.getInt("idType"));
                        productModel.setName(jsonObject.getString("name"));
                        productModel.setDescription(jsonObject.getString("description"));
                        productModel.setPrice(jsonObject.getInt("price"));
                        productModel.setUnit(jsonObject.getString("unit"));
                        productModel.setImage(jsonObject.getString("image"));
                        sellalotList.add(productModel);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                adapter = new HomeSuggestionAdapter(sellalotList,SellALotActivity.this);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2,GridLayoutManager.VERTICAL,false);
                sellalot.setLayoutManager(gridLayoutManager);
                sellalot.setAdapter(adapter);

            }
        });
    }
}