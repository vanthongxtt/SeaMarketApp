package com.sefvi.seamarket.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sefvi.seamarket.Api.GetBillOrder.GetBillOrderApiLml;
import com.sefvi.seamarket.Interface.CartInterface;
import com.sefvi.seamarket.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class ConfirmActivity extends AppCompatActivity {
    ImageView backicon;
    TextView txtToolbarName, txtNameConfirm, txtPhoneConfirm, txtAddressConfirm, txtSumProductConfirm, txtSumPriceProductConfirm, txtDeliverychargesConfirm, txtSumPriceConfirm, txtSumPriceConfirmFooter;
    Button btnBillOrderConfirm;
    String token;
    Integer idCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        initEvents();
        initControls();
        getData();
    }
    private void initEvents(){

        backicon = findViewById(R.id.toolbar_back);
        txtToolbarName = findViewById(R.id.toolbar_name);

        txtNameConfirm = findViewById(R.id.txtNameConfirm);
        txtPhoneConfirm = findViewById(R.id.txtPhoneConfirm);
        txtAddressConfirm = findViewById(R.id.txtAddressConfirm);
        txtSumPriceConfirm = findViewById(R.id.txtSumPriceConfirm);
        txtSumProductConfirm = findViewById(R.id.txtSumProductConfirm);
        txtSumPriceProductConfirm = findViewById(R.id.txtSumPriceProductConfirm);
        txtDeliverychargesConfirm = findViewById(R.id.txtDeliverychargesConfirm);
        txtSumPriceConfirmFooter = findViewById(R.id.txtSumPriceConfirmFooter);
        btnBillOrderConfirm = findViewById(R.id.btnBillOrderConfirm);

        SharedPreferences prefs = getSharedPreferences("Sea",MODE_PRIVATE);
        token = prefs.getString("TOKEN", "");

        Intent intent = getIntent();
        idCart = intent.getIntExtra("idCart", 0);
    }

    private void initControls(){

        txtToolbarName.setText("Xác nhận đơn hàng");
        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnBillOrderConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void getData(){
        GetBillOrderApiLml getBillOrderApiLml = new GetBillOrderApiLml();
        getBillOrderApiLml.GetBillOrderApi(token, idCart, new CartInterface() {
            @Override
            public void getDataSuccess(String mess) {

            }

            @Override
            public void getDataError(String err) {
                Log.d("4g6", err);
            }

            @Override
            public void getDataSuccess(JSONArray list) {

            }

            @Override
            public void getDataSuccess(JSONObject jsonObject) {
                try {
                    txtNameConfirm.setText(jsonObject.getString("fullName"));
                    txtPhoneConfirm.setText(jsonObject.getString("phone"));
                    txtAddressConfirm.setText(jsonObject.getString("address"));
                    txtSumProductConfirm.setText(jsonObject.getString("sumProduct"));
                    txtSumPriceProductConfirm.setText(FormatCost(jsonObject.getString("sumPriceProduct")) + "đ");
                    String sumPrice = String.valueOf(Integer.parseInt(jsonObject.getString("sumPriceProduct")) + 11000);
                    txtSumPriceConfirmFooter.setText(FormatCost(sumPrice) + "đ");
                    txtSumPriceConfirm.setText(FormatCost(sumPrice) + "đ");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
}