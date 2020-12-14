package com.sefvi.seamarket.View.Activity.Personal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.sefvi.seamarket.Adapter.AdminBillAdapter;
import com.sefvi.seamarket.Adapter.OrderAdapter;
import com.sefvi.seamarket.Api.AdminBill.AdminBillApiLml;
import com.sefvi.seamarket.Interface.CartInterface;
import com.sefvi.seamarket.Model.CartModel;
import com.sefvi.seamarket.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdminAccessBills extends AppCompatActivity {
    static RecyclerView search_rv_admim_bill;
   static   String token;
    static ArrayList<CartModel> cartModelArrayList;
    static AdminBillAdapter adminBillAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_access_bills);

        initEvents();

        getData(AdminAccessBills.this);
    }

    private void initEvents() {
        search_rv_admim_bill = findViewById(R.id.search_rv_admim_bill);
        SharedPreferences prefs = getSharedPreferences("Sea",MODE_PRIVATE);
        token = prefs.getString("TOKEN", "");
    }
    public static void callBackBasket(final Context context){
        getData(context);
    }


    private static void getData(Context context){
        cartModelArrayList = new ArrayList<>();
        AdminBillApiLml adminBillApiLml = new AdminBillApiLml();
        adminBillApiLml.BillAdminApi(token, new CartInterface() {
            @Override
            public void getDataSuccess(String mess) {

            }

            @Override
            public void getDataError(String err) {

            }

            @Override
            public void getDataSuccess(JSONArray list) {
                try {
                    for (int i = 0; i <= list.length(); i++){
                        JSONObject jsonObject = new JSONObject(list.get(i).toString());
                        CartModel cartModel = new CartModel();
                        cartModel.setId(jsonObject.getInt("id"));
                        cartModel.setJsonArray(jsonObject.getJSONArray("cartDetail"));
                        cartModel.setNameUser(jsonObject.getString("nameUser"));
                        cartModel.setPhoneUser(jsonObject.getInt("phoneUser"));
                        cartModel.setAddressUser(jsonObject.getString("addressUser"));
                        cartModel.setSumPrice(jsonObject.getInt("sumPrice"));
                        cartModelArrayList.add(cartModel);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("13_56", cartModelArrayList.get(0).getJsonArray().toString());
                adminBillAdapter = new AdminBillAdapter(context, cartModelArrayList);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1,GridLayoutManager.VERTICAL,false);
                search_rv_admim_bill.setLayoutManager(gridLayoutManager);
                search_rv_admim_bill.setAdapter(adminBillAdapter);
                adminBillAdapter.notifyDataSetChanged();
            }

            @Override
            public void getDataSuccess(JSONObject jsonObject) {

            }
        });
    }
}