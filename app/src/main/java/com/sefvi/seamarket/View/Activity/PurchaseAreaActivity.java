package com.sefvi.seamarket.View.Activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.sefvi.seamarket.Adapter.PurchaseAreaAdapter;
import com.sefvi.seamarket.Model.PurchaseAreaModel;
import com.sefvi.seamarket.R;

import java.util.ArrayList;


public class PurchaseAreaActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_area);
        initView();
        ImageView backicon = findViewById(R.id.toolbar_back);
        TextView name = findViewById(R.id.toolbar_name);
        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        name.setText("Khu vực mua hàng");

    }
    public  void initView (){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.purchasearea_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<PurchaseAreaModel> areaModelArrayList = new ArrayList<>();

        areaModelArrayList.add(new PurchaseAreaModel("Hồ Chí Minh"));
        areaModelArrayList.add(new PurchaseAreaModel("Hà Nội"));
        PurchaseAreaAdapter purchaseAreaAdapter = new PurchaseAreaAdapter(areaModelArrayList,getApplicationContext());
        recyclerView.setAdapter(purchaseAreaAdapter);
    }
}