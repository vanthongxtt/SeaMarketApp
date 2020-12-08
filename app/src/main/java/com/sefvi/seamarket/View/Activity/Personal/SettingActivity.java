package com.sefvi.seamarket.View.Activity.Personal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sefvi.seamarket.R;
import com.sefvi.seamarket.View.Activity.ChangeInforActivity;


public class SettingActivity extends AppCompatActivity {
    RelativeLayout language,changeinformation;
    LinearLayout settingLogOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initEvents();
        initControls();

        ImageView backicon = findViewById(R.id.toolbar_back);
        TextView name = findViewById(R.id.toolbar_name);
        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        name.setText(getText(R.string.personal_text_caidat));
    }
    private void  initEvents() {
        language = findViewById(R.id.setting_language);
        changeinformation=findViewById(R.id.setting_change_information);
        settingLogOut = findViewById(R.id.settingLogOut);




    }



    private void initControls(){
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), LanguageActivity.class);
                startActivity(intent);
            }
        });
        changeinformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), ChangeInforActivity.class);
                startActivity(intent);
            }
        });
        settingLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

}