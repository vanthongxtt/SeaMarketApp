package com.sefvi.seamarket.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.sefvi.seamarket.Api.GetUserData.GetUserDataApiLml;
import com.sefvi.seamarket.Api.UpdateUser.UpdateUserApilml;
import com.sefvi.seamarket.Interface.AuthInterface;
import com.sefvi.seamarket.Model.AccountModell;
import com.sefvi.seamarket.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ChangeInforActivity extends AppCompatActivity {
    TextInputEditText changInforAddress,changInforPhone,changInforName;
    EditText changInforCalender;
    RadioGroup signup_radio_gr;
    Button btnUpdate;
    private CircleImageView ProfileImage;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    String token;
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_infor);
        ImageView backicon = findViewById(R.id.toolbar_back);
        TextView name = findViewById(R.id.toolbar_name);
        initEvents();
        initControls();
        GetUserData();
        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        name.setText("Thay đổi thông tin");
        ProfileImage = findViewById(R.id.changInforCardviewAvt);
        ProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Sellect Picture"), PICK_IMAGE);
            }
        });


    }
public void initEvents(){
        changInforAddress = findViewById(R.id.changInforAddress);
        changInforCalender = findViewById(R.id.changInforCalender);
        signup_radio_gr = findViewById(R.id.signup_radio_gr);
        changInforPhone = findViewById(R.id.changInforPhone);
        changInforName = findViewById(R.id.changInforName);
        btnUpdate = findViewById(R.id.btnChangeInforUpdate);

        SharedPreferences prefs = getSharedPreferences("Sea",MODE_PRIVATE);
        token = prefs.getString("TOKEN", "");

}
public void initControls(){
    changInforCalender.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            chonngay();
        }
    });
    signup_radio_gr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.changInforRadioNam){
                gender = "Nam";
            }else if (checkedId == R.id.changInforRadioNu){
                gender = "Nữ";
            }
        }
    });

    btnUpdate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            updateUser();
        }
    });

}
    private  void chonngay(){
        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/mm/dd");
               changInforCalender.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                ProfileImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void GetUserData(){
        GetUserDataApiLml getUserDataApiLml = new GetUserDataApiLml();
        getUserDataApiLml.GetUserDataApi(token, new AuthInterface() {
            @Override
            public void getDataSuccess(AccountModell accountModell) {
                changInforName.setText(accountModell.getFullname());
                changInforPhone.setText(accountModell.getPhone());
                changInforCalender.setText(accountModell.getDateOfBirth());
                changInforAddress.setText(accountModell.getAddress());
                Picasso.get()
                        .load(accountModell.getAvatar())
                        .placeholder(R.mipmap.ic_launcher_round)
                        .error(R.drawable.home_combo_hot_img_cua)
                        .into(ProfileImage);

                if (accountModell.getGender().equals("Nam")){
                    signup_radio_gr.check(R.id.changInforRadioNam);
                }else {
                    signup_radio_gr.check(R.id.changInforRadioNu);
                }
            }
            @Override
            public void getDataError(String err) {

            }
        });
    }

    private void updateUser(){
        if (changInforAddress.getText().length() == 0 || changInforPhone.getText().length() == 0 || changInforCalender.getText().length() == 0 || changInforName.getText().length() == 0){
            Toast.makeText(this, "Không được bỏ trống!", Toast.LENGTH_SHORT).show();
        }else {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            try {
                File file = new File(getRealPathFromURI(imageUri));
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),file);
                builder.addFormDataPart("avatar", file.getName(), requestBody);
            }catch (Exception e){

            }
            builder.addFormDataPart("phone", changInforPhone.getText().toString());
            builder.addFormDataPart("fullname", changInforName.getText().toString());
            builder.addFormDataPart("gender", gender);
            builder.addFormDataPart("dateOfBirth", changInforCalender.getText().toString());
            builder.addFormDataPart("address", changInforAddress.getText().toString());
            MultipartBody body = builder.build();
            UpdateUserApilml updateUserApilml = new UpdateUserApilml();
            updateUserApilml.UpdateUserApi(token, body, new AuthInterface() {
                @Override
                public void getDataSuccess(AccountModell accountModell) {

                }

                @Override
                public void getDataError(String err) {
                    if (err.equals("OK")){
                        Toast.makeText(ChangeInforActivity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
}