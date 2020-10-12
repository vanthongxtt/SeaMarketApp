package com.sefvi.seamarket.View.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.sefvi.seamarket.Adapter.Home_ComboHot_Adapter;
import com.sefvi.seamarket.Adapter.SliderAdapter;
import com.sefvi.seamarket.Model.Home_ComboHot;
import com.sefvi.seamarket.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {
    RecyclerView combohot,saleoff;
    List<Home_ComboHot> comboHotList,saleofflist;

    ViewPager viewPager;
    //add images from drawable to array
    int images[] = {R.drawable.home_img1, R.drawable.home_img2, R.drawable.home_img3, R.drawable.home_img4};
    int currentPageCunter = 0;

    private void Anhxa (View v){
        combohot = v.findViewById(R.id.home_rv_combohot);
        saleoff = v.findViewById(R.id.home_rv_sale_off);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home,container,false);
        Anhxa(v);


        comboHotList = new ArrayList<>();

        comboHotList.add(new Home_ComboHot("cua Cà Mau",120000,R.drawable.home_combo_hot_img_cua));
        comboHotList.add(new Home_ComboHot("Mực nè",90000,R.drawable.home_img_combo_hot_muc));
        comboHotList.add(new Home_ComboHot("Cá",70000,R.drawable.home_img_combo_hot_ca));
        comboHotList.add(new Home_ComboHot("Tôm Bình Điền",150000,R.drawable.home_img_combo_hot_tom));
        comboHotList.add(new Home_ComboHot("Óc vòi voi",150000,R.drawable.home_img_combo_oc));

        saleofflist = new ArrayList<>();
        saleofflist.add(new Home_ComboHot("cua Cà Mau",120000,R.drawable.home_combo_hot_img_cua));
        saleofflist.add(new Home_ComboHot("Mực nè",90000,R.drawable.home_img_combo_hot_muc));
        saleofflist.add(new Home_ComboHot("Cá",70000,R.drawable.home_img_combo_hot_ca));
        saleofflist.add(new Home_ComboHot("Tôm Bình Điền",150000,R.drawable.home_img_combo_hot_tom));
        saleofflist.add(new Home_ComboHot("Óc vòi voi",150000,R.drawable.home_img_combo_oc));

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        combohot.setLayoutManager(manager);

        Home_ComboHot_Adapter adapter = new Home_ComboHot_Adapter(getActivity(),comboHotList);
        combohot.setAdapter(adapter);

        LinearLayoutManager manager2 = new LinearLayoutManager(getActivity());
        manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        saleoff.setLayoutManager(manager2);

        Home_ComboHot_Adapter adapter2 = new Home_ComboHot_Adapter(getActivity(),comboHotList);
        saleoff.setAdapter(adapter2);

        viewPager = v.findViewById(R.id.viewpager);
        //add adapter
        viewPager.setAdapter(new SliderAdapter(images, getActivity()));
//        BottomNavigationView navigationView = v.findViewById(R.id.bottom_nav);
        // intiialize and assign variable
//        BottomNavigationView bottomNavigationView = v.findViewById(R.id.bottom_nav);
        //set home selected
//        bottomNavigationView.setSelectedItemId(R.id.action_home);



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





        return v;

    }
}