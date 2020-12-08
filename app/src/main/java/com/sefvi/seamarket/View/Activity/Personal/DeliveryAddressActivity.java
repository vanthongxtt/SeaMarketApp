package com.sefvi.seamarket.View.Activity.Personal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sefvi.seamarket.R;

import static android.app.PendingIntent.getActivity;

public class DeliveryAddressActivity extends AppCompatActivity {
    LinearLayout deliveryAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_address);


        Anhxa();
        event();

        ImageView backicon = findViewById(R.id.toolbar_back);
        TextView name = findViewById(R.id.toolbar_name);
        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        name.setText("Thêm địa chỉ nhận hàng");



    }
    private void Anhxa (){
        deliveryAddress = findViewById(R.id.deliveryAddress);

    }
    private void event (){
        deliveryAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), AddressActivity.class);
                startActivity(intent);
            }
        });


    }
}