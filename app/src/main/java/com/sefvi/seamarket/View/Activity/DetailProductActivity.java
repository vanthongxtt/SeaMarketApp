package com.sefvi.seamarket.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.sefvi.seamarket.Adapter.SliderAdapter;
import com.sefvi.seamarket.R;

import java.util.Timer;
import java.util.TimerTask;

public class DetailProductActivity extends AppCompatActivity {
    ImageView backicon;
    TextView title;
    Button addproduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        initEvents();

        initControls();


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

    private void  initEvents(){
        backicon = findViewById(R.id.detail_product_back);
        title = findViewById(R.id.detail_product_title_product);
        addproduct = findViewById(R.id.detail_product_btn_add_basket);

    }

    private void initControls(){

        title.setText("Tên sản phẩm");

        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        DetailProductActivity.this,R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(
                                R.layout.bottom_dialog_detail_product,
                                        (LinearLayout)findViewById(R.id.bottomSheetContainer)
                        );
                bottomSheetView.findViewById(R.id.bottomSheet_btn_add_basket).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(DetailProductActivity.this, "chưa thêm vào giỏ hàng được đâu", Toast.LENGTH_SHORT).show();

                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();


            }
        });
    }


}