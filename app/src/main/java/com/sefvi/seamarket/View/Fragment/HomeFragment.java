package com.sefvi.seamarket.View.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.sefvi.seamarket.Adapter.HomeSuggestionAdapter;
import com.sefvi.seamarket.Adapter.Home_ComboHot_Adapter;
import com.sefvi.seamarket.Adapter.Home_Sale_off_Adapter;
import com.sefvi.seamarket.Adapter.SliderAdapter;
import com.sefvi.seamarket.Api.GetProductHome.GetProductHomeApiLml;
import com.sefvi.seamarket.Api.GetProductRandom.GetProductRandomLml;
import com.sefvi.seamarket.Interface.ProductRandom;
import com.sefvi.seamarket.Model.ProductModel;
import com.sefvi.seamarket.R;
import com.sefvi.seamarket.View.Activity.Home.BuygroupActivity;
import com.sefvi.seamarket.View.Activity.Home.DiscountActivity;
import com.sefvi.seamarket.View.Activity.Home.HotActivity;
import com.sefvi.seamarket.View.Activity.Home.ImportActivity;
import com.sefvi.seamarket.View.Activity.PurchaseAreaActivity;
import com.sefvi.seamarket.View.Activity.Home.SellALotActivity;
import com.sefvi.seamarket.View.Activity.Home.SearchActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {
    RecyclerView combohot,saleoff,suggestion;
    LinearLayout homelnlhot,homelnldiscount,homelnlimport,homelnlsellalot,homelnlbuygroup,home_lnl_search,hometextcombohot;
    RelativeLayout home_rl_gps;
    List<ProductModel> comboHotList;
    List<ProductModel> saleofflist;
    List<ProductModel> homeListProducts;
    HomeSuggestionAdapter adapter;

    ViewPager viewPager;
    //add images from drawable to array
    int[] images = {R.drawable.home_img0,R.drawable.home_img1, R.drawable.home_img2, R.drawable.home_img3, R.drawable.home_img4};
    int currentPageCunter = 0;

    String token;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home,container,false);
        SharedPreferences prefs = getContext().getSharedPreferences("Sea",MODE_PRIVATE);
        token = prefs.getString("TOKEN", "");

        Anhxa(v);
        event();
        baner(v);
        setCombohot();
        setSaleoff();
        suggestion();

        return v;
    }
    private void suggestion(){

        homeListProducts = new ArrayList<>();

        GetProductHomeApiLml getProductHomeApiLml = new GetProductHomeApiLml();
        getProductHomeApiLml.GetProductHomeApi(token, 26, new ProductRandom() {
            @Override
            public void getDataSuccess(ProductModel productModel) {

            }

            @Override
            public void getDataError(String err) {

            }

            @Override
            public void getDataSuccess(JSONArray list) {
                for (int i = 0; i <= list.length(); i++){
                    try {
                        JSONObject jsonObject = new JSONObject(list.get(i).toString());
                        Log.d("ahihihi-", jsonObject.getString("name"));
                        ProductModel productModel = new ProductModel();
                        productModel.setId(jsonObject.getInt("id"));
                        productModel.setIdType(jsonObject.getInt("idType"));
                        productModel.setName(jsonObject.getString("name"));
                        productModel.setDescription(jsonObject.getString("description"));
                        productModel.setPrice(jsonObject.getInt("price"));
                        productModel.setUnit(jsonObject.getString("unit"));
                        productModel.setImage(jsonObject.getString("image"));
                        homeListProducts.add(productModel);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter = (HomeSuggestionAdapter) new HomeSuggestionAdapter(homeListProducts,getActivity());
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2,GridLayoutManager.VERTICAL,false);
                suggestion.setLayoutManager(gridLayoutManager);
                suggestion.setAdapter(adapter);
            }
        });
    }

    private void Anhxa (View v){
        combohot = v.findViewById(R.id.home_rv_combohot);
        saleoff = v.findViewById(R.id.home_rv_sale_off);
        suggestion= v.findViewById(R.id.home_rv_suggestion);
        homelnlhot = v.findViewById(R.id.home_lnl_hot);
        hometextcombohot = v.findViewById(R.id.hometextcombohot);
        homelnldiscount = v.findViewById(R.id.home_lnl_discount);
        homelnlimport = v.findViewById(R.id.home_lnl_import);
        homelnlsellalot = v.findViewById(R.id.home_lnl_sellalot);
        homelnlbuygroup = v.findViewById(R.id.home_lnl_buygroup);
        home_rl_gps = v.findViewById(R.id.home_rl_gps);
        home_lnl_search = v.findViewById(R.id.home_lnl_search);

    }
    private void event (){
        homelnlhot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HotActivity.class);
                startActivity(intent);
            }
        });
        homelnlimport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ImportActivity.class);
                startActivity(intent);
            }
        });
        homelnlsellalot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SellALotActivity.class);
                startActivity(intent);
            }
        });
        home_rl_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PurchaseAreaActivity.class);
                startActivity(intent);
            }
        });
        home_lnl_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
        homelnlbuygroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BuygroupActivity.class);
                startActivity(intent);
            }
        });
        homelnldiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DiscountActivity.class);
                startActivity(intent);
            }
        });
        combohot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HotActivity.class);
                startActivity(intent);
            }
        });
        hometextcombohot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HotActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setCombohot(){

        GetProductRandomLml getProductRandomLml = new GetProductRandomLml();
        getProductRandomLml.GetProductRandomApi(token, new ProductRandom() {
            @Override
            public void getDataSuccess(ProductModel productModel) {

            }

            @Override
            public void getDataError(String err) {

            }

            @Override
            public void getDataSuccess(JSONArray list) {
                comboHotList = new ArrayList<>();
                for (int i = 0; i <= list.length(); i++){
                    try {
                        JSONObject jsonObject = new JSONObject(list.get(i).toString());
                        Log.d("ahihihi-c", jsonObject.getString("name"));
                        ProductModel productModel = new ProductModel();
                        productModel.setId(jsonObject.getInt("id"));
                        productModel.setIdType(jsonObject.getInt("idType"));
                        productModel.setName(jsonObject.getString("name"));
                        productModel.setDescription(jsonObject.getString("description"));
                        productModel.setPrice(jsonObject.getInt("price"));
                        productModel.setUnit(jsonObject.getString("unit"));
                        productModel.setImage(jsonObject.getString("image"));
                        comboHotList.add(productModel);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                combohot.setLayoutManager(manager);

                Home_ComboHot_Adapter adapter = new Home_ComboHot_Adapter(getActivity(),comboHotList);
                combohot.setAdapter(adapter);

            }
        });
    }

    private void setSaleoff(){
        saleofflist = new ArrayList<>();
        GetProductRandomLml getProductRandomLml = new GetProductRandomLml();
        getProductRandomLml.GetProductRandomApi(token, new ProductRandom() {
            @Override
            public void getDataSuccess(ProductModel productModel) {

            }

            @Override
            public void getDataError(String err) {

            }

            @Override
            public void getDataSuccess(JSONArray list) {
                for (int i = 0; i <= list.length(); i++) {
                    try {
                        JSONObject jsonObject = new JSONObject(list.get(i).toString());
                        Log.d("ahihihi-", jsonObject.getString("name"));
                        ProductModel productModel = new ProductModel();
                        productModel.setId(jsonObject.getInt("id"));
                        productModel.setIdType(jsonObject.getInt("idType"));
                        productModel.setName(jsonObject.getString("name"));
                        productModel.setDescription(jsonObject.getString("description"));
                        productModel.setPrice(jsonObject.getInt("price"));
                        productModel.setUnit(jsonObject.getString("unit"));
                        productModel.setImage(jsonObject.getString("image"));
                        saleofflist.add(productModel);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                LinearLayoutManager manager2 = new LinearLayoutManager(getContext());
                manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
                saleoff.setLayoutManager(manager2);

                Home_Sale_off_Adapter adapter2 = new Home_Sale_off_Adapter(getContext(), saleofflist);
                saleoff.setAdapter(adapter2);
            }
        });

    }

    private void baner(View v){

        viewPager = v.findViewById(R.id.viewpager);
        //add adapter
        viewPager.setAdapter(new SliderAdapter(images, getActivity()));


        final Handler handler = new Handler();
        final Runnable update  = new Runnable() {
            @Override
            public void run() {
                if (currentPageCunter == images.length){
                    currentPageCunter = 0 ;
                }

                viewPager.setCurrentItem(currentPageCunter++,true);
            }
        };

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },2500,2500);

    }

}