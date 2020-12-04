package com.sefvi.seamarket.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sefvi.seamarket.Adapter.SliderAdapter;
import com.sefvi.seamarket.R;

import java.util.Timer;
import java.util.TimerTask;

public class DetailProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        ImageView backicon = findViewById(R.id.detail_product_back);
        TextView title = findViewById(R.id.detail_product_title_product);
        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText(getText(R.string.personal_text_caidat));


        ViewPager viewPager;
        //add images from drawable to array
        int[] images = {R.drawable.home_img0,R.drawable.home_img1, R.drawable.home_img2, R.drawable.home_img3, R.drawable.home_img4};
        final int[] currentPageCunter = {0};

        viewPager = findViewById(R.id.detail_product_viewpager);
        //add adapter
        viewPager.setAdapter(new SliderAdapter(images, getApplicationContext()));


        final Handler handler = new Handler();
        final Runnable update  = new Runnable() {
            @Override
            public void run() {
                if (currentPageCunter[0] == images.length){
                    currentPageCunter[0] = 0 ;
                }

                viewPager.setCurrentItem(currentPageCunter[0]++,true);
            }
        };

    }


}