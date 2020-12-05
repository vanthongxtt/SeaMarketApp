package com.sefvi.seamarket.View.Activity.Personal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sefvi.seamarket.R;
import com.sefvi.seamarket.View.Activity.ChangeInforActivity;
import com.sefvi.seamarket.View.Fragment.HomeFragment;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {
    TextView languageen,languagevi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
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
        name.setText(getText(R.string.setting_text_ngonngu));
    }
    private void  initEvents() {
       languageen = findViewById(R.id.language_en);
       languagevi = findViewById(R.id.language_vi);



    }


    public void changelanguage( String language){
        Locale locale = new Locale(language);
        Configuration config = new Configuration();
        config.locale =locale;
        getBaseContext().getResources().updateConfiguration(
                config,
                getBaseContext().getResources().getDisplayMetrics()
        );
        Intent intent = new Intent(LanguageActivity.this,LanguageActivity.class);
        startActivity(intent);

    }
    private void initControls(){
        languageen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changelanguage("en");


            }
        });

        languagevi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changelanguage("vi");
                 

            }
        });



    }
}