package com.sefvi.seamarket.View.Activity.Personal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sefvi.seamarket.Adapter.CartListAdapter;
import com.sefvi.seamarket.Adapter.HomeSuggestionAdapter;
import com.sefvi.seamarket.Adapter.ProducAdapter;
import com.sefvi.seamarket.Api.GetCart.GetCartApiLml;
import com.sefvi.seamarket.Interface.CartInterface;
import com.sefvi.seamarket.Model.CartModel;
import com.sefvi.seamarket.Model.ProductModel;
import com.sefvi.seamarket.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.Policy;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class BasketActivity extends AppCompatActivity {
     ImageView backicon;
     static TextView name, txtSumCart;
     static String token;
     static RecyclerView basket_recyclerview;
     static   List<CartModel> cartModelList;
    static CartListAdapter cartListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        initEvents();
        initControls();
        GetDataCartList(getApplicationContext());
    }

    private void initEvents(){
         backicon = findViewById(R.id.toolbar_back);
         name = findViewById(R.id.toolbar_name);
        basket_recyclerview = findViewById(R.id.basket_recyclerview);
        txtSumCart = findViewById(R.id.txtSumCartBasket);

        SharedPreferences prefs = getSharedPreferences("Sea",MODE_PRIVATE);
        token = prefs.getString("TOKEN", "");

    }

    private void initControls(){

        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        name.setText(getText(R.string.action_basket));
    }

    private static void GetDataCartList(final  Context context){
        cartModelList = new ArrayList<>();
        GetCartApiLml getCartApiLml = new GetCartApiLml();
        getCartApiLml.GetCartList(token, new CartInterface() {
            @Override
            public void getDataSuccess(String mess) {
            }

            @Override
            public void getDataError(String err) {
                cartModelList = new ArrayList<>();
                txtSumCart.setText(0 + " đ");
                cartListAdapter = new CartListAdapter(context, cartModelList);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1,GridLayoutManager.VERTICAL,false);
                basket_recyclerview.setLayoutManager(gridLayoutManager);
                basket_recyclerview.setAdapter(cartListAdapter);
                cartListAdapter.notifyDataSetChanged();
            }

            @Override
            public void getDataSuccess(JSONArray list) {
                Integer sum = 0;
                try {
                    for (int i = 0; i <= list.length(); i++){
                        JSONObject jsonObject = new JSONObject(list.get(i).toString());
                        CartModel cartModel = new CartModel();
                        Integer sumPrice = jsonObject.getInt("sumPrice");
                        cartModel.setName(jsonObject.getString("name"));
                        cartModel.setPrice(jsonObject.getInt("price"));
                        cartModel.setImage(jsonObject.getJSONObject("image").getString("image"));
                        cartModel.setSumPrice(sumPrice);
                        cartModel.setIdCartDetail(jsonObject.getInt("idCartDetail"));
                        cartModel.setQuantily(jsonObject.getInt("quantily"));
                        cartModelList.add(cartModel);
                        sum += sumPrice;
                        Log.d("====", sumPrice + " - " + sum);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                txtSumCart.setText(FormatCost(String.valueOf(sum)) + "đ");
                cartListAdapter = new CartListAdapter(context, cartModelList);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1,GridLayoutManager.VERTICAL,false);
                basket_recyclerview.setLayoutManager(gridLayoutManager);
                basket_recyclerview.setAdapter(cartListAdapter);
                cartListAdapter.notifyDataSetChanged();
            }
        });
    }
    public static void callBackBasket(final Context context){
        GetDataCartList(context);
    }
    private static String FormatCost(String cost){
        try {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator(',');
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###", symbols);
            return decimalFormat.format(Integer.parseInt(cost+""));
        }catch (Exception e) {
            return cost + "";
        }
    }
}