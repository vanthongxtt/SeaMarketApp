package com.sefvi.seamarket.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sefvi.seamarket.R;
import com.sefvi.seamarket.View.Activity.Bottom_navigation.Home_Activity;

public class Login_Activity extends AppCompatActivity {
    Button Login_btn_login;
    TextView phone,password,tvdangki;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Login_btn_login = findViewById(R.id.Login_btn_login);
        tvdangki = findViewById(R.id.login_tv_dangki);

        Login_btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Activity.this, Home_Activity.class);
                startActivity(intent);
            }
        });
        tvdangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Activity.this,SignUp_Activity.class);
                startActivity(intent);
            }
        });
        //ahihi

    }
}