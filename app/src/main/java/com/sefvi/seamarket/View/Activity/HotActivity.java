package com.sefvi.seamarket.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sefvi.seamarket.R;

public class HotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot);
        ImageView backicon = findViewById(R.id.toolbar_back);
        TextView name = findViewById(R.id.toolbar_name);
        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        name.setText("Sản phẩm hot");
    }
}