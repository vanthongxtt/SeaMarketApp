package com.sefvi.seamarket.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sefvi.seamarket.R;
import com.sefvi.seamarket.Utils.Checks;

public class SignUp_Activity extends AppCompatActivity {
TextView tvdangnhap;
Button btnDangKi;
EditText signupPhone, signupPass, signupRePass, signupHoTen, signupNamSinh;
    private void anhXa(){
    signupPhone = findViewById(R.id.signup_edt_phone);
    signupPass = findViewById(R.id.signup_edt_password);
    signupRePass = findViewById(R.id.signup_edt_repassword);
    signupHoTen = findViewById(R.id.signup_edt_fullname);
    signupNamSinh = findViewById(R.id.signup_edt_year_of_birth);
    btnDangKi = findViewById(R.id.signup_btn_dangki);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);
        anhXa();
        tvdangnhap = findViewById(R.id.signup_tv_dangnhap);

        tvdangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp_Activity.this,Login_Activity.class);
                startActivity(intent);
            }
        });
        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lavigate(signupPhone.getText().toString(), signupPass.getText().toString(), signupRePass.getText().toString(), signupHoTen.getText().toString(), signupNamSinh.getText().toString());
            }
        });
    }
    private void lavigate(String phone, String pass, String rePass, String hoTen, String yearOfBirth){
        if(!Checks.PhoneCheck(phone)){
            Toast.makeText(this, "PhoneNumber Không được để rỗng và phải đúng định dạng!", Toast.LENGTH_SHORT).show();
        }else if(!Checks.CheckPassword(pass)){
            Toast.makeText(this, "Password phải trên 6 kí tự!", Toast.LENGTH_SHORT).show();
        }else if (!rePass.matches(pass) || rePass.isEmpty() ){
            Toast.makeText(this, "RePass Không được để trống và phải trùng với password", Toast.LENGTH_SHORT).show();
        }else if(hoTen.isEmpty()){
            Toast.makeText(this, "Họ tên không được để trống!", Toast.LENGTH_SHORT).show();
        }else if(yearOfBirth.isEmpty()){
            Toast.makeText(this, "Năm sinh Không được để trống", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Đăng kí thành công!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
            startActivity(intent);
        }
    }
}