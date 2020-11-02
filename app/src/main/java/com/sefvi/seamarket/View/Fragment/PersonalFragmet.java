package com.sefvi.seamarket.View.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.sefvi.seamarket.View.Activity.Personal.OrderActivity;
import com.sefvi.seamarket.View.Activity.Personal.RulesActivity;
import com.sefvi.seamarket.R;
import com.sefvi.seamarket.View.Activity.Personal.SettingActivity;
import com.sefvi.seamarket.View.Activity.Personal.VersionActivity;

public class PersonalFragmet extends Fragment {
    RelativeLayout  personal_map,personal_order,personal_setting,personal_hotline,
            personal_ruless,personal_version;
    TextView personal_tv_username,personal_tv_phonenumber;
    ImageView personal_img_user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_personal,container,false);
        Anhxa(v);
        event();





        return v;
    }
    private void Anhxa (View v){
        personal_map = v.findViewById(R.id.personal_map);
        personal_order = v.findViewById(R.id.personal_order);
        personal_setting = v.findViewById(R.id.personal_setting);
        personal_hotline = v.findViewById(R.id.personal_hotline);
        personal_ruless = v.findViewById(R.id.personal_ruless);
        personal_version = v.findViewById(R.id.personal_version);

        personal_tv_username = v.findViewById(R.id.personal_tv_username);
        personal_tv_phonenumber = v.findViewById(R.id.personal_tv_phonenumber);
        personal_img_user = v.findViewById(R.id.personal_img_user);



    }
    private void event (){
        personal_hotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               xuLyGoiLuon(personal_tv_phonenumber.getText().toString(), getActivity());
            }
        });


        personal_ruless.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RulesActivity.class);
                startActivity(intent);
            }
        });

        personal_version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), VersionActivity.class);
                startActivity(intent);
            }
        });
        personal_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });
        personal_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                startActivity(intent);
            }
        });

    }
    // xin quyen call
    private static void xuLyGoiLuon(String sdt, Context context) {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            Uri uri = Uri.parse("tel:" + sdt);
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(uri);
            context.startActivity(intent);
        }
    }
    private static final int REQUEST_CALL = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
