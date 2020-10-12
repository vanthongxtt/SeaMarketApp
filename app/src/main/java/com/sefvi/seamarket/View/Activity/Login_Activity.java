package com.sefvi.seamarket.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sefvi.seamarket.R;
import com.sefvi.seamarket.Utils.Checks;



public class Login_Activity extends AppCompatActivity {
    Button login_btn_login;
    TextView forgotpassword,signup;
    EditText phone,password;

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
//                Intent intent = new Intent(Login_Activity.this,MainActivity.class);
//                 startActivity(intent);

                validate(phone.getText().toString(), password.getText().toString());
            }
        });



    }
    private  void validate(String phone, String password){
        if (!Checks.PhoneCheck(phone)){
            Toast.makeText(this, "Bạn chưa nhập SDT hoặc nhập sai định dạng!", Toast.LENGTH_SHORT).show();
        } else if (!Checks.CheckPassword(password)) {

            Toast.makeText(this, "Password phải trên 6 kí tự!", Toast.LENGTH_SHORT).show();
        }else{
            // get API
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }



    }
}