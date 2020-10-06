package com.sefvi.seamarket.View.Activity.Bottom_navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sefvi.seamarket.R;

public class Notification_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);
        // intiialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.action_notification);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case  R.id.action_home:
                        startActivity(new Intent(getApplicationContext(), Home_Activity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case  R.id.action_product:
                        startActivity(new Intent(getApplicationContext(), Product_Activity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case  R.id.action_notification:

                        return true;

                    case  R.id.action_basket:
                        startActivity(new Intent(getApplicationContext(), Basket_Activity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case  R.id.action_personal:
                        startActivity(new Intent(getApplicationContext(), Personal_Activity.class));
                        overridePendingTransition(0,0);
                        return true;


                }
                return false;
            }
        });
    }
}