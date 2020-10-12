package com.sefvi.seamarket.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sefvi.seamarket.R;


public class Login_Activity extends AppCompatActivity {
    Button login_btn_login;
    TextView phone,password,forgotpassword,signup;

    private void Anhxa(){
        login_btn_login = findViewById(R.id.Login_btn_login);
        signup = findViewById(R.id.login_tv_dangki);
        phone =findViewById(R.id.login_edt_phone);
        password = findViewById(R.id.login_edt_password);
        forgotpassword = findViewById(R.id.Login_tv_forgot_password);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Anhxa();



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Activity.this,SignUp_Activity.class);
                startActivity(intent);
            }
        });
        login_btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });



    }
    private  void validate(String userName, String userPassword){
        if ((userName == "Admin") && (userPassword == "12345")){
            Intent intent = new Intent(Login_Activity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}