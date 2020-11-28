package com.sefvi.seamarket.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sefvi.seamarket.Api.Auth.Register.RegisterApiLml;
import com.sefvi.seamarket.Interface.AuthInterface;
import com.sefvi.seamarket.Model.AccountModell;
import com.sefvi.seamarket.R;
import com.sefvi.seamarket.Utils.Checks;

public class SignUp_Activity extends AppCompatActivity {
    TextView tvdangnhap;
    Button btnDangKi;
    EditText signupPhone, signupPass, signupRePass, signupHoTen, signupNamSinh, address;
    RadioGroup radioGender;
    String gender = "Nam";

    RegisterApiLml registerApiLml;

    private void anhXa(){
    signupPhone = findViewById(R.id.signup_edt_phone);
    signupPass = findViewById(R.id.signup_edt_password);
    signupRePass = findViewById(R.id.signup_edt_repassword);
    signupHoTen = findViewById(R.id.signup_edt_fullname);
    signupNamSinh = findViewById(R.id.signup_edt_year_of_birth);
    address = findViewById(R.id.signup_edt_address);
    radioGender = (RadioGroup) findViewById(R.id.signup_radio_gr);
    btnDangKi = findViewById(R.id.signup_btn_dangki);
    tvdangnhap = findViewById(R.id.signup_tv_dangnhap);

    registerApiLml = new RegisterApiLml();


}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);
        anhXa();

        radioGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checkId = group.getCheckedRadioButtonId();
                if (checkId == R.id.signup_rdo_btn_nam){
                    gender = "Nam";
                }else if (checkId == R.id.signup_rdo_btn_nu){
                    gender = "Nữ";
                }
                Log.d("checkss", gender);
            }
        });

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
                lavigate(signupPhone.getText().toString(), signupPass.getText().toString(), signupRePass.getText().toString(), signupHoTen.getText().toString(), signupNamSinh.getText().toString(), gender, address.getText().toString() );
            }
        });
    }
    private void lavigate(String phone, String pass, String rePass, String hoTen, String yearOfBirth,String gender , String address){
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
        }else  if (address.isEmpty()){
            Toast.makeText(this, "Địa chỉ không được để trống",Toast.LENGTH_SHORT).show();
        }else {
            String avt = "https://cdnsefvi.sefvi.com//upload/photos/2020/09/3dX8BexbC9ZaxIymRLHJ_21_1866a284688c319524958c2e11951243_avatar.png?cache=1600684891";
            registerApiLml.RegisterApi(phone, pass, rePass, hoTen, yearOfBirth, gender, avt, address, new AuthInterface() {
                @Override
                public void getDataSuccess(AccountModell accountModell) {
                    SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(SignUp_Activity.this);
                    SharedPreferences.Editor myEditor = myPreferences.edit();
                    myEditor.putString("TOKEN", accountModell.getToken());
                    myEditor.commit();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void getDataError(String err) {
                    Toast.makeText(getApplicationContext(), err, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}