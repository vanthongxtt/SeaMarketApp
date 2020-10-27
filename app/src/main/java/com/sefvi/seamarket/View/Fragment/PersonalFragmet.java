package com.sefvi.seamarket.View.Fragment;

import android.content.Intent;
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
import androidx.fragment.app.Fragment;

import com.sefvi.seamarket.View.Activity.RulesActivity;
import com.sefvi.seamarket.R;
import com.sefvi.seamarket.View.Activity.VersionActivity;

public class PersonalFragmet extends Fragment {
    RelativeLayout  personal_map,personal_setting,personal_hotline,
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
        personal_setting = v.findViewById(R.id.personal_setting);
        personal_hotline = v.findViewById(R.id.personal_hotline);
        personal_ruless = v.findViewById(R.id.personal_ruless);
        personal_version = v.findViewById(R.id.personal_version);

        personal_tv_phonenumber = v.findViewById(R.id.personal_tv_phonenumber);


    }
    private void event (){
        personal_tv_phonenumber.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String phone_no= personal_tv_phonenumber.getText().toString().replaceAll("-", "");
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse(phone_no));
                callIntent.setData(Uri.parse("tel:"+phone_no));
                startActivity(callIntent);
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

    }
}
