package com.sefvi.seamarket.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sefvi.seamarket.Api.Auth.Register.RegisterApiLml;
import com.sefvi.seamarket.Interface.AuthInterface;
import com.sefvi.seamarket.Model.AccountModell;
import com.sefvi.seamarket.R;
import com.sefvi.seamarket.Utils.Checks;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SignUp_Activity extends AppCompatActivity {
TextView tvdangnhap;
Button btnDangKi;
EditText signupPhone, signupPass, signupRePass, signupHoTen, signupcalender, singupAddress;
ProgressDialog progressDialog;
RadioGroup signup_radio_gr;
RegisterApiLml registerApiLml;
String gender = "Nam";
    private void anhXa(){
    signupPhone = findViewById(R.id.signup_edt_phone);
    signupPass = findViewById(R.id.signup_edt_password);
    signupRePass = findViewById(R.id.signup_edt_repassword);
    signupHoTen = findViewById(R.id.signup_edt_fullname);
    signupcalender = findViewById(R.id.signup_edt_calender);
    btnDangKi = findViewById(R.id.signup_btn_dangki);
    signup_radio_gr = findViewById(R.id.signup_radio_gr);
    singupAddress = findViewById(R.id.signup_edt_address);
    registerApiLml = new RegisterApiLml();
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
                lavigate(signupPhone.getText().toString(), signupPass.getText().toString(), signupRePass.getText().toString(), signupHoTen.getText().toString(), signupcalender.getText().toString(), singupAddress.getText().toString());
            }
        });

        signupcalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            chonngay();
            }
        });

        signup_radio_gr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                gender = String.valueOf(radioButton.getText());
            }
        });
    }
    private  void chonngay(){
        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(calendar.DATE);
        int thang = calendar.get(calendar.MONTH);
        int nam = calendar.get(calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
                signupcalender.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }
    private void lavigate(String phone, String password, String rePassword, String fullName, String dateOfBirth, String address){
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Đang đăng nhập...");
        progressDialog.setCanceledOnTouchOutside(false); //không cho nhấn ngoài
        progressDialog.show();
        if(!Checks.PhoneCheck(phone)){
            Toast.makeText(this, "PhoneNumber Không được để rỗng và phải đúng định dạng!", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }else if(!Checks.CheckPassword(password)){
            Toast.makeText(this, "Password phải trên 6 kí tự!", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }else if (!rePassword.matches(password) || rePassword.isEmpty() ){
            Toast.makeText(this, "RePass Không được để trống và phải trùng với password", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }else if(fullName.isEmpty()){
            Toast.makeText(this, "Họ tên không được để trống!", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }else if(dateOfBirth.isEmpty()){
            Toast.makeText(this, "Năm sinh Không được để trống!", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }else if(address.isEmpty()){
            Toast.makeText(this, "Địa chỉ không được để trống!", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }else{
                registerApiLml.RegisterApi(phone, password, rePassword,fullName,dateOfBirth,gender,"https://cdnsefvi.sefvi.com//upload/photos/d-avatar.jpg" ,address,new AuthInterface() {
                    @Override
                    public void getDataSuccess(AccountModell accountModell) {
                        SharedPreferences.Editor editor = getSharedPreferences("Sea",MODE_PRIVATE).edit();
                        editor.putString("TOKEN", accountModell.getToken());
                        editor.apply();


                        Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void getDataError(String err) {
                        Toast.makeText(getApplicationContext(),err, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
//            Toast.makeText(this, "Đăng kí thành công!", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
//            startActivity(intent);
        }
    }
}