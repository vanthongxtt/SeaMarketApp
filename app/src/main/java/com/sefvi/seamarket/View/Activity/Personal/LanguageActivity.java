package com.sefvi.seamarket.View.Activity.Personal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.sefvi.seamarket.R;
import com.sefvi.seamarket.View.Activity.MainActivity;
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
        startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }
    private void initControls(){
        languageen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LanguageActivity.this);
                builder.setMessage("You want change language to English?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        changelanguage("en");
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
               alertDialog.show();

            }
        });

        languagevi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LanguageActivity.this);
                builder.setMessage("Bạn muốn thay đổi ngôn ngữ về tiếng Việt?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        changelanguage("vi");
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

    }
}