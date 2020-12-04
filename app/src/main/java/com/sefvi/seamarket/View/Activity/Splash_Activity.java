package com.sefvi.seamarket.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ProgressBar;

import com.sefvi.seamarket.R;
import com.sefvi.seamarket.Utils.ShPref;

public class Splash_Activity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(Splash_Activity.this);
//        String token = myPreferences.getString("TOKEN", "");
        SharedPreferences prefs = getSharedPreferences("Sea",MODE_PRIVATE);
        String token = prefs.getString("TOKEN", "");
        Log.d("token_s", token);

        if (isEmpty(token)){
            startActivity(new Intent(Splash_Activity.this, Login_Activity.class));
            finish();
        }else {
            startActivity(new Intent(Splash_Activity.this, MainActivity.class));
            finish();
        }


    }
    public static final boolean isEmpty(String x) {
        return (x == null || x.trim().equals(""));
    }

}