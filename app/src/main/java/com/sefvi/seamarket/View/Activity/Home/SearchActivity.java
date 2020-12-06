package com.sefvi.seamarket.View.Activity.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.sefvi.seamarket.Adapter.HomeSuggestionAdapter;
import com.sefvi.seamarket.Api.GetProductHome.GetProductHomeApiLml;
import com.sefvi.seamarket.Api.GetSearchProducts.GetSearchProductsApi;
import com.sefvi.seamarket.Api.GetSearchProducts.GetSearchProductsApiLml;
import com.sefvi.seamarket.Interface.ProductRandom;
import com.sefvi.seamarket.Model.ProductModel;
import com.sefvi.seamarket.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    RecyclerView suggestion;
    List<ProductModel> searchListProducts;
    ImageView backicon;
    EditText edt_search_keyword;
    String token;
    HomeSuggestionAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initEvents();
        initControls();

    }
    private void initEvents(){
        backicon = findViewById(R.id.search_toolbar_back);
        suggestion= findViewById(R.id.search_rv_suggestion);
        edt_search_keyword = findViewById(R.id.edt_search_keyword);
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("Sea",MODE_PRIVATE);
        token = prefs.getString("TOKEN", "");
    }
    private  void initControls(){
        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        edt_search_keyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dataKeywordApi(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    private void dataKeywordApi(String keyword){

        searchListProducts = new ArrayList<>();

        GetSearchProductsApiLml getSearchProductsApiLml = new GetSearchProductsApiLml();
        getSearchProductsApiLml.GetSearchProductsApi(token, keyword, new ProductRandom() {
            @Override
            public void getDataSuccess(ProductModel productModel) {

            }

            @Override
            public void getDataError(String err) {

            }

            @Override
            public void getDataSuccess(JSONArray list) {
                searchListProducts.clear();
                for (int i = 0; i <= list.length(); i++){
                    try {
                        JSONObject jsonObject = new JSONObject(list.get(i).toString());
                        ProductModel productModel = new ProductModel();
                        productModel.setId(jsonObject.getInt("id"));
                        productModel.setIdType(jsonObject.getInt("idType"));
                        productModel.setName(jsonObject.getString("name"));
                        productModel.setDescription(jsonObject.getString("description"));
                        productModel.setPrice(jsonObject.getInt("price"));
                        productModel.setUnit(jsonObject.getString("unit"));
                        productModel.setImage(jsonObject.getString("image"));
                        searchListProducts.add(productModel);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter = (HomeSuggestionAdapter) new HomeSuggestionAdapter(searchListProducts,getApplicationContext());
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2,GridLayoutManager.VERTICAL,false);
                suggestion.setLayoutManager(gridLayoutManager);
                suggestion.setAdapter(adapter);

            }
        });

    }
}