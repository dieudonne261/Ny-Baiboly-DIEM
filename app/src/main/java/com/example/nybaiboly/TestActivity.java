package com.example.nybaiboly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.CompoundButton;

import com.example.nybaiboly.databinding.ActivityMainBinding;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.Objects;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_setting);
        SwitchMaterial switchMaterial = findViewById(R.id.switch1);
        //Objects.requireNonNull(getSupportActionBar()).setTitle();
        switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
        boolean isNight = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES;
        switchMaterial.setChecked(isNight);

    }

    @Override
    public void recreate() {
       finish();
       overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
       startActivity(getIntent());
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
}