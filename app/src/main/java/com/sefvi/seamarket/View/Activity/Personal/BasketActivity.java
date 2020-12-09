package com.sefvi.seamarket.View.Activity.Personal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sefvi.seamarket.Api.GetCart.GetCartApiLml;
import com.sefvi.seamarket.Interface.CartInterface;
import com.sefvi.seamarket.R;

import org.json.JSONArray;
import org.json.JSONException;

public class BasketActivity extends AppCompatActivity {
    ImageView backicon;
    TextView name;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        initEvents();
        initControls();
        GetDataCartList();
    }

    private void initEvents(){
         backicon = findViewById(R.id.toolbar_back);
         name = findViewById(R.id.toolbar_name);

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

    private void GetDataCartList(){
        GetCartApiLml getCartApiLml = new GetCartApiLml();
        getCartApiLml.GetCartList(token, new CartInterface() {
            @Override
            public void getDataSuccess(String mess) {
            }

            @Override
            public void getDataError(String err) {

            }

            @Override
            public void getDataSuccess(JSONArray list) {
                try {
                    Log.d("Base----", list.getString(1));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}