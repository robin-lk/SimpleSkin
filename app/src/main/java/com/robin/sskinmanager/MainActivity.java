package com.robin.sskinmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.robin.skins.SkinFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory2(LayoutInflater.from(this), new SkinFactory(this));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}