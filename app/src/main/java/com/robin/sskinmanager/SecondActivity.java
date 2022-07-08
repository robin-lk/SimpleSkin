package com.robin.sskinmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.robin.skins.SkinManager;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SkinManager.getInstance().addUI(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().removeUI(this);
    }

}