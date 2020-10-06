package com.sefvi.seamarket.View.Activity.Bottom_navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sefvi.seamarket.R;

public class Basket_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);
        // intiialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.action_basket);

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

                    case  R.id.action_usergroup:
                        startActivity(new Intent(getApplicationContext(), User_group_Activity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case  R.id.action_basket:

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