package com.sefvi.seamarket.Adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.sefvi.seamarket.Model.ProductImageModel;
import com.sefvi.seamarket.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SliderProductAdapter extends PagerAdapter {

    List<ProductImageModel> images;
    LayoutInflater layoutInflater;
    Context context;


    public SliderProductAdapter(List<ProductImageModel> images, Context context) {
        this.images = images;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View myImageLayout = layoutInflater.inflate(R.layout.slide_images,container, false);
        ProductImageModel productImageModel = images.get(position);
        ImageView imageview = myImageLayout.findViewById(R.id.imageview);
        String url = "https://api.sefvi.com/SeaMarketApi/V1/uploads/" + productImageModel.getNameImage();
        Picasso.get()
                .load(url)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.drawable.home_combo_hot_img_cua)
                .into(imageview);


        container.addView(myImageLayout);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
