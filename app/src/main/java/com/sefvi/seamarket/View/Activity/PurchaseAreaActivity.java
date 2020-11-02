package com.sefvi.seamarket.View.Activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.sefvi.seamarket.Adapter.ProducFishAdapter;
import com.sefvi.seamarket.Adapter.PurchaseAreaAdapter;
import com.sefvi.seamarket.Model.ProducFishModel;
import com.sefvi.seamarket.Model.PurchaseAreaModel;
import com.sefvi.seamarket.R;

import java.util.ArrayList;
import java.util.List;


public class PurchaseAreaActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_area);
        initView();

    }
    public  void initView (){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.purchasearea_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<PurchaseAreaModel> areaModelArrayList = new ArrayList<>();

        areaModelArrayList.add(new PurchaseAreaModel("Ho chi minh"));
        areaModelArrayList.add(new PurchaseAreaModel("Ho noi"));
        PurchaseAreaAdapter purchaseAreaAdapter = new PurchaseAreaAdapter(areaModelArrayList,getApplicationContext());
        recyclerView.setAdapter(purchaseAreaAdapter);
    }
}