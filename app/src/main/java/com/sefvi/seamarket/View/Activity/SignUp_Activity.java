package com.sefvi.seamarket.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sefvi.seamarket.R;

public class SignUp_Activity extends AppCompatActivity {
TextView tvdangnhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);

        tvdangnhap = findViewById(R.id.signup_tv_dangnhap);

        tvdangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp_Activity.this,Login_Activity.class);
                startActivity(intent);
            }
        });
    }
}