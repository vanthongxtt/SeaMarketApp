package com.sefvi.seamarket.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.sefvi.seamarket.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChangeInforActivity extends AppCompatActivity {
    TextInputEditText changInforAddress,changInforPhone,changInforName;
    EditText changInforCalender;
    RadioButton changInforRadioNam,changInforRadioNu;
    private CircleImageView ProfileImage;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_infor);
        ImageView backicon = findViewById(R.id.toolbar_back);
        TextView name = findViewById(R.id.toolbar_name);
        initEvents();
        initControls();
        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        name.setText("Thay đổi thông tin");
        ProfileImage = (CircleImageView) findViewById(R.id.changInforCardviewAvt);
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
        changInforRadioNam = findViewById(R.id.changInforRadioNam);
        changInforRadioNu = findViewById(R.id.changInforRadioNu);
        changInforPhone = findViewById(R.id.changInforPhone);
        changInforName = findViewById(R.id.changInforName);


}
public void initControls(){
    changInforCalender.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            chonngay();
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
}