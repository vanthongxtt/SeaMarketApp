package com.sefvi.seamarket.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sefvi.seamarket.Api.Auth.Login.LoginApiLml;
import com.sefvi.seamarket.Interface.AuthInterface;
import com.sefvi.seamarket.Model.AccountModell;
import com.sefvi.seamarket.R;
import com.sefvi.seamarket.Utils.Checks;
import com.sefvi.seamarket.Utils.ShPref;


public class Login_Activity extends AppCompatActivity {
    Button login_btn_login;
    TextView forgotpassword,signup;
    EditText phone,password;
    LoginApiLml loginApiLml;
     ProgressDialog progressDialog;
    Activity activity;

    private void Anhxa(){
        login_btn_login = findViewById(R.id.Login_btn_login);
        signup = findViewById(R.id.login_tv_dangki);
        phone =findViewById(R.id.login_edt_phone);
        password = findViewById(R.id.login_edt_password);
        forgotpassword = findViewById(R.id.Login_tv_forgot_password);

        loginApiLml = new LoginApiLml();

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
        activity = this;

        forgotpassword.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         Toast.makeText(Login_Activity.this, "Chức năng đang bảo trì!", Toast.LENGTH_SHORT).show();
     }
 });

    }
    private  void validate(String phone, String password){
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Đang đăng nhập...");
        progressDialog.setCanceledOnTouchOutside(false); //không cho nhấn ngoài
        progressDialog.show();
        if (!Checks.PhoneCheck(phone)){
            Toast.makeText(this, "Bạn chưa nhập SDT hoặc nhập sai định dạng!", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        } else if (!Checks.CheckPassword(password)) {
            Toast.makeText(this, "Password phải trên 6 kí tự!", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }else{
            loginApiLml.LoginApi(phone, password, new AuthInterface() {
                @Override
                public void getDataSuccess(AccountModell accountModell) {
                    Log.d("login", accountModell.getUuid() + " - " + accountModell.getToken());
//                    SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(Login_Activity.this);
//                    SharedPreferences.Editor myEditor = myPreferences.edit();
//                    myEditor.putString("TOKEN", accountModell.getToken());
//                    myEditor.commit();

                    SharedPreferences.Editor editor = getSharedPreferences("Sea",MODE_PRIVATE).edit();
                    editor.putString("TOKEN", accountModell.getToken());
                    editor.apply();


                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void getDataError(String e) {
                   Toast.makeText(getApplicationContext(),e, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
        }



    }
}