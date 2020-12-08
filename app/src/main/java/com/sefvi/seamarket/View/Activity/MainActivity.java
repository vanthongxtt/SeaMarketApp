package com.sefvi.seamarket.View.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sefvi.seamarket.View.Fragment.OderFragment;
import com.sefvi.seamarket.View.Fragment.HomeFragment;
import com.sefvi.seamarket.View.Fragment.NotificationFragment;
import com.sefvi.seamarket.View.Fragment.PersonalFragmet;
import com.sefvi.seamarket.View.Fragment.ProductFragment;
import com.sefvi.seamarket.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.main_bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        loadFragment(new HomeFragment());
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selcetedFragmet = null;

                    switch (item.getItemId()){
                        case R.id.action_home:
                            selcetedFragmet = new HomeFragment();
                            break;
                        case R.id.action_product:
                            selcetedFragmet = new ProductFragment();
                            break;
                        case R.id.action_notification:
                            selcetedFragmet = new NotificationFragment();
                            break;
                        case R.id.action_basket:
                            selcetedFragmet = new OderFragment();
                            break;
                        case R.id.action_personal:
                            selcetedFragmet = new PersonalFragmet();

                    }

                    return loadFragment(selcetedFragmet);
                }
            };
    private   boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }
}