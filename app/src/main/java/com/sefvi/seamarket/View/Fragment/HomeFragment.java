package com.sefvi.seamarket.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
import com.sefvi.seamarket.Model.Home_ComboHot;
import com.sefvi.seamarket.Model.Home_SaleOff;
import com.sefvi.seamarket.R;
import com.sefvi.seamarket.View.Activity.HotActivity;
import com.sefvi.seamarket.View.Activity.ImportActivity;
import com.sefvi.seamarket.View.Activity.PurchaseAreaActivity;
import com.sefvi.seamarket.View.Activity.SellALotActivity;
import com.sefvi.seamarket.View.Activity.VersionActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {
    RecyclerView combohot,saleoff,suggestion;
    LinearLayout homelnlhot,homelnldiscount,homelnlimport,homelnlsellalot,homelnlbuygroup;
    RelativeLayout home_rl_gps;
    List<Home_ComboHot> comboHotList;
    List<Home_SaleOff> saleofflist;
    List<String> name;
    List<Integer> price;
    List<Integer> img;
    HomeSuggestionAdapter adapter;

    ViewPager viewPager;
    //add images from drawable to array
    int images[] = {R.drawable.home_img0,R.drawable.home_img1, R.drawable.home_img2, R.drawable.home_img3, R.drawable.home_img4};
    int currentPageCunter = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home,container,false);
        Anhxa(v);
        event();
        baner(v);
        setCombohot();
        setSaleoff();
        suggestion();


//        BottomNavigationView navigationView = v.findViewById(R.id.bottom_nav);
        // intiialize and assign variable
//        BottomNavigationView bottomNavigationView = v.findViewById(R.id.bottom_nav);
        //set home selected
//        bottomNavigationView.setSelectedItemId(R.id.action_home);

        return v;
    }
    private void suggestion(){


        name = new ArrayList<>();
        price = new ArrayList<>();
        img = new ArrayList<>();

        name.add("ca");
        name.add("ca");
        name.add("ca");
        name.add("ca");
        name.add("ca");
        name.add("ca");
        name.add("ca");
        name.add("ca");
        name.add("ca");
        name.add("ca");

        price.add(99999);
        price.add(99999);
        price.add(99999);
        price.add(99999);
        price.add(99999);
        price.add(99999);
        price.add(99999);
        price.add(99999);
        price.add(99999);
        price.add(99999);


        img.add(R.drawable.home_img_combo_hot_ca);
        img.add(R.drawable.home_img_combo_hot_ca);
        img.add(R.drawable.home_img_combo_hot_ca);
        img.add(R.drawable.home_img_combo_hot_ca);
        img.add(R.drawable.home_img_combo_hot_ca);
        img.add(R.drawable.home_img_combo_hot_ca);
        img.add(R.drawable.home_img_combo_hot_ca);
        img.add(R.drawable.home_img_combo_hot_ca);
        img.add(R.drawable.home_img_combo_hot_ca);
        img.add(R.drawable.home_img_combo_hot_ca);


        adapter = (HomeSuggestionAdapter) new HomeSuggestionAdapter(name,price,img,getActivity());


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2,GridLayoutManager.VERTICAL,false);
        suggestion.setLayoutManager(gridLayoutManager);
        suggestion.setAdapter(adapter);






    }

    private void Anhxa (View v){
        combohot = v.findViewById(R.id.home_rv_combohot);
        saleoff = v.findViewById(R.id.home_rv_sale_off);
        suggestion= v.findViewById(R.id.home_rv_suggestion);
        homelnlhot = v.findViewById(R.id.home_lnl_hot);
        homelnldiscount = v.findViewById(R.id.home_lnl_discount);
        homelnlimport = v.findViewById(R.id.home_lnl_import);
        homelnlsellalot = v.findViewById(R.id.home_lnl_sellalot);
        homelnlbuygroup = v.findViewById(R.id.home_lnl_buygroup);
        home_rl_gps = v.findViewById(R.id.home_rl_gps);

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

    }

    private void setCombohot(){
        comboHotList = new ArrayList<>();

        comboHotList.add(new Home_ComboHot("cua Cà Mau",120000,R.drawable.home_combo_hot_img_cua));
        comboHotList.add(new Home_ComboHot("Mực nè",90000,R.drawable.home_img_combo_hot_muc));
        comboHotList.add(new Home_ComboHot("Cá",70000,R.drawable.home_img_combo_hot_ca));
        comboHotList.add(new Home_ComboHot("Tôm Bình Điền",150000,R.drawable.home_img_combo_hot_tom));
        comboHotList.add(new Home_ComboHot("Óc vòi voi",150000,R.drawable.home_img_combo_oc));
        comboHotList.add(new Home_ComboHot("cua Cà Mau",120000,R.drawable.home_combo_hot_img_cua));
        comboHotList.add(new Home_ComboHot("Mực nè",90000,R.drawable.home_img_combo_hot_muc));
        comboHotList.add(new Home_ComboHot("Cá",70000,R.drawable.home_img_combo_hot_ca));
        comboHotList.add(new Home_ComboHot("Tôm Bình Điền",150000,R.drawable.home_img_combo_hot_tom));
        comboHotList.add(new Home_ComboHot("Óc vòi voi",150000,R.drawable.home_img_combo_oc));
        


        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        combohot.setLayoutManager(manager);

        Home_ComboHot_Adapter adapter = new Home_ComboHot_Adapter(getActivity(),comboHotList);
        combohot.setAdapter(adapter);


    }

    private void setSaleoff(){
        saleofflist = new ArrayList<>();
        saleofflist.add(new Home_SaleOff("cua ",120000,R.drawable.home_combo_hot_img_cua));
        saleofflist.add(new Home_SaleOff("Mực ",90000,R.drawable.home_img_combo_hot_muc));
        saleofflist.add(new Home_SaleOff("Cá",70000,R.drawable.home_img_combo_hot_ca));
        saleofflist.add(new Home_SaleOff("Tôm ",150000,R.drawable.home_img_combo_hot_tom));
        saleofflist.add(new Home_SaleOff("Óc ",150000,R.drawable.home_img_combo_oc));
        saleofflist.add(new Home_SaleOff("cua ",120000,R.drawable.home_combo_hot_img_cua));
        saleofflist.add(new Home_SaleOff("Mực ",90000,R.drawable.home_img_combo_hot_muc));
        saleofflist.add(new Home_SaleOff("Cá",70000,R.drawable.home_img_combo_hot_ca));
        saleofflist.add(new Home_SaleOff("Tôm ",150000,R.drawable.home_img_combo_hot_tom));
        saleofflist.add(new Home_SaleOff("Óc ",150000,R.drawable.home_img_combo_oc));


        LinearLayoutManager manager2 = new LinearLayoutManager(getActivity());
        manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        saleoff.setLayoutManager(manager2);

        Home_Sale_off_Adapter adapter2 = new Home_Sale_off_Adapter(getActivity(), saleofflist) {
        };
        saleoff.setAdapter(adapter2);
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