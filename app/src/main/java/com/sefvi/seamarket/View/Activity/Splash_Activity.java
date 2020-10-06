package com.sefvi.seamarket.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.sefvi.seamarket.R;

import java.util.logging.Handler;

public class Splash_Activity extends AppCompatActivity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        new handler().postDelayed(new Runnable() {
//            @Override public void run() {
//                Intent i = new Intent(SplashScreen.this, MainActivity.class); startActivity(i);
//                finish(); } }, 3000);
    }


}